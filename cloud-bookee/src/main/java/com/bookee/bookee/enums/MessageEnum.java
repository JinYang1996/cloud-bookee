package com.bookee.bookee.enums;

public enum MessageEnum {

	ACCESSORY("accessory","入库"),
	INQUIRY("inquiry","询件"),
	ORDER("order","订单");
	
	private String key;
	private String value;
	private MessageEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByKey(String key){
		for(MessageEnum keyEnum :MessageEnum.values()){
			if(keyEnum.getKey().equals(key)){
				return keyEnum.getValue();
			}
		}
		
		return null;
	}
}
