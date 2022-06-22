package com.sunbeam.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description work queue 队列模型
 * @RabbitListener注解属性详解
 * 1.ackMode：消息确认模式 noack, auto, manual
 * 2.queues：监听的消息队列
 * 3.concurrency：并行消费 只有一个n值，表示固定4个消费，m-n，表示m个并行消费者，最多有n个
 **/
@Component
@Slf4j
public class DefaultModel {

    /**
     * 默认队列模型
     * 使用的是默认交换机，路由键是默认要发送队列的名字
     * 官方代码：https://www.rabbitmq.com/tutorials/tutorial-one-java.html
     */
//    @RabbitListener(queuesToDeclare = @Queue("simple"))
//    public void simpleQueueListener(Message message,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
//        String messageStr = new String(message.getBody());
//        log.info("message:{},deliveryTag:{}",messageStr,deliveryTag);
//    }

    /**
     * 消息消费确认机制demo
     */
    @RabbitListener(queuesToDeclare = @Queue("simple"),ackMode = "MANUAL",concurrency = "1-10")
    public void simpleListener(Message message,
                               @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                               Channel channel) {
        String messageStr = new String(message.getBody());
//        log.info("message:{},deliveryTag:{}",messageStr,deliveryTag);

        try {
//        channel.basicReject(deliveryTag,true);
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            log.info("message:{},deliveryTag:{}",messageStr,deliveryTag);
        }
    }

}
