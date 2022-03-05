package com.example.rabbitmq.listener;

import com.example.rabbitmq.config.DirectMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 指定key通过一个交换机，将消息发送到指定队列
 */
@Slf4j
@Component
public class DirectModel {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = DirectMQConfig.DIRECT_QUEUE_ONE),
            exchange = @Exchange(value = DirectMQConfig.DIRECT_EXCHANGE,type = "direct"),
            key = "k1"
    )})
    public void queue1(Message message){

        log.info("queue_1,msg:{}",message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = DirectMQConfig.DIRECT_QUEUE_TWO),
            exchange = @Exchange(value = DirectMQConfig.DIRECT_EXCHANGE,type = "direct"),
            key = "k1"
    )})
    public void queue2(Message message){

        log.info("queue_2,msg:{}",message);
    }
}
