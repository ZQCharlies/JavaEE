package com.sunbeam.rocketmq.controller;

import com.sunbeam.rocketmq.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wzq
 * @Date : 2022/6/22
 * @Version : 1.0
 * @Description :
 **/
@Api("触发生产者")
@RestController
public class ProducerController {

    @Value("${demo.rocketmq.transTopic}")
    private String springTransTopic;
    @Value("${demo.rocketmq.topic}")
    private String springTopic;
    @Value("${demo.rocketmq.topic.user}")
    private String userTopic;

    @Value("${demo.rocketmq.orderTopic}")
    private String orderPaidTopic;
    @Value("${demo.rocketmq.msgExtTopic}")
    private String msgExtTopic;
    @Value("${demo.rocketmq.stringRequestTopic}")
    private String stringRequestTopic;
    @Value("${demo.rocketmq.bytesRequestTopic}")
    private String bytesRequestTopic;
    @Value("${demo.rocketmq.objectRequestTopic}")
    private String objectRequestTopic;
    @Value("${demo.rocketmq.genericRequestTopic}")
    private String genericRequestTopic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @ApiOperation("sendMessage")
    @GetMapping("/sendMessage")
    public String sendMessage(){

        SendResult sendResult = null;
//        sendResult = rocketMQTemplate.syncSend(springTopic, "Hello, World!");
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);

        sendResult = rocketMQTemplate.syncSend(userTopic, new User().setUserAge((byte) 18).setUserName("Kitty"));
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", userTopic,sendResult);
//
//        sendResult = rocketMQTemplate.syncSend(userTopic, MessageBuilder.withPayload(
//                new User().setUserAge((byte) 21).setUserName("Lester")).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", userTopic, sendResult);
//
//        // Use the extRocketMQTemplate
//        sendResult = extRocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload("Hello, World!2222".getBytes()).build());
//        System.out.printf("extRocketMQTemplate.syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
//
//        // Send string with spring Message
//        sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        System.out.printf("syncSend2 to topic %s sendResult=%s %n", springTopic, sendResult);
//
//        // Send user-defined object
//        rocketMQTemplate.asyncSend(orderPaidTopic, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult var1) {
//                System.out.printf("async onSucess SendResult=%s %n", var1);
//            }
//
//            @Override
//            public void onException(Throwable var1) {
//                System.out.printf("async onException Throwable=%s %n", var1);
//            }
//
//        });
//
//        // Send message with special tag
//        rocketMQTemplate.convertAndSend(msgExtTopic + ":tag0", "I'm from tag0");  // tag0 will not be consumer-selected
//        System.out.printf("syncSend topic %s tag %s %n", msgExtTopic, "tag0");
//        rocketMQTemplate.convertAndSend(msgExtTopic + ":tag1", "I'm from tag1");
//        System.out.printf("syncSend topic %s tag %s %n", msgExtTopic, "tag1");
//
//        // Send a batch of strings
//        testBatchMessages();
//
//        // send a bath of strings orderly
//        testSendBatchMessageOrderly();
//
//        // Send transactional messages using rocketMQTemplate
//        testRocketMQTemplateTransaction();
//
//        // Send transactional messages using extRocketMQTemplate
//        testExtRocketMQTemplateTransaction();
//
//        // Send request in sync mode and receive a reply of String type.
//        String replyString = rocketMQTemplate.sendAndReceive(stringRequestTopic, "request string", String.class);
//        System.out.printf("send %s and receive %s %n", "request string", replyString);
//
//        // Send request in sync mode with timeout parameter and receive a reply of byte[] type.
//        byte[] replyBytes = rocketMQTemplate.sendAndReceive(bytesRequestTopic, MessageBuilder.withPayload("request byte[]").build(), byte[].class, 3000);
//        System.out.printf("send %s and receive %s %n", "request byte[]", new String(replyBytes));
//
//        // Send request in sync mode with hashKey parameter and receive a reply of User type.
//        User requestUser = new User().setUserAge((byte) 9).setUserName("requestUserName");
//        User replyUser = rocketMQTemplate.sendAndReceive(objectRequestTopic, requestUser, User.class, "order-id");
//        System.out.printf("send %s and receive %s %n", requestUser, replyUser);
//        // Send request in sync mode with timeout and delayLevel parameter parameter and receive a reply of generic type.
//        ProductWithPayload<String> replyGenericObject = rocketMQTemplate.sendAndReceive(genericRequestTopic, "request generic",
//                new TypeReference<ProductWithPayload<String>>() {
//                }.getType(), 30000, 2);
//        System.out.printf("send %s and receive %s %n", "request generic", replyGenericObject);
//
//        // Send request in async mode and receive a reply of String type.
//        rocketMQTemplate.sendAndReceive(stringRequestTopic, "request string", new RocketMQLocalRequestCallback<String>() {
//            @Override public void onSuccess(String message) {
//                System.out.printf("send %s and receive %s %n", "request string", message);
//            }
//
//            @Override public void onException(Throwable e) {
//                e.printStackTrace();
//            }
//        });
//        // Send request in async mode and receive a reply of User type.
//        rocketMQTemplate.sendAndReceive(objectRequestTopic, new User().setUserAge((byte) 9).setUserName("requestUserName"), new RocketMQLocalRequestCallback<User>() {
//            @Override public void onSuccess(User message) {
//                System.out.printf("send user object and receive %s %n", message.toString());
//            }
//
//            @Override public void onException(Throwable e) {
//                e.printStackTrace();
//            }
//        }, 5000);
        return "null";
    }
}
