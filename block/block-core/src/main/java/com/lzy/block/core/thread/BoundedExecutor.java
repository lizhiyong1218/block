package com.lzy.block.core.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 设置信号量的上界设置为线程池的大小加上可排队任务的数量，这是因为信号量需要控制正在执行的
 * 和等待执行的任务数量
 *
 */
public class BoundedExecutor {
	private Semaphore semaphore;
	private Executor executor;
	
	public BoundedExecutor(Executor executor,int bound){
		this.executor=executor;
		this.semaphore=new Semaphore(bound);
	}
	
	public void submitTask(final Runnable command) throws InterruptedException{
		semaphore.acquire();
		try {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						command.run();
					} finally{
						semaphore.release();
					}
				}
			});
		} catch (RejectedExecutionException e) {
			semaphore.release();
		}
		
	}
}
