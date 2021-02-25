package com.jy.cloudconsumer3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CloudConsumer3Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer3Application.class, args);
    }

}
