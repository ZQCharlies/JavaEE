package com.sunbeam.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author wzq
 * @desc: kafka配置类
 * @date 2022/4/29
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newTopic(){
        NewTopic topic = TopicBuilder.name("topic")
                .partitions(1)//分区个数
                .replicas(1)//分片
                .build();
        return topic;
    }
}
