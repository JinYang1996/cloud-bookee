package com.bookee.bookee.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Configuration
@Slf4j
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean("zkClient")
    public ZooKeeper zkClient(){
        ZooKeeper zooKeeper = null;

        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString,timeout,new Watcher(){

                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            log.error("初始化zookeeper连接成功，状态为{}",zooKeeper.getState());
        } catch (Exception e) {
            log.error("初始化zookeeper连接失败{}",e.getMessage());
        }
        return zooKeeper;
    }
}
