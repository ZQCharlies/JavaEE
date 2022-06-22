package com.sunbeam.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wzq
 * @Date 2021/7/11
 **/
@Configuration
public class DirectMQConfig {
    //queue
    public static final String DIRECT_QUEUE_ONE = "direct.queue.one";
    public static final String DIRECT_QUEUE_TWO = "direct.queue.two";
    //exchange
    public static final String DIRECT_EXCHANGE = "direct.exchange";
    //routing key
    public static final String K1 = "k1";
    public static final String K2 = "k2";
}
