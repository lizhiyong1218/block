/**  
* @Title: ProcessHistoryVo.java
* @Package com.lzy.block.api.vo.activiti
* @author 李志勇  
* @date 2015年2月4日 下午4:37:37
* @version V1.0  
*/ 
package com.lzy.block.api.vo.activiti;

import java.util.Date;

/**
 * @ClassName: ProcessHistoryVo
 * @Description: 流程历史vo 
 * @author 李志勇
 * @date 2015年2月4日 下午4:37:37
 *
 */
public class ProcessHistoryVo {

	/*任务名称*/
	private String taskName;
	/*处理人*/
	private String assignee;
	/*任务开始时间*/
	private Date startTime;
	/*任务结束时间*/
	private Date endTime;
	/*任务拥有人*/
	private String owner;
	/*任务描述*/
	private String description;
	
	
//	private String businessId;
//	private String taskId;
//	private String taskKey;
//	private String processDefId;
//	private String processDefKey;
//	private String processInstanceId;
//	private int priority;
//	private Date deadLine;
//	private String executionId;
//	private String formKey;
//	private String processName;
//	private boolean isSuspended;
//	private String verifyUrl;
//	private String verifyInfo;
//	private String stratUser;
//
//	private Date claimTime;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ProcessHistoryVo [taskName=" + taskName + ", assignee="
				+ assignee + ", startTime=" + startTime + ", endTime="
				+ endTime + ", owner=" + owner + ", description=" + description
				+ "]";
	}
	 
	
}
