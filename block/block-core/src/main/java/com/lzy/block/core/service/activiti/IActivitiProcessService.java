/**  
* @Title: IActivitiProcessService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月16日 上午11:11:40
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.vo.activiti.ProcessDefInfo;


/**
 * @ClassName: IActivitiProcessService
 * @Description: 工作流流程service 
 * @author 李志勇
 * @date 2015年1月16日 上午11:11:40
 *
 */
public interface IActivitiProcessService {
	
	/**
	 * 
	* @Title: getMyStartProcess
	* @Description: 我启动的流程
	* @param userName
	* @return:     
	* Pagination<ProcessDefInfo>    
	* @throws
	 */
	public Pagination<ProcessDefInfo> getMyStartProcess(String userName,PageModel pageModel)throws Exception;
	
	/**
	 * 
	 * @Title: getMyJoinProcess
	 * @Description: 我参与的流程
	 * @param userName
	 * @param pageModel
	 * @return
	 * @throws Exception
	 *             : Pagination<ProcessDefInfo>
	 * @throws
	 */
	public Pagination<ProcessDefInfo> getMyJoinProcess(String userName,PageModel pageModel)throws Exception;
	
	/**
	 * 
	 * @Title: getMyJoinFinishedProcess
	 * @Description: 我参与的已经结束的流程
	 * @param userName
	 * @param pageModel
	 * @return
	 * @throws Exception
	 *             : Pagination<ProcessDefInfo>
	 * @throws
	 */
	public Pagination<ProcessDefInfo> getMyJoinFinishedProcess(String userName,PageModel pageModel)throws Exception;
	
	/**
	 * 
	 * @Title: getProcessById
	 * @Description: 根据流程id获取流程对象
	 * @param processDefinitionId
	 * @return: ProcessDefinition
	 * @throws
	 */
	public ProcessDefinition getProcessById(String processDefinitionId)throws Exception;
	
	/**
	 * 
	 * @Title: getProcessInstanceById
	 * @Description: 根据流程实例Id获取流程实例对象
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 *             : ProcessInstance
	 * @throws
	 */
	public ProcessInstance getProcessInstanceById(String processInstanceId)throws Exception;

	/**
	 * 
	 * @Title: getCurrentActiviti
	 * @Description: 获取流程当前节点
	 * @param processDefId 工作流id
	 * @param activitiId  当前节点id
	 * @return: ActivityImpl
	 * @throws
	 */
	public ActivityImpl getCurrentActiviti(String processDefId,String activitiId);
	
	
}
