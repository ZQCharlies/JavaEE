package com.sunbeam.rabbitmq.producer;

import com.sunbeam.rabbitmq.config.DirectMQConfig;
import com.sunbeam.rabbitmq.config.FanoutMQConfig;
import com.sunbeam.rabbitmq.config.TopicMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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

//        for (int i = 0; i < 1000000; i++) {
            String concatMessage = String.valueOf(Math.random());
//            log.info("message:{}",concatMessage);
            rabbitTemplate.convertAndSend("simple",concatMessage);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        }
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

    /**
     * spring:
     *   rabbitmq:
     *     host: 192.168.31.168
     *     #开启rabbitMQ的生产方确认模式
     *     publisher-confirm-type: correlated
     *     # 开启发布者退回模式
     *     publisher-returns: true
     */
    @GetMapping("callback")
    public void callback(){

        //确认模式：保证发送方到交换机的可靠性。
        //    1.开启confirm模式，publisher-confirm-type: correlated
        //    2.设置rabbitTemplate的确认回调函数。判断消息没有到达交换机
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if(b==false){//消息没有到达交换机,如果消息到达交换机则返回true,如果消息没有到达交换机则返回一个false
                    System.out.println("继续发现消息");
                    //取消订单
                }
            }
        });

        //退回模式:保证到达队列的可靠性
        //1. 开启退回模式。
        //2. 设置RabbitTemplate的退回回调函数。只要交换机到队列失败时才会触发该方法
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback(){
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                //可以继续发送也可以取消相应的业务功能。
                System.out.println("消息从交换机到队列失败"+returnedMessage.getReplyText());
            }
        });


    }
}
