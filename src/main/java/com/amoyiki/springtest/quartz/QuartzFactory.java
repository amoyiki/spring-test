package com.amoyiki.springtest.quartz;
import com.amoyiki.springtest.entry.ScheduleJob;
import com.amoyiki.springtest.utils.SpringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * 任务调度工厂
 * @author amoyiki
 * @since 2019/3/27
 */
public class QuartzFactory implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        Object o = SpringUtil.getBean(scheduleJob.getBeanName());
        try {
            Method method = o.getClass().getMethod(scheduleJob.getMethodName());
            method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
