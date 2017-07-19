package com.lzy.block.core.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.lzy.block.api.model.newhouse.NewHouse;

public class NewHouseSyncTest {
	
	private static BlockingQueue<NewHouse> blockingQueue=new LinkedBlockingQueue<NewHouse>();
	private static ExecutorService threadPool = Executors.newCachedThreadPool();//可回收线程池
	
	public static void deal(){
		List<NewHouse> all=new ArrayList<NewHouse>();//从数据库查询
		NewHouse n=null;
		for(int i=0;i<100;i++){
			n=new NewHouse();
			n.setId(i);
			n.setDecoration("描述:"+i);
			all.add(n);
		}
		blockingQueue.addAll(all);//将查询到的结果放到同步队列
		
		for(int i=0;i<4;i++){
			syncNewhouseRun task=new syncNewhouseRun(blockingQueue);
			threadPool.execute(task);
		}
//		newFixedThreadPool.shutdown();//不再接收任务，执行完任务之后关闭
//		newFixedThreadPool.shutdownNow();//取消所有遗留的任务然后关闭
	}
	
	public static void main(String[] args) {
		deal();
	}
	
}

class syncNewhouseRun implements Runnable{
	
	private int count;

	private BlockingQueue<NewHouse> blockingQueue;
	
	public syncNewhouseRun(BlockingQueue<NewHouse> blockingQueue){
		this.blockingQueue=blockingQueue;
	}
	
	@Override
	public void run() {
		try {
			while(blockingQueue.size()>0){
				NewHouse newHouse=blockingQueue.take();
//				newhouseservice.update(newhouse);
				System.out.println(Thread.currentThread().getName()+"处理:"+newHouse.getDecoration());
				count++;
				Thread.sleep(100);
			}
			System.out.println(Thread.currentThread().getName()+"总共处理:"+count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
