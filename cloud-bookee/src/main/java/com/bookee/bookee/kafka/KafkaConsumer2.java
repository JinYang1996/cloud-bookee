package com.bookee.bookee.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer2 {

    @KafkaListener(id="consumer2",groupId = "group1",topicPartitions = {@TopicPartition(topic = "testtopic4",partitions = {"2"})})
    public void onMessage2(ConsumerRecord<?,?> record,Consumer consumer){
        log.error("消费者2 ===== 接收到消息,{},{},{},{}",record.topic(),record.partition(),record.value(),record.offset());
        try {
            consumer.commitAsync();
        } catch (Exception e) {
            log.error("消费者2 ===== 异步提交失败 {},{},{},{} 同步提交开始。。。",record.topic(),record.partition(),record.value(),record.offset());
            consumer.commitSync();
        }
    }
}
