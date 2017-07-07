package com.lzy.block.core.thread.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 向线程池里面提交多个任务，完成一个任务就立刻获取一个任务的结果
 * 
 * 
 * 1.并发下载图片
 * 2.下载完一张图片之后立刻显示下载完的那张，不用等待所有图片都下载完
 *
 */
public class Renderder {
	
	private final ExecutorService executor=Executors.newFixedThreadPool(10);//线程池
	
	public void render(){
		List<String> info=new ArrayList<String>();//找出所有图片地址
		for (final String img : info) {//提交任务到线程池，异步执行任务
			executor.submit(new Callable<ImgData>() {
				@Override
				public ImgData call() throws Exception {//执行下载图片功能，并且返回图片
					System.out.println("文件路径:"+img);
					ImgData d=null;//下载文件
					return d;
				}
			});
		}
		
		CompletionService<ImgData> completionService=new ExecutorCompletionService<ImgData>(executor);
		
		try {
			//从completionService对象的队列里面取结果，如果有结果take方法就会返回线程池里面返回的对象
			for(int t=0,n=info.size();t<n;t++){//总共有info.size个图片，所以取那么多次
				Future<ImgData> f = completionService.take();
				ImgData imgData = f.get();
				System.err.println("渲染图片："+imgData);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	class ImgData{
		
	}
	
}
