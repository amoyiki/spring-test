package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.ScheduleJob;

import java.util.List;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
public interface ScheduleJobService {

    /**
     *
     * 添加定时任务
     * @author amoyiki
     * @param job
     * @return void
     */
    void add(ScheduleJob job);

    /**
     * 开始定时任务
     *
     * @author amoyiki
     * @param id
     * @return void
     */
    void start(int id);
    /**
     * 暂停定时任务
     *
     * @author amoyiki
     * @param id
     * @return void
     */
    void pause(int id);
    /**
     * 删除定时任务
     *
     * @author amoyiki
     * @param id
     * @return void
     */
    void delete(int id);

    /**
     * 开始所有定时任务
     *
     * @author amoyiki
     * @param
     * @return void
     */
    void startAllJob();


    /**
     * 暂停所有定时任务
     *
     * @author amoyiki
     * @param
     * @return void
     */
    void pauseAllJob();

    List<ScheduleJob> allList();

}
