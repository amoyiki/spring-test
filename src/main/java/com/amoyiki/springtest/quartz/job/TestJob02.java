package com.amoyiki.springtest.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
@Component("testJob02")
@Transactional
@Slf4j
public class TestJob02 {
    public void execute() {
        log.info("TestJob02 开始执行");
        log.info("{}", new Date());
        log.info("TestJob02 结束执行");

    }
}
