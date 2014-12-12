/**  
* @Title: QuartzJobFactory.java
* @Package com.lzy.block.core.quartz
* @author 李志勇  
* @date 2014年12月12日 下午3:18:43
* @version V1.0  
*/ 
package com.lzy.block.core.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lzy.block.api.model.quartz.ScheduleJob;
 

/**
 * @ClassName: QuartzJobFactory
 * @Description: 定时任务运行工厂 
 * @author 李志勇
 * @date 2014年12月12日 下午3:18:43
 *
 */
public class QuartzJobFactory implements Job {
	 
    public void execute(JobExecutionContext context) throws JobExecutionException {
         
         System.out.println("任务成功运行");
         ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
         System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
         System.out.println(scheduleJob.toString()); 
         //根据name 与 group组成的唯一标识来判别该执行何种操作……
          
    }
 
}
