package com.amoyiki.springtest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 手动 ACK 消息
 *
 * @author amoyiki
 * @since 2019/3/31
 */
@Slf4j
@Service
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
    /**
     * 实现消息发送后到 RabbitMQ 交换器接受 ACK 回调，消息发送确认失败就进行重试
     *
     * @author amoyiki
     * @param correlationData
     * @param ack
     * @param cause
     * @return void
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("├ [消息队列]: 消息ID:{} ACK 成功", correlationData.getId());
        } else {
            log.error("├ [消息队列]: 消息ID:{} ACK 失败", correlationData.getId());
        }
    }

    /**
     * 消息发送到 RabbitMQ 交换器， 但无相应队列与交换器绑定时回调
     *
     * @author amoyiki
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     * @return void
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("├ [消息队列]: 消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",replyCode, replyText, exchange, routingKey, new String(message.getBody()));
    }
    /**
     * convertAndSend 异步,消息是否发送成功用ConfirmCallback和ReturnCallback回调函数类确认。
     * 发送MQ消息
     *
     * @author amoyiki
     * @param exchangeName
     * @param routingKey
     * @param msg
     * @return void
     */
    public void sendMessage(String exchangeName, String routingKey, Object msg){
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg, new CorrelationData(UUID.randomUUID().toString()));
    }
    /**
     *  sendMessageAndReturn 当发送消息过后,该方法会一直阻塞在哪里等待返回结果,
     *  直到请求超时,配置spring.rabbitmq.template.reply-timeout来配置超时时间。
     *  发送MQ消息并返回结果
     *
     * @author amoyiki
     * @param exchangeName
     * @param routingKey
     * @param msg
     * @return java.lang.Object
     */
    public Object sendMessageAndReturn(String exchangeName, String routingKey, Object msg){
        return rabbitTemplate.convertSendAndReceive(exchangeName, routingKey, msg, new CorrelationData(UUID.randomUUID().toString()));
    }
}
