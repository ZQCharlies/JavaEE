package com.sunbeam.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wzq
 * @Date 2021/7/11
 **/
@Configuration
public class WorkMQConfig {

    public static final String WORK_QUEUE_NAME = "work.queue";

    @Bean
    public Queue workQueue(){
        return new Queue(WORK_QUEUE_NAME);
    }

}
