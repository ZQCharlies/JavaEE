package com.example.rabbitmq.listener;

import com.example.rabbitmq.config.TopicMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wzq
 * @Date 2021/7/11
 * 订阅模型 topic 根据路由键转发消息到队列，其中一个队列可以绑定多个"通配符"的路由键
 * 官方示例：https://www.rabbitmq.com/tutorials/tutorial-four-java.html
 **/
@Slf4j
@Component
public class TopicModel {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = TopicMQConfig.TOPIC_QUEUE_NAME_ONE,durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = TopicMQConfig.TOPIC_EXCHANGE_NAME,type = "topic"),
            key = TopicMQConfig.TOPIC_ROUTING_KEY_ONE)})
    public void topicProcessOne(Message message){
        String content = new String(message.getBody());
        log.info("queue:{},content:{}",TopicMQConfig.TOPIC_QUEUE_NAME_ONE,content);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = TopicMQConfig.TOPIC_QUEUE_NAME_TWO,durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = TopicMQConfig.TOPIC_EXCHANGE_NAME,type = "topic"),
            key = TopicMQConfig.TOPIC_ROUTING_KEY_TWO)})
    public void topicProcessTwo(Message message){
        String content = new String(message.getBody());
        log.info("queue:{},content:{}",TopicMQConfig.TOPIC_QUEUE_NAME_TWO,content);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = TopicMQConfig.TOPIC_QUEUE_NAME_MORE,durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = TopicMQConfig.TOPIC_EXCHANGE_NAME,type = "topic"),
            key = TopicMQConfig.TOPIC_ROUTING_MORE)})
    public void topicProcessMoreKeys(Message message){
        String content = new String(message.getBody());
        log.info("queue:{},content:{}",TopicMQConfig.TOPIC_QUEUE_NAME_MORE,content);
    }
}
