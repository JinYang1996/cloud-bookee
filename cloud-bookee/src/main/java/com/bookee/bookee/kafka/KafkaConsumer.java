package com.bookee.bookee.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(id="consumer1",topics = {"testtopic4"},groupId = "group1")
    public void onMessage(ConsumerRecord<?,?> record,Consumer consumer){
        log.error("消费者1 ===== 接收到消息,{},{},{},{}",record.topic(),record.partition(),record.value(),record.offset());
        try {
            consumer.commitAsync();
        } catch (Exception e) {
            log.error("消费者1 ===== 异步提交失败 {},{},{},{} 同步提交开始。。。",record.topic(),record.partition(),record.value(),record.offset());
            consumer.commitSync();
        }
    }

}
