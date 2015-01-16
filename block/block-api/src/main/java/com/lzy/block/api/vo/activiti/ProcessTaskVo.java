/**  
* @Title: ProcessTaskInfo.java
* @Package com.lzy.block.api.vo.activiti
* @author 李志勇  
* @date 2015年1月16日 下午2:25:30
* @version V1.0  
*/ 
package com.lzy.block.api.vo.activiti;

import java.util.Date;

/**
 * @ClassName: ProcessTaskInfo
 * @Description: 任务流程组合vo 
 * @author 李志勇
 * @date 2015年1月16日 下午2:25:30
 *
 */
public class ProcessTaskVo {
	/*任务id*/
	private String taskId;
	/*任务名称*/
	private String taskName;
	/*创建时间*/
	private Date createTime;
	/*流程名称*/
	private String processName;
	/*流程版本*/
	private int processVersion;
	/*流程实例ID(一个工作流可以启动多个实例)*/
	private String processInstanceId;
	/*任务类型*/
	private String taskType;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	 
	public int getProcessVersion() {
		return processVersion;
	}
	public void setProcessVersion(int processVersion) {
		this.processVersion = processVersion;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	@Override
	public String toString() {
		return "ProcessTaskVo [taskId=" + taskId + ", taskName=" + taskName
				+ ", createTime=" + createTime + ", processName=" + processName
				+ ", processVersion=" + processVersion + ", processInstanceId="
				+ processInstanceId + ", taskType=" + taskType + "]";
	}
	
}
