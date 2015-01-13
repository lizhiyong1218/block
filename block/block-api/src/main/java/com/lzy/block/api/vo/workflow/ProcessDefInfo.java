package com.lzy.block.api.vo.workflow;

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
	private String startFormKey;
	
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

	public String getStartFormKey() {
		return startFormKey;
	}

	public void setStartFormKey(String startFormKey) {
		this.startFormKey = startFormKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	@Override
	public String toString() {
		return "ProcessDefInfo [id=" + id + ", name=" + name + ", category="
				+ category + ", version=" + version + ", resourceName="
				+ resourceName + ", diagramResourceName=" + diagramResourceName
				+ ", deployTime=" + deployTime + ", deploymentId=" + deploymentId
				+ ", startFormKey=" + startFormKey + "]";
	}

	
}
