/**  
* @Title: ScheduleJob.java
* @Package com.lzy.block.api.model.task
* @author 李志勇  
* @date 2014年12月12日 下午3:15:39
* @version V1.0  
*/ 
package com.lzy.block.api.model.quartz;

import java.io.Serializable;

/**
 * @ClassName: ScheduleJob
 * @Description: 任务 
 * @author 李志勇
 * @date 2014年12月12日 下午3:15:39
 *
 */
public class ScheduleJob implements Serializable{
	
	private static final long serialVersionUID = 2004073420081986979L;

	/** 任务id */
    private String jobId;
      
    /** 任务名称 */
    private String jobName;
      
    /** 任务分组 */
    private String jobGroup;
      
    /** 任务状态 0禁用 1启用 2删除*/
    private String jobStatus;
      
    /** 任务运行时间表达式 */
    private String cronExpression;
      
    /** 任务描述 */
    private String desc;
 
    public ScheduleJob() {
        super();
    }
 
    public ScheduleJob(String jobId, String jobName, String jobGroup,
            String jobStatus, String cronExpression, String desc) {
        super();
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobStatus = jobStatus;
        this.cronExpression = cronExpression;
        this.desc = desc;
    }
 
    public String getJobId() {
        return jobId;
    }
 
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
 
    public String getJobName() {
        return jobName;
    }
 
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
 
    public String getJobGroup() {
        return jobGroup;
    }
 
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
 
    public String getJobStatus() {
        return jobStatus;
    }
 
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
 
    public String getCronExpression() {
        return cronExpression;
    }
 
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
 
    public String getDesc() {
        return desc;
    }
 
    public void setDesc(String desc) {
        this.desc = desc;
    }

	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName
				+ ", jobGroup=" + jobGroup + ", jobStatus=" + jobStatus
				+ ", cronExpression=" + cronExpression + ", desc=" + desc + "]";
	}
    
}
