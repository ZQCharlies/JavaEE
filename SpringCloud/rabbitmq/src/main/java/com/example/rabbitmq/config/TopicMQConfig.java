package com.example.rabbitmq.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author wzq
 * @Date 2021/7/11
 * topic 配置
 **/
@Configuration
public class TopicMQConfig {
    //queue
    public static final String TOPIC_QUEUE_NAME_ONE = "topic.queue.one";
    public static final String TOPIC_QUEUE_NAME_TWO = "topic.queue.two";
    public static final String TOPIC_QUEUE_NAME_MORE = "topic.queue.more";

    //exchange
    public static final String TOPIC_EXCHANGE_NAME = "topic.exchange";

    //routing key
    public static final String TOPIC_ROUTING_KEY_ONE = "key.one";
    public static final String TOPIC_ROUTING_KEY_TWO = "key.two";
    public static final String TOPIC_ROUTING_MORE = "key.#";

}
