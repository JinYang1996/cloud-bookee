package com.bookee.bookee.service;

/**
 * 消息推送业务层
 * @author jy
 *
 */
public interface MessageService {
	
	public String sendMessage(String messageType) throws Exception;

}
