package com.bookee.bookee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bookee.bookee.enums.MessageEnum;
import com.bookee.bookee.enums.UserTypeEnum;
import com.bookee.factory.MessageTaskTypeServiceStrategyFactory;

@Service
public class AccessoryMessageServiceImpl implements MessageTaskTypeService,InitializingBean {

	@Override
	public String sendMessage(String userType) throws Exception {
		if(StringUtils.isBlank(userType)){
			throw new Exception("用户类型为空");
		}
		return UserTypeEnum.getValueByKey(userType)+MessageEnum.ACCESSORY.getValue()+"消息推送";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MessageTaskTypeServiceStrategyFactory.register(MessageEnum.ACCESSORY.getKey(), this);
	}

}
