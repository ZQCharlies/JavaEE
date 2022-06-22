package com.sunbeam.rabbitmq.listener;

import com.sunbeam.rabbitmq.config.FanoutMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wzq
 * @Date 2021/7/11
 * 订阅模型 fanout  交换机把消息发送给绑定过的所有队列
 **/
@Slf4j
@Component
public class FanoutModel {

    //配置版
    @RabbitListener(queues = FanoutMQConfig.FANOUT_QUEUE_ONE)
    public void subProcessOne(Message message){
        String content = new String(message.getBody());
        log.info("fanout one,message:{}",content);

    }

    @RabbitListener(queues = FanoutMQConfig.FANOUT_QUEUE_TWO)
    public void subProcessTwo(Message message){
        String content = new String(message.getBody());
        log.info("fanout two,message:{}",content);
    }

}
