/**  
* @Title: ProcessTaskInfo.java
* @Package com.lzy.block.api.vo.activiti
* @author 李志勇  
* @date 2015年1月16日 下午2:25:30
* @version V1.0  
*/ 
package com.lzy.block.api.vo.activiti;

import java.io.Serializable;
import java.util.Date;

import com.lzy.block.api.constant.activiti.ActivitiTaskTypeStatus;

/**
 * @ClassName: ProcessTaskInfo
 * @Description: 任务流程组合vo 
 * @author 李志勇
 * @date 2015年1月16日 下午2:25:30
 *
 */
public class ProcessTaskVo implements Serializable{
	private static final long serialVersionUID = -6089163224634486420L;
	
	/*任务id*/
	private String taskId;
	/*任务名称*/
	private String taskName;
	/*优先级*/
	private int taskPriority;
	/*创建时间*/
	private Date createTime;
	/*过期时间*/
	private Date dueDate;
	/*所属人*/
	private String taskOwner;
	/*指派人*/
	private String taskAssignee;
	/*任务描述*/
	private String taskDesc;
	/*流程id*/
	private String processDefId;
	/*流程名称*/
	private String processName;
	/*流程版本*/
	private int processVersion;
	/*流程实例ID(一个工作流可以启动多个实例)*/
	private String processInstanceId;
	/*流程图*/
	private String diagramResourceName;
	/*任务类型*/
	private String taskType;
	/*任务类型中文显示*/
	private String taskTypeFormatter;
 
	
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
		setTaskTypeFormatter(ActivitiTaskTypeStatus.getZhName(taskType));
	}
	
	public String getTaskTypeFormatter() {
		return taskTypeFormatter;
	}
	public void setTaskTypeFormatter(String taskTypeFormatter) {
		this.taskTypeFormatter = taskTypeFormatter;
	}
	
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	
	public String getTaskOwner() {
		return taskOwner;
	}
	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	public String getTaskAssignee() {
		return taskAssignee;
	}
	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public int getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}
	
	public String getProcessDefId() {
		return processDefId;
	}
	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}
	@Override
	public String toString() {
		return "ProcessTaskVo [taskId=" + taskId + ", taskName=" + taskName
				+ ", taskPriority=" + taskPriority + ", createTime="
				+ createTime + ", dueDate=" + dueDate + ", taskOwner="
				+ taskOwner + ", taskAssignee=" + taskAssignee
				+ ", processDefId=" + processDefId + ", processName="
				+ processName + ", processVersion=" + processVersion
				+ ", processInstanceId=" + processInstanceId
				+ ", diagramResourceName=" + diagramResourceName
				+ ", taskType=" + taskType + ", taskTypeFormatter="
				+ taskTypeFormatter + "]";
	}
	 
}
