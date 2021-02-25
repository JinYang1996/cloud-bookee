package com.bookee.bookee.controller;

import java.util.List;

import com.bookee.bookee.utils.zkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookee.bookee.entity.UserEo;
import com.bookee.bookee.properities.LibraryProperities;
import com.bookee.bookee.properities.WebSite;
import com.bookee.bookee.service.IProductService;
import com.bookee.bookee.service.MessageService;
import com.bookee.bookee.service.UserService;
import com.bookee.event.MessagePublisher;
import com.bookee.factory.MessageServiceStrategyFactory;

@RestController
@RequestMapping(value = "/system/user")
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    MessagePublisher messagePublisher;
    
    @Autowired
    LibraryProperities libraryProperities;
    
    @Autowired
    WebSite webSite;
    
    @Autowired
    IProductService productService;

    @Autowired
    zkUtils zkUtils;

    @RequestMapping("selectOne")
    public List<UserEo> selectOne(UserEo userEo){
    	userEo = userEo.toBuilder()
    			.id("11111")
    			.name("张三")
    			.build();
    	System.out.println(userEo.toString());
        return userService.selectOne(userEo);
    }
    
    @RequestMapping("sendMessage")
    public String sendMessage(String userType,String taskTypeCode) throws Exception{
        MessageService service =  MessageServiceStrategyFactory.getMessageByUserType(userType);
        return service.sendMessage(taskTypeCode);
    }
    
    @RequestMapping("publishMessage")
    public void publishMessage(String message){
    	System.out.println(libraryProperities.getLocation());
    	System.out.println(libraryProperities.getBooks());
    	System.out.println(webSite.getUrl());
    	messagePublisher.publish(message);
    }
    
    @RequestMapping("oldLogin")
    public String oldLogin(){
    	return "oldLogin";
    }
    
    @RequestMapping("login")
    public String login(){
    	return "login";
    }
    
    @RequestMapping("seckillProduct")
    public String seckillProduct(Long productId){
    	boolean flag = productService.seckillProduct(productId,1);
    	if(flag){
    		return "抢购成功";
    	}else{
    		return "抢购失败";
    	}
    }

    @RequestMapping("createZkNode")
    public boolean createZkNode(String path,String data){
        return zkUtils.createNode(path,data);
    }

    @RequestMapping("createEphemeralNode")
    public boolean createEphemeralNode(String path,String data){
        return zkUtils.createEphemeralNode(path,data);
    }

    @RequestMapping("getChildrenNodes")
    public List<String> getChildrenNodes(String path) throws Exception{
        return zkUtils.getChildren(path);
    }

    /**
     * 以下单-扣款举例
     * 整个事务定义为orderPay节点，此时创建持久化节点，同时创建下单和扣款临时顺序节点
     *
     */
}
