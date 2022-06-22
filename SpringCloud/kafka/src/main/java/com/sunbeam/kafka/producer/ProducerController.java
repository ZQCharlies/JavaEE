package com.sunbeam.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq
 * @desc: kafka生产者
 * @date 2022/4/29
 */
@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        kafkaTemplate.send("topic", JSON.toJSONString("message"));
        return null;
    }

}
