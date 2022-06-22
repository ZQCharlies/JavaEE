package com.sunbeam.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
@Slf4j
public class RabbitReturnCallback implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        //可以继续发送也可以取消相应的业务功能。
        log.info("消息从交换机到队列失败"+returnedMessage.getMessage());
    }
}
