/**  
* @Title: ActivitiHistoryServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年2月4日 下午4:27:18
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.stereotype.Service;

import com.lzy.block.core.service.activiti.IActivitiHistoryService;

/**
 * @ClassName: ActivitiHistoryServiceImpl
 * @Description: 流程历史信息 
 * @author 李志勇
 * @date 2015年2月4日 下午4:27:18
 *
 */
@Service
public class ActivitiHistoryServiceImpl implements IActivitiHistoryService {
	@Resource
	private HistoryService historyService;

	
	@Override
	public List<HistoricTaskInstance> findHistoryTasksByProcessInstanceId(
			String processInstanceId) {
		return historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).orderByTaskDueDate()
				.asc().list();
	}
}
