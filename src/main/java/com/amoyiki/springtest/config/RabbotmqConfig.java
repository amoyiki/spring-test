package com.amoyiki.springtest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author amoyiki
 * @date 2019/3/28
 */
@Configuration
@Slf4j
public class RabbotmqConfig {

    // 消息交换机
    private static final String DIRECT_EXCHANGE = "DirectExchange";
    private static final String TOPIC_EXCHANGE = "TopicExchange";
    private static final String FANOUT_EXCHANGE = "FanoutExchange";

    // 队列名
    private static final String DIRECT_QUEUE = "DirectQueue";
    private static final String TOPIC_QUEUE = "TopicQueue";
    private static final String FANOUT_QUEUE = "FanoutQueue";

    // Key
    private static final String DIRECT_KEY = "DirectKey";
    private static final String TOPIC_KEY = "Topic.#";

    /**
     * 1.name           队列名字
     * 2.durable="true" 是否持久化 rabbitmq重启的时候不需要创建新的队列
     * 3.auto-delete    表示消息队列没有在使用时将被自动删除 默认是false
     * 4.exclusive      表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean
    public Queue dirctQueue() {
        return new Queue(DIRECT_QUEUE, true, false, false);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE, true, false, false);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE, true, false, false);
    }

    /**
     * 1.name       交换机名称
     * 2.durable    是否持久化
     * 3.auto-delete 消费者断开后是否删除
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

    /**
     * 将 Queue 和 exchange 绑定
     *
     */
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(dirctQueue()).to(directExchange()).with(DIRECT_KEY);
    }

    @Bean
    public Binding bindingTopic(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_KEY);
    }
    @Bean
    public Binding bindingFanout(){
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    /**
     *
     * 消息转化实例， 转成 JSON 传输，实体类不用序列化
     *
     */
    @Bean
    public MessageConverter integrationEventMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
