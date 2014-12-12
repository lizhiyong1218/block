/**  
* @Title: SimpleJob.java
* @Package com.lzy.block.console.task.job
* @author 李志勇  
* @date 2014年12月12日 下午2:39:45
* @version V1.0  
*/ 
package com.lzy.block.console.task.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName: SimpleJob
 * @Description: SimpleJob 
 * @author 李志勇
 * @date 2014年12月12日 下午2:39:45
 *
 */
public class SimpleJob implements Job {  
	  
    @Override  
    public void execute(JobExecutionContext context)  
            throws JobExecutionException {  
        // job 的名字  
        String jobName = context.getJobDetail().getKey().getName();  
          
        // 任务执行的时间  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");  
        String jobRunTime = dateFormat.format(Calendar.getInstance().getTime());  
          
        // 输出任务执行情况  
        System.out.println("任务 : " + jobName + " 在  " +jobRunTime + " 执行了 ");  
    }  
}  
