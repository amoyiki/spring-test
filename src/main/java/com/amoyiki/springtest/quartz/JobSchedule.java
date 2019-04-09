package com.amoyiki.springtest.quartz;

import com.amoyiki.springtest.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
@Component
@Slf4j
public class JobSchedule implements CommandLineRunner {

    @Autowired
    private QuartzService quartzService;

    /**
     *  任务调度开始
     *
     * @author amoyiki
     * @param args
     * @return void
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("├ [任务调度] =============== 开始 =================");
        quartzService.timingTask();
        log.info("├ [任务调度] =============== 结束 =================");
    }
}
