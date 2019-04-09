package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.ScheduleJob;
import com.amoyiki.springtest.enums.JobOperateEnum;
import org.quartz.SchedulerException;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
public interface QuartzService {
    /**
     * 查询是否有要定时的任务
     *
     * @param
     * @return void
     * @author amoyiki
     */
    void timingTask();

    /**
     * 添加任务
     *
     * @author amoyiki
     * @param job
     * @return void
     */
    void addJob(ScheduleJob job);

    /**
     * 修改任务
     *
     * @author amoyiki
     * @param operateEnum
     * @param job
     * @return void
     */
    void operateJob(JobOperateEnum operateEnum, ScheduleJob job) throws SchedulerException;

    /**
     * 开始所有任务
     *
     * @author amoyiki
     * @param
     * @return void
     */
    void startAllJob() throws SchedulerException;

    /**
     * 暂停所有任务
     *
     * @author amoyiki
     * @param
     * @return void
     */
    void pauseAllJob() throws SchedulerException;
}
