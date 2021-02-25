package com.bookee.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bookee.bookee.service.MessageTaskTypeService;

public class MessageTaskTypeServiceStrategyFactory {

	private static Map<String,MessageTaskTypeService> maps = new ConcurrentHashMap<String,MessageTaskTypeService>();
	
	public static MessageTaskTypeService getMessageTaskTypeService(String taskType){
		return maps.get(taskType);
	}
	
	public static void register(String taskType,MessageTaskTypeService service){
		maps.put(taskType, service);
	}
}
