/**  
* @Title: IActivitiTaskService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月16日 上午11:02:25
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.Task;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.vo.activiti.ProcessTaskVo;


/**
 * @ClassName: IActivitiTaskService
 * @Description: 工作流任务 
 * @author 李志勇
 * @date 2015年1月16日 上午11:02:25
 *
 */
public interface IActivitiTaskService {
	
	public List<ProcessTaskVo> getTodoTask(String userId) ;
	
	public Pagination<ProcessTaskVo> getTodoTask(String userId,PageModel pageModel);
	
	
	/**
	 * 根据任务ID查找任务
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	Task findTaskById(String taskId) ;
	
	/**
	 * 根据任务ID查找已经审核过的任务
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	HistoricTaskInstance findHistoryTaskById(String id);

	/**
	 * 根据任务ID查找活动节点
	 * @param taskId
	 * @return
	 */
	ActivityImpl findActivitiByTaskId(String taskId); 
	
	/**
	 * 根据业务ID和流程定义关键字查找任务
	 * @param businessKey
	 * @param processDefinitionKey
	 * @return
	 */
	Task getTaskByBusinessKeyAndProcessDefinitionKey(String businessKey,String processDefinitionKey);
	
	/**
	 * 填充任务信息
	 * @param taskInfos
	 * @param taskVerifyInfos
	 * @param processInstanceId
	 */
//	void fillTaskInfo(List<TaskInfo> taskInfos,List<HistoricTaskInstance> taskVerifyInfos,String processInstanceId);
	
	/**
	 * 根据任务ID查找Task定义节点
	 * @param taskId
	 * @return
	 */
	TaskDefinition findTaskDefinitionByTaskId(String taskId);
	
	/**
	 * 动态分配任务
	 * @param taskAssignVariables
	 */
//	void assignTask(TaskAssignVariables taskAssignVariables)throws Exception;
//	
//	PageModel<Task> findTodoTasks(String userId, int fristResult, int maxResult,WorkflowSearchVo workflowSearchVo);
//
//	PageModel<Task> findTodoTasks(String userId,int fristResult, int maxResult);
//	
//	<T extends BusinessObject> List<T> findTodoTasks(String userId,
//			TaskType taskType, int fristResult, int maxResult,
//			BusinessObjectGetter<T> bussinessObjectGetter);
//
//	<T extends BusinessObject> List<T> findTodoTasksByDefKey(
//			String processDefinitionKey, String userId, TaskType taskType,
//			int fristResult, int maxResult,
//			BusinessObjectGetter<T> bussinessObjectGetter);
//
//	<T extends BusinessObject> List<T> findTodoTasksByQuery(TaskQuery query,
//			BusinessObjectGetter<T> bussinessObjectGetter);

	/**
	 * 委派任务(签收了的任务)
	 * 
	 * @param taskId
	 * @param userId
	 */
	void delegateTask(String taskId, String userId);

	/**
	 * 委派的任务已经完成，可以送回到任务的所有者
	 * 
	 * @param taskId
	 * @param variables
	 */
	void resolveTask(String taskId, Map<String, Object> variables);

	/**
	 * 用户有任务无法完成，将任务委托给其他人（userId代表的用户仍然是任务的所有者）
	 * 
	 * @param taskId
	 * @param variables
	 */
	void setOwnerAndAssignTask(String taskId, String userId,
			String delegateUserId);
	
	/**
	 * 根据实例编号查找下一个任务节点(如果是网关需要提供条件)
	 * @param String procInstId ：实例编号
	 * @return
	 */
	TaskDefinition nextTaskDefinition(String procInstId,String conditionExpression);

	/**
	 * 查找一个流程下的所有被审批的任务
	 * @param processInstanceId
	 * @return
	 */
	List<HistoricTaskInstance> findHistoryTasksByProcessInstanceId(
			String processInstanceId);

	/**
	 * 获取任务参数
	 * @param taskId
	 * @return
	 */
	Map<String, Object> getVariables(String taskId);
	
    /**
     * 判断某个人是否有审核任务的权限
     * @param userId
     * @return
     */
	boolean hasVerifyPermission(String taskId,String userId);
}
