/**  
* @Title: IActivitiHistoryService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年2月4日 下午4:26:50
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;

/**
 * @ClassName: IActivitiHistoryService
 * @Description: 历史信息 
 * @author 李志勇
 * @date 2015年2月4日 下午4:26:50
 *
 */
public interface IActivitiHistoryService {

	/**
	 * 
	 * @Title: findHistoryTasksByProcessInstanceId
	 * @Description: 获取处理过的历史记录
	 * @param processInstanceId 流程实例id
	 * @return: List<HistoricTaskInstance>
	 * @throws
	 */
	List<HistoricTaskInstance> findHistoryTasksByProcessInstanceId(
			String processInstanceId);
	
}
