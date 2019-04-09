package com.amoyiki.springtest.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 覆盖已有任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延迟 60s 再启动任务
        schedulerFactoryBean.setStartupDelay(60);
        return schedulerFactoryBean;
    }
    @Bean(name = "scheduler")
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
