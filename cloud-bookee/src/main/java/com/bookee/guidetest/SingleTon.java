package com.bookee.guidetest;

//双重校验锁实现单例模式
public class SingleTon {
	private volatile static SingleTon instance;
	
	private SingleTon(){
		
	}
	
	public static SingleTon getInstance(){
		if(instance == null){
			synchronized(SingleTon.class){
				if(instance == null){
					instance = new SingleTon();
				}
			}
		}
		return instance;
	}
}
