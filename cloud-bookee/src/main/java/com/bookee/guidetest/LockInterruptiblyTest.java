package com.bookee.guidetest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {


	static Lock lock = new ReentrantLock();
	
	static class InnerClass implements Runnable{

		Lock lock;
		public InnerClass(Lock lock){
			this.lock = lock;
		}
		
		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName()+"is running");
				lock.lockInterruptibly();
				System.out.println(Thread.currentThread().getName()+"is over");
			} catch (Exception e) {
				System.out.println("haha");
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
		}
		
	}
	
	public static void main(String[] args){
		InnerClass innerClass = new InnerClass(lock);
		Thread thread = new Thread(innerClass);
		Thread thread2 = new Thread(){
			public void run(){
				System.out.println(Thread.currentThread().getName()+"     is running");
				System.out.println("5"+lock.tryLock());
				System.out.println(Thread.currentThread().getName()+"      is over");
			}
		};
		
		try {
			lock.lock();
			thread.start();
			System.out.println(1);
			Thread.sleep(3000);
//			thread.interrupt();
			System.out.println(2);
			Thread.sleep(3000);

			
		} catch (InterruptedException e) {
			System.out.println("eeeeeeeeeee");
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread2.start();
	}
}
