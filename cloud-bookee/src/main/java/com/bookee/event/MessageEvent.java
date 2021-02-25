package com.bookee.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public MessageEvent(Object source,String message) {
		super(source);
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}
}
