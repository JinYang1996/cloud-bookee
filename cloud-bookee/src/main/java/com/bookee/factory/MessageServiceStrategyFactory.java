package com.bookee.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.bookee.bookee.service.MessageService;

public class MessageServiceStrategyFactory {
	
	private static Map<String,MessageService> maps = new ConcurrentHashMap<String,MessageService>();
	
	public static MessageService getMessageByUserType(String userType){
		return maps.get(userType);
	}
	
	public static void register(String userType,MessageService service) throws Exception{
		if(StringUtils.isBlank(userType)){
			throw new Exception("用户类型不能空");
		}
		maps.put(userType, service);
	}
}
