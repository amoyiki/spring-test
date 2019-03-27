package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.ScheduleJob;
import com.amoyiki.springtest.enums.JobOperateEnum;
import com.amoyiki.springtest.mapper.ScheduleJobMapper;
import com.amoyiki.springtest.quartz.QuartzFactory;
import com.amoyiki.springtest.service.QuartzService;
import com.amoyiki.springtest.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/27
 */
@Service
@Transactional
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    // 调度器
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobMapper jobMapper;


    @Override
    public void timingTask() {
        List<ScheduleJob> scheduleJobs = jobMapper.selectAll();
        log.info("├ [job size]: {}", scheduleJobs);
        if (scheduleJobs != null){
            scheduleJobs.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJob job) {
        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                .startNow()
                .build();
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class)
                .withIdentity(job.getJobName())
                .build();
        // 传入调度的数据
        jobDetail.getJobDataMap().put("scheduleJob", job);

        // 调度作业

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void operateJob(JobOperateEnum operateEnum, ScheduleJob job) throws SchedulerException {
        JobKey jobKey = new JobKey(job.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null){
            throw new RuntimeException("调度任务错误");
        }
        switch (operateEnum){
            case START:
                scheduler.resumeJob(jobKey);
                break;
            case PAUSE:
                scheduler.pauseJob(jobKey);
                break;
            case DELETE:
                scheduler.deleteJob(jobKey);
                break;

        }
    }

    @Override
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.standby();
    }
}
