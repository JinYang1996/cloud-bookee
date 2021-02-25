package com.bookee.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class MessageListener implements ApplicationListener<MessageEvent> {

	@Override
	public void onApplicationEvent(MessageEvent event) {
		String message = event.getMessage();
		System.out.println("打印的信息是："+message);
	}

}
