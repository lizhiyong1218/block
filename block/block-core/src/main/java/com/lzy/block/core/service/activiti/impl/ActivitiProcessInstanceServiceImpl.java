/**  
* @Title: ActivitiProcessInstanceServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月26日 下午2:28:43
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzy.block.core.service.activiti.IActivitiProcessInstanceService;
import com.lzy.block.core.service.activiti.IActivitiTaskService;

/**
 * @ClassName: ActivitiProcessInstanceServiceImpl
 * @Description: 流程实例service  
 * @author 李志勇
 * @date 2015年1月26日 下午2:28:43
 *
 */
@Service
public class ActivitiProcessInstanceServiceImpl implements
		IActivitiProcessInstanceService {
	
	private static Logger logger = Logger.getLogger(ActivitiProcessInstanceServiceImpl.class.getName());
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	@Resource
	private IActivitiTaskService activitiTaskService;
	@Autowired
	private TaskService taskService;

	/* (非 Javadoc)
	 * <p>Title: startWorkFlow</p>
	 * <p>Description: </p>
	 * @param processDefinitionKey
	 * @param busnessKey
	 * @param userId
	 * @param variables
	 * @throws Exception
	 * @see com.lzy.block.core.service.activiti.IActivitiProcessInstanceService#startWorkFlow(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public ProcessInstance startWorkFlow(String processDefinitionKey, String busnessKey,
			String userId, Map<String, Object> variables) throws Exception {
		ProcessInstance processInstance = null;
		try {
			identityService.setAuthenticatedUserId(userId);//在localthread对象中设置了用户，启动进程时从localthread取用户
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,
					busnessKey, variables);
			Task nextTask = activitiTaskService.getCurrentTaskByBusKeyAndProDefKey(processInstance.getBusinessKey(),processDefinitionKey);
			if(nextTask!=null){
				setTaskPeople(processInstance.getProcessDefinitionId(), nextTask.getTaskDefinitionKey(), nextTask.getId());
			}
		}catch(Exception e){
			logger.error("启动流程失败： "+e);
			throw new RuntimeException();
		}finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}
	
	
	private void setTaskPeople(String processDefinitionId,String taskDefinitionKey,String taskId) throws Exception{
		/*OrgStructModel example = new OrgStructModel();
		example.setDefKey(processDefinitionId);
		example.setTaskKey(taskDefinitionKey);
		List<OrgStructModel> list = orgStructService.getAll(example);
		logger.info("查询审核分配人！"+ list.size());
		for (OrgStructModel orgStructModel : list) {
			if(orgStructModel.getType().equals(TaskAssignType.ASSIGNEE.value())){
				logger.info("关联任务和审核分配人！" );
				taskService.addUserIdentityLink(taskId, orgStructModel.getOrgId(), IdentityLinkType.ASSIGNEE);
			}else if(orgStructModel.getType().equals(TaskAssignType.PARTICIPANT.value())){
				taskService.addUserIdentityLink(taskId, orgStructModel.getOrgId(), IdentityLinkType.PARTICIPANT);
			}else if(orgStructModel.getType().equals(TaskAssignType.CANDIDATE_USER.value())){
				taskService.addCandidateUser(taskId, orgStructModel.getOrgId());
			}else if(orgStructModel.getType().equals(TaskAssignType.CANDIDATE_GROUP.value())){
				taskService.addCandidateGroup(taskId, orgStructModel.getOrgId());
			}
		}*/
		String userId="kafeitu";
		/*设置为候选*/
		taskService.addUserIdentityLink(taskId, userId, IdentityLinkType.CANDIDATE);
	}

}
