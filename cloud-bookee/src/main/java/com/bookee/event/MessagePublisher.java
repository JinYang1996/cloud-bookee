package com.bookee.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

	@Autowired
	ApplicationContext applicationContext;
	
	public void publish(String message){
		applicationContext.publishEvent(new MessageEvent(this,message));
	}
}
