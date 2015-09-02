/**  
* @Title: ThreadWait.java
* @Package com.lzy.block.core.thread
* @author 李志勇  
* @date 2015年9月2日 下午6:03:32
* @version V1.0  
*/ 
package com.lzy.block.core.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName: ThreadWait
 * @Description: 线程超时 
 * @author 李志勇
 * @date 2015年9月2日 下午6:03:32
 *
 */
public class ThreadWait {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService exec = Executors.newFixedThreadPool(1);

        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(1000 * 5);
                return "线程执行完成.";
            }
        };

        try {
            Future<String> future = exec.submit(call);
            String obj = future.get(4 * 1000, TimeUnit.MILLISECONDS); // 任务处理超时时间设置
            System.out.println("任务成功返回:" + obj);
        } catch (TimeoutException ex) {
            System.out.println("处理超时啦....");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("处理失败.");
            e.printStackTrace();
        }
        // 关闭线程池
        exec.shutdown();
        
        System.out.println("完毕");
    }
}
