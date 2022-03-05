package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wzq
 * @Date 2021/7/11
 **/
@Configuration
public class FanoutMQConfig {
    //queue
    public static final String FANOUT_QUEUE_ONE = "fanout.queue.one";
    public static final String FANOUT_QUEUE_TWO = "fanout.queue.two";
    //exchange
    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    //队列
    @Bean
    public Queue fanoutQueueOne(){return new Queue(FANOUT_QUEUE_ONE);}
    @Bean
    public Queue fanoutQueueTwo(){return new Queue(FANOUT_QUEUE_TWO);}
    //交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    //绑定队列
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }
}
