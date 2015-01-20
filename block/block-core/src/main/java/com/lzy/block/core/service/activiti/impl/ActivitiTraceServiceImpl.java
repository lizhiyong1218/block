/**  
* @Title: ActivitiTraceServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月20日 上午11:30:28
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.block.core.service.activiti.IActivitiTraceService;




/**
 * @ClassName: ActivitiTraceServiceImpl
 * @Description: 流程路线 
 * @author 李志勇
 * @date 2015年1月20日 上午11:30:28
 *
 */
@Service
public class ActivitiTraceServiceImpl implements IActivitiTraceService {
	
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	IdentityService identityService;
	
	private static Logger logger = Logger.getLogger(ActivitiTraceServiceImpl.class.getName());
	
	@Override
	public List<Map<String, Object>> traceProcess(String processInstanceId) {
		Execution execution = runtimeService.createExecutionQuery()
				.executionId(processInstanceId).singleResult();// 执行实例
		Object property;
		String activityId = "";
		try {
			property = PropertyUtils.getProperty(execution, "activityId");
			if (property != null) {
				activityId = property.toString();
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccess: " + e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error("InvocationTarget: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error("NoSuchMethod: " + e.getMessage());
		}
		
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processInstance
						.getProcessDefinitionId());
		List<ActivityImpl> activitiList = processDefinition.getActivities();// 获得当前任务的所有节点
		
		List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
		Map<String, TaskDefinition> taskDefinitions = processDefinition.getTaskDefinitions();
		for (ActivityImpl activity : activitiList) {

			boolean currentActiviti = false;
			String id = activity.getId();
			
			// 当前节点
			if (id.equals(activityId)) {
				currentActiviti = true;
			}

			Map<String, Object> activityImageInfo;
			try {
				activityImageInfo = packageSingleActivitiInfo(activity,processInstance, currentActiviti);
				TaskDefinition taskDefinition = taskDefinitions.get(id);
				String taskName = null;
				if(taskDefinition!=null){
					taskName = taskDefinition.getNameExpression().getExpressionText();
					activityImageInfo.put("taskName", taskName);
				}
				activityInfos.add(activityImageInfo);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return activityInfos;
	}
	
	
	/**
	 * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 * 
	 * @param activity
	 * @param processInstance
	 * @param currentActiviti
	 * @return
	 */
	private Map<String, Object> packageSingleActivitiInfo(
			ActivityImpl activity, ProcessInstance processInstance,
			boolean currentActiviti) throws Exception {
		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> activityInfo = new HashMap<String, Object>();
		activityInfo.put("currentActiviti", currentActiviti);
		setPosition(activity, activityInfo);
		setWidthAndHeight(activity, activityInfo);
		
		Map<String, Object> properties = activity.getProperties();
		vars.put("任务类型",properties.get("type"));
		activityInfo.put("taskType",properties.get("type").toString());
		
		ActivityBehavior activityBehavior = activity.getActivityBehavior();
		logger.debug(activityBehavior);
		if (activityBehavior instanceof UserTaskActivityBehavior) {

			Task currentTask = null;

			/*
			 * 当前节点的task
			 */
			if (currentActiviti) {
				currentTask = getCurrentTaskInfo(processInstance);
			}

			/*
			 * 当前任务的分配角色
			 */
			UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
			TaskDefinition taskDefinition = userTaskActivityBehavior
					.getTaskDefinition();
			Set<Expression> candidateGroupIdExpressions = taskDefinition
					.getCandidateGroupIdExpressions();
			if (!candidateGroupIdExpressions.isEmpty()) {
				// 任务的处理角色
				setTaskGroup(vars, candidateGroupIdExpressions);

				// 当前处理人
				if (currentTask != null) {
					setCurrentTaskAssignee(vars, currentTask);
				}
			}
		}

		vars.put("节点说明", properties.get("documentation"));

		String description = activity.getProcessDefinition().getDescription();
		vars.put("描述", description);

		logger.debug("trace variables: {}"+ vars);
		activityInfo.put("vars", vars);
		activityInfo.put("id",activity.getId());
		return activityInfo;
	}
	
	/**
	 * 设置坐标位置
	 * 
	 * @param activity
	 * @param activityInfo
	 */
	private void setPosition(ActivityImpl activity,
			Map<String, Object> activityInfo) {
		activityInfo.put("x", activity.getX());
		activityInfo.put("y", activity.getY());
	}
	
	/**
	 * 设置宽度、高度属性
	 * 
	 * @param activity
	 * @param activityInfo
	 */
	private void setWidthAndHeight(ActivityImpl activity,
			Map<String, Object> activityInfo) {
		activityInfo.put("width", activity.getWidth());
		activityInfo.put("height", activity.getHeight());
	}
	
	/**
	 * 获取当前节点信息
	 * 
	 * @param processInstance
	 * @return
	 */
	private Task getCurrentTaskInfo(ProcessInstance processInstance) {
		Task currentTask = null;
		try {
			String activitiId = (String) PropertyUtils.getProperty(
					processInstance, "activityId");
			logger.debug("current activity id: {}"+ activitiId);

			currentTask = taskService.createTaskQuery()
					.processInstanceId(processInstance.getId())
					.taskDefinitionKey(activitiId).singleResult();
			logger.debug("current task for processInstance: {}"+ToStringBuilder.reflectionToString(currentTask));

		} catch (Exception e) {
			logger.error(
					"can not get property activityId from processInstance: {}"+processInstance);
		}
		return currentTask;
	}
	
	/**
	 * 设置当前处理人信息
	 * 
	 * @param vars
	 * @param currentTask
	 */
	private void setCurrentTaskAssignee(Map<String, Object> vars,
			Task currentTask) {
		String assignee = currentTask.getAssignee();
		if (assignee != null) {
			User assigneeUser = identityService.createUserQuery()
					.userId(assignee).singleResult();
			String userInfo = "任务未分配！";
			if(assigneeUser!=null){
				userInfo = assigneeUser.getFirstName() + " "
						+ assigneeUser.getLastName();
			}
			vars.put("当前处理人", userInfo);
		}
	}
	
	private void setTaskGroup(Map<String, Object> vars,
			Set<Expression> candidateGroupIdExpressions) {
		String roles = "";
		/*for (Expression expression : candidateGroupIdExpressions) {
			String expressionText = expression.getExpressionText();
			ActGroupModel groupModel = groupService.getGroupByGroupId(expressionText);
			if(groupModel!=null){
				String roleName = groupModel.getGroupName();
				roles += roleName;
			}
		}*/
		vars.put("任务所属角色", roles);
	}
	
}
