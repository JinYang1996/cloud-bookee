package com.bookee.bookee.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bookee.bookee.properities.ProfileProperities;
@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	ProfileProperities profileProperities;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(profileProperities.getEmail());
		
	}

}
