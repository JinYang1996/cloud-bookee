package com.bookee.bookee.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bookee.bookee.enums.UserTypeEnum;
import com.bookee.factory.MessageServiceStrategyFactory;
import com.bookee.factory.MessageTaskTypeServiceStrategyFactory;

@Service
public class InnerMessageServiceImpl implements MessageService,InitializingBean {

	@Override
	public String sendMessage(String messageType) throws Exception {
		MessageTaskTypeService message = MessageTaskTypeServiceStrategyFactory.getMessageTaskTypeService(messageType);
		return message.sendMessage(UserTypeEnum.INNER.getKey());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MessageServiceStrategyFactory.register(UserTypeEnum.INNER.getKey(), this);
	}

}
