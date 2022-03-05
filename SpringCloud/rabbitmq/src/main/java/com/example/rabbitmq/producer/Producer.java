package com.example.rabbitmq.producer;

import com.example.rabbitmq.config.DirectMQConfig;
import com.example.rabbitmq.config.FanoutMQConfig;
import com.example.rabbitmq.config.TopicMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author wzq
 * @Date 2021/7/6
 * workQueue队列模型，消息分发;角色1、生产者 2、队列 3、消费者
 **/
@Slf4j
@RestController
@RequestMapping("/producer/")
public class Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * @description: 简单队列模型
     */
    @GetMapping(value = "simple")
    public void  simple(){
        String concatMessage = String.valueOf(Math.random());
        log.info("message:{}",concatMessage);
        rabbitTemplate.convertAndSend("simple",concatMessage);
    }

    /**
     * work queue
     */
    @GetMapping(value = "work")
    public void  work(){
        for (int i = 0; i < 8; i++) {
            String concatMessage = String.valueOf(Math.random());
            log.info("message:{}",concatMessage);
            rabbitTemplate.convertAndSend("work_queue",concatMessage);
        }
    }

    /**
     * 订阅模型 fanout模型 由交换机转发到多个队列
     */
    @GetMapping("fanout")
    public void fanout(){
        for (int i = 0; i < 8; i++) {
            String concatMessage = String.valueOf(Math.random());
            log.info("message:{}",concatMessage);
            rabbitTemplate.convertAndSend(FanoutMQConfig.FANOUT_EXCHANGE,"",concatMessage);
        }
    }

    /**
     * 路由模式 direct
     */
    @GetMapping(value = "direct")
    public void  direct(){
        for (int i = 0; i < 8; i++) {
            String concatMessage = String.valueOf(Math.random());
            log.info("message:{}",concatMessage);
            if (i%2==0){
                rabbitTemplate.convertAndSend(DirectMQConfig.DIRECT_EXCHANGE,DirectMQConfig.K1,concatMessage);
            }else {
                rabbitTemplate.convertAndSend(DirectMQConfig.DIRECT_EXCHANGE,DirectMQConfig.K2,concatMessage);
            }
        }
    }

    /**
     * topic模型 根据路由键转发消息到队列，其中一个队列可以绑定多个"通配符"的路由键
     */
    @GetMapping("topic")
    public void topic(){
        for (int i = 0; i < 8; i++) {
            String concatMessage = String.valueOf(Math.random());
            if (i % 2 == 0){
                log.info("i:{},key:{},content:{}",i,TopicMQConfig.TOPIC_QUEUE_NAME_TWO,concatMessage);
                rabbitTemplate.convertAndSend(TopicMQConfig.TOPIC_EXCHANGE_NAME, TopicMQConfig.TOPIC_ROUTING_KEY_ONE,String.valueOf(i));
            }else {
                log.info("i:{},key:{},content:{}",i,TopicMQConfig.TOPIC_ROUTING_KEY_TWO,concatMessage);
                rabbitTemplate.convertAndSend(TopicMQConfig.TOPIC_EXCHANGE_NAME,TopicMQConfig.TOPIC_ROUTING_KEY_TWO,String.valueOf(i));
            }
        }
    }
}
