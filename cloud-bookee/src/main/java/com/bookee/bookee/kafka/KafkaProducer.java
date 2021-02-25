package com.bookee.bookee.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/kafka/normal/{sendMessage}")
    public void sendMessage(@PathVariable("sendMessage")String message){
        kafkaTemplate.send("topic1",message);
    }

    @GetMapping("/kafka/callback/{sendMessage}")
    public void sendMessage1(@PathVariable("sendMessage")String message){
        kafkaTemplate.send("testtopic4",message).addCallback(
                success->{
                    log.error("消息发送成功，{},{},{}",success.getRecordMetadata().topic(),success.getRecordMetadata().partition(),success.getRecordMetadata().offset());
                },failure->{
                    log.error("消息发送失败");
                }
        );
    }

    @GetMapping("/kafka/messageKey/{sendMessage}")
    public void sendMessage2(@PathVariable("sendMessage")String message){
        kafkaTemplate.send("testtopic4","111111",message).addCallback(
                success->{
                    log.error("消息发送成功，{},{},{}",success.getRecordMetadata().topic(),success.getRecordMetadata().partition(),success.getRecordMetadata().offset());
                },failure->{
                    log.error("消息发送失败");
                }
        );
    }

    @GetMapping("/kafka/transcationMessage/{sendMessage}")
    public void sendMessage3(@PathVariable("sendMessage")String message){
        kafkaTemplate.executeInTransaction(operations->{
            operations.send("testtopic4",message).addCallback(
                    success->{
                        log.error("消息发送成功，{},{},{}",success.getRecordMetadata().topic(),success.getRecordMetadata().partition(),success.getRecordMetadata().offset());
                    },failure->{
                        log.error("消息发送失败");
                    }
            );
            throw new RuntimeException("111");
        });
    }
}
