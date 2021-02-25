package com.bookee.bookee.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaInitialConfig {
    /**
     * 设置分区数为8 分区副本为2
     * @return
     */
    @Bean
    public NewTopic initialTopic(){
        return new NewTopic("testtopic2",12,(short) 2);
    }

    @Bean
    public NewTopic updateTopic(){
        return new NewTopic("testtopic4",10,(short)3);
    }
}
