package com.lzy.block.api.vo.activiti;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ProcessDefInfo
* @Description: 流程部署信息 
* @author 李志勇
* @date 2015年1月9日 上午10:22:04
*
 */
public class ProcessDefInfo implements Serializable {
	/**
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -873639756188989912L;
	
	/*流程id*/
	private String id;
	/*名称*/
	private String name;
	/*类型*/
	private String category;
	/*版本*/
	private int version;
	/*xml名称*/
	private String resourceName;
	/*图片*/
	private String diagramResourceName;
	/*部署时间*/
	private Date deployTime;
	/*部署id*/
	private String deploymentId;
	/*流程key*/
	private String processKey;
	
	/*流程发起人*/
	private String startUser;
	/*开始时间*/
	private Date startTime;
	/*结束时间*/
	private Date endTime;
	/*当前节点名称*/
	private String currentNode;
	/*当前节点id*/
	private String activitiId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	 

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	
	public String getStartUser() {
		return startUser;
	}

	public void setStartUser(String startUser) {
		this.startUser = startUser;
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

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	public String getActivitiId() {
		return activitiId;
	}

	public void setActivitiId(String activitiId) {
		this.activitiId = activitiId;
	}

	@Override
	public String toString() {
		return "ProcessDefInfo [id=" + id + ", name=" + name + ", category="
				+ category + ", version=" + version + ", resourceName="
				+ resourceName + ", diagramResourceName=" + diagramResourceName
				+ ", deployTime=" + deployTime + ", deploymentId="
				+ deploymentId + ", processKey=" + processKey + ", startUser="
				+ startUser + ", startTime=" + startTime + ", endTime="
				+ endTime + ", currentNode=" + currentNode + ", activitiId="
				+ activitiId + "]";
	}
	
}
