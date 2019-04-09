package com.amoyiki.springtest.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author amoyiki
 * @date 2019/3/27
 */
@Component("testJob01")
@Transactional
@Slf4j
public class TestJob01 {
    public void execute() {
        log.info("TestJob01 开始执行");
        log.info("{}", new Date());
        log.info("TestJob01 结束执行");

    }
}
