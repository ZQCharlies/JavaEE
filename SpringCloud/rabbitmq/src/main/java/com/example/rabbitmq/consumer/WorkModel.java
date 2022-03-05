package com.example.rabbitmq.consumer;

import com.example.rabbitmq.config.WorkMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * work queue模型，任务在多个消费者之间共享，但是一个消息只能被一个消费者获取。
 * 官方网址：https://www.rabbitmq.com/tutorials/tutorial-two-java.html
 **/
@Slf4j
@Component
public class WorkModel {

    @RabbitListener(queuesToDeclare = @Queue("work_queue"))
    public void workProcessOne(Message message){
        String content = new String(message.getBody());
        log.info("process one:"+content);
    }

    @RabbitListener(queues = "work_queue")
    public void workProcessTwo(Message message){
        String content = new String(message.getBody());
        log.info("process two:"+content);
    }
}
