/**  
* @Title: ProcessTaskDetailVo.java
* @Package com.lzy.block.api.vo.activiti
* @author 李志勇  
* @date 2015年2月4日 下午6:00:03
* @version V1.0  
*/ 
package com.lzy.block.api.vo.activiti;

import java.util.Date;


/**
 * @ClassName: ProcessTaskDetailVo
 * @Description: 任务详情vo 
 * @author 李志勇
 * @date 2015年2月4日 下午6:00:03
 *
 */
public class ProcessTaskDetailVo {
	/*流程启动人*/
	private String processStartBy;
	/*流程启动时间*/
	private Date processStartTime;
	/*任务名称*/
	private String taskName;
	/*指派人*/
	private String assignee;
	/*业务编号*/
	private String businessKey;
	/*流程实例id*/
	private String processInstanceId;
	/*表单key,保存业务的路径，如/WEB-INF/page/rma/rmaVerify.jsp*/
	private String formKey;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessStartBy() {
		return processStartBy;
	}

	public void setProcessStartBy(String processStartBy) {
		this.processStartBy = processStartBy;
	}

	public Date getProcessStartTime() {
		return processStartTime;
	}

	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}

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
	
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	@Override
	public String toString() {
		return "ProcessTaskDetailVo [processStartBy=" + processStartBy
				+ ", processStartTime=" + processStartTime + ", taskName="
				+ taskName + ", assignee=" + assignee + "]";
	}
	
	
}
