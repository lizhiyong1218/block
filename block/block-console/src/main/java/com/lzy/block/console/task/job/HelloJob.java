/**  
* @Title: HelloJob.java
* @Package com.lzy.block.console.task
* @author 李志勇  
* @date 2014年12月12日 下午2:34:33
* @version V1.0  
*/ 
package com.lzy.block.console.task.job;
import java.util.Date;  

import org.quartz.Job;  
import org.quartz.JobExecutionContext;  
/**
 * @ClassName: HelloJob
 * @Description: 定时任务 
 * @author 李志勇
 * @date 2014年12月12日 下午2:34:33
 *
 */
public class HelloJob implements Job {  
	  
    /** 
     * 实现你自己的定时方法 ,至于里面写什么,您说了蒜!<br> 
     * 这里只输出 HelloWorld! 
     */  
    @Override  
    public void execute(JobExecutionContext context){  
        // 输出 HelloWorld !  
        System.out.println("Hello World! - " + new Date());  
    }  
  
}
