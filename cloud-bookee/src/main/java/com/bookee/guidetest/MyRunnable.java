package com.bookee.guidetest;

import java.util.Date;

public class MyRunnable implements Runnable{

	private String command;
	
	public MyRunnable(String s){
		this.command = s;
	}
	
	@Override
	public void run() {
		System.out.println("enter 当前线程："+Thread.currentThread()+" 时间为："+new Date());
		processCommand();
		System.out.println("exit 当前线程："+Thread.currentThread()+" 时间为："+new Date());
	}
	
	private void processCommand(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString(){
		return command;
		
	}
}
