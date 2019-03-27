package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.entry.ScheduleJob;
import com.amoyiki.springtest.enums.JobOperateEnum;
import com.amoyiki.springtest.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amoyiki
 * @since 2019/3/27
 */
@RestController
@RequestMapping("/job")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService jobService;

    @GetMapping(value = "/hello")
    public String helloJob(){
        return "Hello Quartz!";
    }

    @GetMapping("/add")
    public String add(){
        ScheduleJob job = new ScheduleJob();
        job.setJobName("任务01");
        job.setCronExpression("0/1 * * * * ?");
        job.setBeanName("testJob01");
        job.setMethodName("execute");
        job.setStatus(JobOperateEnum.PAUSE.getCode());
        job.setDeleted(false);
        jobService.add(job);
        return "新增定时任务成功";
    }

    @GetMapping("/start/{id}")
    public String start(@PathVariable("id") Integer id){
        jobService.start(id);
        return "启动定时任务成功";
    }

    @GetMapping("/pause/{id}")
    public String pause(@PathVariable("id") Integer id){
        jobService.pause(id);
        return "暂停定时任务成功";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        jobService.delete(id);
        return "删除定时任务成功";
    }

    @GetMapping("/startAllJob")
    public String startAllJob(){
        jobService.startAllJob();
        return "启动所有定时任务成功";
    }


    @GetMapping("/pauseAllJob")
    public String pauseAllJob(){
        jobService.pauseAllJob();
        return "暂停所有定时任务成功";
    }






}
