/**  
* @Title: IActivitiProcessInstanceService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月23日 下午1:19:56
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * @ClassName: IActivitiProcessInstanceService
 * @Description: 流程实例service 
 * @author 李志勇
 * @date 2015年1月23日 下午1:19:56
 *
 */
public interface IActivitiProcessInstanceService {
	
	/**
	 * 
	 * @Title: startWorkFlow
	 * @Description: 启动流程
	 * @param processDefinitionKey 流程定义id
	 * @param busnessKey 业务编号
	 * @param userId     用户id
	 * @param variables  参数
	 * @throws Exception
	 *             : void
	 * @throws
	 */
	public ProcessInstance startWorkFlow(String processDefinitionKey, String busnessKey,
			String userId, Map<String, Object> variables)throws Exception;
	
	/**
	 * 
	* @Title: getProcessInstanceById
	* @Description: 通过id获取流程实例
	* @param processInstanceId
	* @return:     
	* ProcessInstance    
	* @throws
	 */
	public ProcessInstance getProcessInstanceById(String processInstanceId);
	
	
}
