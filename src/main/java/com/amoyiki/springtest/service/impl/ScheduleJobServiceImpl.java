package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.ScheduleJob;
import com.amoyiki.springtest.enums.JobOperateEnum;
import com.amoyiki.springtest.mapper.ScheduleJobMapper;
import com.amoyiki.springtest.service.QuartzService;
import com.amoyiki.springtest.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
@Service
@Transactional
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private QuartzService quartzService;
    @Autowired
    private ScheduleJobMapper jobMapper;

    @Override
    public void add(ScheduleJob job) {
        jobMapper.insert(job);
        try {
            quartzService.addJob(job);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(int id) {
        ScheduleJob scheduleJob = jobMapper.selectByPrimaryKey(id);
        scheduleJob.setStatus(1);
        jobMapper.updateByPrimaryKey(scheduleJob);
        try {
            quartzService.operateJob(JobOperateEnum.START, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(int id) {
        ScheduleJob scheduleJob = jobMapper.selectByPrimaryKey(id);
        scheduleJob.setStatus(2);
        jobMapper.updateByPrimaryKey(scheduleJob);
        try {
            quartzService.operateJob(JobOperateEnum.PAUSE, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        ScheduleJob scheduleJob = jobMapper.selectByPrimaryKey(id);
        jobMapper.delete(scheduleJob);
        try {
            quartzService.operateJob(JobOperateEnum.DELETE, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startAllJob() {
        jobMapper.updateAllStatus(JobOperateEnum.START.getCode());
        try {
            quartzService.startAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAllJob() {
        jobMapper.updateAllStatus(JobOperateEnum.PAUSE.getCode());
        try {
            quartzService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ScheduleJob> allList() {
        return jobMapper.selectAll();
    }
}
