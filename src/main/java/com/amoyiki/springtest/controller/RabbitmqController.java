package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.service.impl.RabbitSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author amoyiki
 * @since 2019/3/31
 */
@RestController
@Slf4j
public class RabbitmqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitSender sender;

    /**
     * 发送消息，生产者
     *
     *
     */
    @GetMapping("directSend")
    public String directSend() {
        String message = "direct 发送消息";
//        rabbitTemplate.convertAndSend("DirectExchange", "DirectKey",
//                message, new CorrelationData(UUID.randomUUID().toString()));
        sender.sendMessage("DirectExchange", "DirectKey", message);
        return "OK";
    }

    @GetMapping("topicSend")
    public String topicSend() {
        String message = "topic 发送消息";
//        rabbitTemplate.convertAndSend("TopicExchange", "Topic.Key",
//                message, new CorrelationData(UUID.randomUUID().toString()));
        sender.sendMessage("TopicExchange", "Topic.Key", message);
        return "OK";
    }

    @GetMapping("fanoutSend")
    public String fanoutSend() {
        String message = "fanout  发送消息";
        rabbitTemplate.convertAndSend("FanoutExchange", "", message, new CorrelationData(UUID.randomUUID().toString()));
        return "OK";
    }

    /**
     *
     * 消息消费者
     *
     */
    @RabbitListener(queues = "DirectQueue")
    @RabbitHandler
    public void directMessage(String message){
        log.error("DirectConsumer directMessage : "+message);
    }

    @RabbitListener(queues = "TopicQueue")
    @RabbitHandler
    public void topicMessage(String message){
        log.error("TopicConsumer  topicMessage : "+message);
    }

    @RabbitListener(queues = "FanoutQueue")
    @RabbitHandler
    public void fanoutMessage(String message){
        log.error("FanoutConsumer   fanoutMessage  : "+message);
    }
}
