package com.jy.cloudconsumer3.controller;

import com.jy.cloudconsumer3.feign.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    HelloRemote helloRemote;

    @RequestMapping("/hello")
    public String hello(String name){
        return helloRemote.hello(name);
    }
}
