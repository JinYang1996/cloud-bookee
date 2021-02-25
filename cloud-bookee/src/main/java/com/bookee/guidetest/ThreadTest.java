package com.bookee.guidetest;

public class ThreadTest {

	public static void main(String[] args) {
		Thread t = new Thread(){
			public void run(){
				pong();
			}
		};
		t.start();
		System.out.println("ping");
	}

	static void pong(){
		System.out.println("pong");
	}
}
