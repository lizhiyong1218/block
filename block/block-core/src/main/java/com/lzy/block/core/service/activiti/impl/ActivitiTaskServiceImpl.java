/**  
* @Title: ActivitiTaskServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月16日 下午1:36:09
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.constant.activiti.ActivitiTaskTypeStatus;
import com.lzy.block.api.vo.activiti.ProcessTaskVo;
import com.lzy.block.core.service.activiti.IActivitiTaskService;

/**
 * @ClassName: ActivitiTaskServiceImpl
 * @Description: 任务 
 * @author 李志勇
 * @date 2015年1月16日 下午1:36:09
 *
 */
@Service
public class ActivitiTaskServiceImpl implements IActivitiTaskService {
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	

	/* (非 Javadoc)
	 * <p>Title: getTodoTask</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#getTodoTask(java.lang.String)
	 */
	@Override
	public Pagination<ProcessTaskVo> getTodoTask(String userId,PageModel pageModel) {
		Pagination<ProcessTaskVo> pagination=new Pagination<ProcessTaskVo>();
		
		List<ProcessTaskVo> taskList= getTodoTask(userId);
		List<ProcessTaskVo> list=new ArrayList<ProcessTaskVo>();
		
		int startIndex=(pageModel.getPage()-1)*pageModel.getRows();
		int endIndex=startIndex+pageModel.getRows()-1;
		
		if(startIndex>taskList.size()){
			startIndex=taskList.size();
		}
		
		if(endIndex>taskList.size()){
			endIndex=taskList.size()-1;
		}
		
		for(int i=startIndex;i<=endIndex;i++){
			list.add(taskList.get(i));
		}
        pagination.setRecordList(taskList);
        pagination.setRecordCount(taskList.size());
		return pagination;
	}
	
	@Override
	public List<ProcessTaskVo> getTodoTask(String userId) {
		
		List<ProcessTaskVo> taskList=new ArrayList<ProcessTaskVo>();
        // 已经签收的任务
        List<Task> todoList = taskService.createTaskQuery().taskAssignee(userId).active().list() ;
        String processDefinitionId=null;//流程id
        ProcessDefinition processDefinition=null;//流程对象
        ProcessTaskVo processTaskVo=null;//任务和流程信息
        for (Task task : todoList) {
            processDefinitionId = task.getProcessDefinitionId();
            processDefinition = getProcessDefinition(processDefinitionId);
            processTaskVo=packageTaskInfo(task, processDefinition);
            processTaskVo.setTaskType(ActivitiTaskTypeStatus.ASSIGNEE.value());
            taskList.add(processTaskVo);
        }
         
        // 等待签收的任务
        List<Task> toClaimList = taskService.createTaskQuery().taskCandidateUser(userId).active().list();
        for (Task task : toClaimList) {
            processDefinitionId = task.getProcessDefinitionId();
            processDefinition = getProcessDefinition(processDefinitionId);
            processTaskVo=packageTaskInfo(task, processDefinition);
            processTaskVo.setTaskType(ActivitiTaskTypeStatus.CANDIDATE.value());
            taskList.add(processTaskVo);
        }

        // 查询被委派的任务
        /*List<Task> delegationList = taskService.createTaskQuery().taskAssignee(userId).taskDelegationState(DelegationState.PENDING).active().list();
        for (Task task : delegationList) {
            processDefinitionId = task.getProcessDefinitionId();
            processDefinition = getProcessDefinition(processDefinitionId);
            processTaskVo=packageTaskInfo(task, processDefinition);
            processTaskVo.setTaskType(ActivitiTaskTypeStatus.DELEGATION.value());
            taskList.add(processTaskVo);
        }*/
        
		return taskList;
	}
	
	

	/**
	 * 
	 * @Title: getProcessDefinition
	 * @Description: 根据流程id获取流程对象
	 * @param processDefinitionId
	 * @return: ProcessDefinition
	 * @throws
	 */
	 private ProcessDefinition getProcessDefinition(String processDefinitionId) {
	        /*ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE.get(processDefinitionId);
	        if (processDefinition == null) {
	            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	            PROCESS_DEFINITION_CACHE.put(processDefinitionId, processDefinition);
	        }*/
		 	ProcessDefinition processDefinition =repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	        return processDefinition;
	 }
	 
	/**
	 * 
	 * @Title: packageTaskInfo
	 * @Description: 封装任务和流程信息
	 * @param sdf
	 * @param task
	 * @param processDefinition
	 * @return: Map<String,Object>
	 * @throws
	 */
	/* private Map<String, Object> packageTaskInfo(SimpleDateFormat sdf, Task task, ProcessDefinition processDefinition) {
	        Map<String, Object> singleTask = new HashMap<String, Object>();
	        singleTask.put("id", task.getId());
	        singleTask.put("name", task.getName());
	        singleTask.put("createTime", sdf.format(task.getCreateTime()));
	        singleTask.put("pdname", processDefinition.getName());
	        singleTask.put("pdversion", processDefinition.getVersion());
	        singleTask.put("pid", task.getProcessInstanceId());
	        return singleTask;
	  }*/
	 	
	/**
	 * 
	 * @Title: packageTaskInfo
	 * @Description: 组织任务和流程信息
	 * @param task
	 * @param processDefinition
	 * @return: ProcessTaskVo
	 * @throws
	 */
	private ProcessTaskVo packageTaskInfo(Task task,ProcessDefinition processDefinition) {
		ProcessTaskVo processTaskVo = new ProcessTaskVo();
		processTaskVo.setTaskId(task.getId());
		processTaskVo.setTaskName(task.getName());
		processTaskVo.setCreateTime(task.getCreateTime());
		processTaskVo.setDueDate(task.getDueDate());
		processTaskVo.setTaskOwner(task.getOwner());
		processTaskVo.setTaskAssignee(task.getAssignee());
		processTaskVo.setTaskDesc(task.getDescription());
		processTaskVo.setProcessDefId(processDefinition.getId());
		processTaskVo.setProcessName(processDefinition.getName());
		processTaskVo.setProcessVersion(processDefinition.getVersion());
		processTaskVo.setProcessInstanceId(task.getProcessInstanceId());
		processTaskVo.setDiagramResourceName(processDefinition.getDiagramResourceName());
		return processTaskVo;
	}
	
	
	/* (非 Javadoc)
	 * <p>Title: getTaskByBusinessKeyAndProcessDefinitionKey</p>
	 * <p>Description: </p>
	 * @param businessKey
	 * @param processDefinitionKey
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#getTaskByBusinessKeyAndProcessDefinitionKey(java.lang.String, java.lang.String)
	 */
	@Override
	public Task getCurrentTaskByBusKeyAndProDefKey(String businessKey,
			String processDefinitionKey) {
		Execution execution = runtimeService.createExecutionQuery()
				.processDefinitionKey(processDefinitionKey)
				.processInstanceBusinessKey(businessKey).singleResult();
		if (execution == null) {
			return null;
		}
		Task task = taskService.createTaskQuery()
				.executionId(execution.getId()).singleResult();
		return task;
	}
	
	@Override
	public void claim(String taskId, String userId) {
		 taskService.claim(taskId, userId);
	}
	
	@Override
	public void completeTask(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}

	@Override
	public Task getTaskById(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}
	
	 


	

}
