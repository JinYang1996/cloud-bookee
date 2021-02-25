package com.bookee.bookee.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bookee.bookee.enums.UserTypeEnum;
import com.bookee.factory.MessageServiceStrategyFactory;
import com.bookee.factory.MessageTaskTypeServiceStrategyFactory;

@Service
public class ThirdPartMessageServiceImpl implements MessageService,InitializingBean {

	@Override
	public String sendMessage(String messageType) throws Exception {
		MessageTaskTypeService message = MessageTaskTypeServiceStrategyFactory.getMessageTaskTypeService(messageType);
		return message.sendMessage(UserTypeEnum.THIRD.getKey());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		MessageServiceStrategyFactory.register(UserTypeEnum.THIRD.getKey(), this);
	}

}
