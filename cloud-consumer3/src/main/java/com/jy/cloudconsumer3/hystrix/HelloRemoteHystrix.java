package com.jy.cloudconsumer3.hystrix;

import com.jy.cloudconsumer3.feign.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return name+" is failed";
    }
}
