package com.bookee.bookee.enums;

public enum UserTypeEnum {

	INNER("1","内部人员"),
	SUPPLIER("2","供应商"),
	THIRD("3","第三方");
	
	private String key;
	private String value;
	
	private UserTypeEnum(String key, String value) {
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
		for(UserTypeEnum keyEnum :UserTypeEnum.values()){
			if(keyEnum.getKey().equals(key)){
				return keyEnum.getValue();
			}
		}
		
		return null;
	}
}
