/**  
* @Title: ActivitiTaskServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月16日 下午1:36:09
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.constant.activiti.ActivitiTaskTypeStatus;
import com.lzy.block.api.vo.activiti.ProcessTaskVo;
import com.lzy.block.core.service.activiti.IActivitiTaskService;

/**
 * @ClassName: ActivitiTaskServiceImpl
 * @Description: TODO 
 * @author 李志勇
 * @date 2015年1月16日 下午1:36:09
 *
 */
public class ActivitiTaskServiceImpl implements IActivitiTaskService {
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private RepositoryService repositoryService;
	

	/* (非 Javadoc)
	 * <p>Title: getTodoTask</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#getTodoTask(java.lang.String)
	 */
	@Override
	public Pagination<ProcessTaskVo> getTodoTask(String userId,int firstResult,int maxResults) {
		
		Pagination<ProcessTaskVo> pagination=new Pagination<ProcessTaskVo>();
		List<ProcessTaskVo> taskList=new ArrayList<ProcessTaskVo>();

        // 已经签收的任务
        List<Task> todoList = taskService.createTaskQuery().taskAssignee(userId).active().listPage(firstResult, maxResults);
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
        List<Task> toClaimList = taskService.createTaskQuery().taskCandidateUser(userId).active().listPage(firstResult, maxResults);
        for (Task task : toClaimList) {
            processDefinitionId = task.getProcessDefinitionId();
            processDefinition = getProcessDefinition(processDefinitionId);
            processTaskVo=packageTaskInfo(task, processDefinition);
            processTaskVo.setTaskType(ActivitiTaskTypeStatus.CANDIDATE.value());
            taskList.add(processTaskVo);
        }

        // 查询被委派的任务
        List<Task> delegationList = taskService.createTaskQuery().taskAssignee("henryyan").taskDelegationState(DelegationState.PENDING).active().listPage(firstResult, maxResults);
        for (Task task : delegationList) {
            processDefinitionId = task.getProcessDefinitionId();
            processDefinition = getProcessDefinition(processDefinitionId);
            processTaskVo=packageTaskInfo(task, processDefinition);
            processTaskVo.setTaskType(ActivitiTaskTypeStatus.DELEGATION.value());
            taskList.add(processTaskVo);
        }
        pagination.setRecordList(taskList);
         
        
		return null;
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
	 private Map<String, Object> packageTaskInfo(SimpleDateFormat sdf, Task task, ProcessDefinition processDefinition) {
	        Map<String, Object> singleTask = new HashMap<String, Object>();
	        singleTask.put("id", task.getId());
	        singleTask.put("name", task.getName());
	        singleTask.put("createTime", sdf.format(task.getCreateTime()));
	        singleTask.put("pdname", processDefinition.getName());
	        singleTask.put("pdversion", processDefinition.getVersion());
	        singleTask.put("pid", task.getProcessInstanceId());
	        return singleTask;
	  }
	 	
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
		processTaskVo.setProcessName(processDefinition.getName());
		processTaskVo.setProcessVersion(processDefinition.getVersion());
		processTaskVo.setProcessInstanceId(task.getProcessInstanceId());
		return processTaskVo;
	}
	
	
	/* (非 Javadoc)
	 * <p>Title: findTaskById</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#findTaskById(java.lang.String)
	 */
	@Override
	public Task findTaskById(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: findHistoryTaskById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#findHistoryTaskById(java.lang.String)
	 */
	@Override
	public HistoricTaskInstance findHistoryTaskById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: findActivitiByTaskId</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#findActivitiByTaskId(java.lang.String)
	 */
	@Override
	public ActivityImpl findActivitiByTaskId(String taskId) {
		// TODO Auto-generated method stub
		return null;
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
	public Task getTaskByBusinessKeyAndProcessDefinitionKey(String businessKey,
			String processDefinitionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: findTaskDefinitionByTaskId</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#findTaskDefinitionByTaskId(java.lang.String)
	 */
	@Override
	public TaskDefinition findTaskDefinitionByTaskId(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: delegateTask</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @param userId
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#delegateTask(java.lang.String, java.lang.String)
	 */
	@Override
	public void delegateTask(String taskId, String userId) {
		// TODO Auto-generated method stub

	}

	/* (非 Javadoc)
	 * <p>Title: resolveTask</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @param variables
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#resolveTask(java.lang.String, java.util.Map)
	 */
	@Override
	public void resolveTask(String taskId, Map<String, Object> variables) {
		// TODO Auto-generated method stub

	}

	/* (非 Javadoc)
	 * <p>Title: setOwnerAndAssignTask</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @param userId
	 * @param delegateUserId
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#setOwnerAndAssignTask(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setOwnerAndAssignTask(String taskId, String userId,
			String delegateUserId) {
		// TODO Auto-generated method stub

	}

	/* (非 Javadoc)
	 * <p>Title: nextTaskDefinition</p>
	 * <p>Description: </p>
	 * @param procInstId
	 * @param conditionExpression
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#nextTaskDefinition(java.lang.String, java.lang.String)
	 */
	@Override
	public TaskDefinition nextTaskDefinition(String procInstId,
			String conditionExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: findHistoryTasksByProcessInstanceId</p>
	 * <p>Description: </p>
	 * @param processInstanceId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#findHistoryTasksByProcessInstanceId(java.lang.String)
	 */
	@Override
	public List<HistoricTaskInstance> findHistoryTasksByProcessInstanceId(
			String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: getVariables</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#getVariables(java.lang.String)
	 */
	@Override
	public Map<String, Object> getVariables(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	 * <p>Title: hasVerifyPermission</p>
	 * <p>Description: </p>
	 * @param taskId
	 * @param userId
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiTaskService#hasVerifyPermission(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasVerifyPermission(String taskId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
