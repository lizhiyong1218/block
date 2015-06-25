
/**     
 * @FileName: taskController.java   
 * @Description: 同步数据  
 * @author: ZhiYong.Li    
 * @date:2015年5月21日   
 */

package com.lzy.block.console.controller.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.api.constant.common.ResultStatus;
import com.lzy.block.api.model.quartz.ScheduleJob;
import com.lzy.block.api.vo.ResultVo;



/**       
 * @author: ZhiYong.Li  
 * @date:2015年5月21日     
 */
@Controller
@RequestMapping("/task")
public class TaskController {
	
	private static Logger logger=Logger.getLogger(TaskController.class.getName());
	 
	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
	
	/**
	 * 
	* @Title: taskList
	* @Description: 计划任务列表
	* @return:     
	* Map<String,Object>    
	* @throws
	 */
	@RequestMapping(value="taskList")
	@ResponseBody
	public Map<String, Object> taskList(){
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler
						.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					ScheduleJob job = new ScheduleJob();
					job.setJobName(jobKey.getName());
					job.setJobGroup(jobKey.getGroup());
					job.setDesc("触发器:" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler
							.getTriggerState(trigger.getKey());
					job.setJobStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setCronExpression(cronExpression);
					}
					jobList.add(job);
				}
			}
		} catch (SchedulerException e) {
			logger.error("查询任务异常", e);
		}
	 
		
		ModelMap map=new ModelMap();
		map.put("rows", jobList);  
		map.put("total", jobList.size()); 
		return map;
	}

	/**
	 * 
	* @Title: currentTaskList
	* @Description: 正在执行的任务
	* @return:     
	* Map<String,Object>    
	* @throws
	 */
	@RequestMapping(value="currentTaskList")
	@ResponseBody
	public Map<String, Object> currentTaskList(){
		List<ScheduleJob> jobList=new ArrayList<ScheduleJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<ScheduleJob>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
			    ScheduleJob job = new ScheduleJob();
			    JobDetail jobDetail = executingJob.getJobDetail();
			    JobKey jobKey = jobDetail.getKey();
			    Trigger trigger = executingJob.getTrigger();
			    job.setJobName(jobKey.getName());
			    job.setJobGroup(jobKey.getGroup());
			    job.setDesc("触发器:" + trigger.getKey());
			    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			    job.setJobStatus(triggerState.name());
			    if (trigger instanceof CronTrigger) {
			        CronTrigger cronTrigger = (CronTrigger) trigger;
			        String cronExpression = cronTrigger.getCronExpression();
			        job.setCronExpression(cronExpression);
			    }
			    jobList.add(job);
			}
		} catch (SchedulerException e) {
			logger.error("查询任务异常", e);
		}
		
		ModelMap map=new ModelMap();
		map.put("rows", jobList);  
		map.put("total", jobList.size()); 
		return map;
	}
	
	/**
	 * 
	* @Title: execute
	* @Description: 立即执行
	* @param scheduleJob
	* @return:     
	* ResultVo    
	* @throws
	 */
	@RequestMapping(value="execute")
	@ResponseBody
	public ResultVo execute(ScheduleJob scheduleJob){
		logger.info("手动执行"+scheduleJob);
		ResultVo res=new ResultVo();
		res.setStatus(ResultStatus.FAILURE.value());
		res.setMessage("操作失败");
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.triggerJob(jobKey);
			res.setStatus(ResultStatus.SUCCESS.value());
			res.setMessage("操作成功");
		} catch (SchedulerException e) {
			logger.error("执行失败",e);
		}
		return res;
	}
	
	/**
	 * 
	* @Title: pause
	* @Description: 暂停
	* @param scheduleJob
	* @return:     
	* ResultVo    
	* @throws
	 */
	@RequestMapping(value="pause")
	@ResponseBody
	public ResultVo pause(ScheduleJob scheduleJob){
		logger.info("手动暂停"+scheduleJob);
		ResultVo res=new ResultVo();
		res.setStatus(ResultStatus.FAILURE.value());
		res.setMessage("操作失败");
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.pauseJob(jobKey);
			res.setStatus(ResultStatus.SUCCESS.value());
			res.setMessage("操作成功");
		} catch (SchedulerException e) {
			logger.error("暂停失败",e);
		}
		return res;
	}

	/**
	 * 
	* @Title: resume
	* @Description: 恢复
	* @param scheduleJob
	* @return:     
	* ResultVo    
	* @throws
	 */
	@RequestMapping(value="resume")
	@ResponseBody
	public ResultVo resume(ScheduleJob scheduleJob){
		logger.info("恢复任务"+scheduleJob);
		ResultVo res=new ResultVo();
		res.setStatus(ResultStatus.FAILURE.value());
		res.setMessage("操作失败");
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.resumeJob(jobKey);
			res.setStatus(ResultStatus.SUCCESS.value());
			res.setMessage("操作成功");
		} catch (SchedulerException e) {
			logger.error("恢复失败",e);
		}
		return res;
	}

	/**
	 * 
	* @Title: delete
	* @Description: 删除
	* @param scheduleJob
	* @return:     
	* ResultVo    
	* @throws
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public ResultVo delete(ScheduleJob scheduleJob){
		logger.info("删除任务"+scheduleJob);
		ResultVo res=new ResultVo();
		res.setStatus(ResultStatus.FAILURE.value());
		res.setMessage("操作失败");
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.deleteJob(jobKey);
			res.setStatus(ResultStatus.SUCCESS.value());
			res.setMessage("操作成功");
		} catch (SchedulerException e) {
			logger.error("删除失败",e);
		}
		return res;
	}
	
}
