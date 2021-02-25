package com.bookee.guidetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

	private static final int CORE_POOL_SIZE = 5;
	private static final int MAXINUM_POOL_SIZE = 10;
	private static final int QUEUE_CAPACITY = 100;
	private static final long KEEP_ALIVE_TIME = 1L;
	
	public static void main(String[] args){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAXINUM_POOL_SIZE,
				KEEP_ALIVE_TIME,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY),
				new ThreadPoolExecutor.DiscardPolicy());
		
		for(int i=0;i<10;i++){
			Runnable runner = new MyRunnable(""+i);
			executor.execute(runner);
		}
		//终止线程
		executor.shutdown();
		while(!executor.isTerminated()){
			
		}
		System.out.println("Finished All threads");
	}
}
