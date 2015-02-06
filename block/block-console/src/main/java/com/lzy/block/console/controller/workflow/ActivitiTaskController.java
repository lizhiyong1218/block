/**  
* @Title: WorkFlowTaskController.java
* @Package com.lzy.block.console.controller.workflow
* @author 李志勇  
* @date 2015年1月16日 上午10:07:52
* @version V1.0  
*/ 
package com.lzy.block.console.controller.workflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.common.StrUtil;
import com.lzy.block.api.vo.activiti.ProcessHistoryVo;
import com.lzy.block.api.vo.activiti.ProcessTaskDetailVo;
import com.lzy.block.api.vo.activiti.ProcessTaskVo;
import com.lzy.block.core.service.activiti.IActivitiHistoryService;
import com.lzy.block.core.service.activiti.IActivitiProcessInstanceService;
import com.lzy.block.core.service.activiti.IActivitiProcessService;
import com.lzy.block.core.service.activiti.IActivitiTaskService;

/**
 * @ClassName: WorkFlowTaskController
 * @Description: 工作流任务控制器 
 * @author 李志勇
 * @date 2015年1月16日 上午10:07:52
 *
 */
@Controller
@RequestMapping(value = "/activitiTask")
public class ActivitiTaskController {
	
	private static Logger logger = Logger.getLogger(ActivitiTaskController.class.getName());
	@Resource
	private IActivitiTaskService activitiTaskService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private FormService formService;
	@Resource
	private IActivitiProcessInstanceService activitiProcessInstanceService;
	@Resource
	private IActivitiProcessService activitiProcessService;
	@Resource
	private IActivitiHistoryService activitiHistoryService;
	
	
	@RequestMapping(value = "/taskAssignDetail")
	public @ResponseBody String taskAssignDetail( ){
		return "";
	}
	
	
	/**
	 * 
	* @Title: workflowList
	* @Description: 我的代办
	* @param pageModel
	* @return:     
	* Map<String,Object>    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/todoTaskList")
	public  Map<String, Object>  workflowList(PageModel pageModel) {
		Pagination<ProcessTaskVo> pm = new Pagination<ProcessTaskVo>();
		try {				
			pm=activitiTaskService.getTodoTask("kafeitu", pageModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
	/**
	 * 
	* @Title: taskDetail
	* @Description: 任务详情
	* @param taskId
	* @return
	* @throws Exception:     
	* ProcessTaskDetailVo    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/taskDetail")
	public 
	ProcessTaskDetailVo taskDetail(String taskId) throws Exception {
		ProcessTaskDetailVo detailVo=new ProcessTaskDetailVo();
		String userId="kafeitu";
		Task task = activitiTaskService.getTaskById(taskId);
		if(task!=null){
			//获取流程实例
			ProcessInstance processInstance = activitiProcessInstanceService.getProcessInstanceById(task.getProcessInstanceId());
			Map<String, Object> processVariables = processInstance.getProcessVariables();
			String busKEY= processInstance.getBusinessKey();
			System.out.println(busKEY);
			String processStartBy=(String) processVariables.get("startBy");//流程发起人
			String businessKey=(String) processVariables.get("businessKey");//流程业务编号
			Date processStartTime= (Date) processVariables.get("startTime");//创建时间
			
			//获取当前节点信息
			ActivityImpl currentActiviti = activitiProcessService.getCurrentActiviti(processInstance.getProcessDefinitionId(), processInstance.getActivityId());
			String taskName=(String) currentActiviti.getProperty("name");//当前节点名称
					 
			
			detailVo.setProcessStartBy(processStartBy);
			detailVo.setProcessStartTime(processStartTime);
			detailVo.setTaskName(taskName);
			detailVo.setAssignee(userId);
			detailVo.setBusinessKey(businessKey);
			
			
		}else{
			logger.error("任务为空!"+taskId);
		}
		
		
 
		/*TaskInfo taskInfo = new TaskInfo();
		taskInfo.setCreateTime(task.getCreateTime());
		taskInfo.setDeadLine(task.getDueDate());
		taskInfo.setDescription(task.getDescription());
		taskInfo.setOwner(task.getOwner());
		taskInfo.setPriority(task.getPriority());
		taskInfo.setProcessDefId(task.getProcessDefinitionId());
		taskInfo.setTaskId(task.getId());
		taskInfo.setTaskKey(task.getTaskDefinitionKey());
		taskInfo.setTaskName(task.getName());

		taskInfo.setAssignee(workflowUserService.getRealUserNameByUserId(task.getAssignee()));
		
		taskInfo.setBusinessId(processInstance.getBusinessKey());
		taskInfo.setExecutionId(task.getExecutionId());
		taskInfo.setProcessInstanceId(task.getProcessInstanceId());
		taskInfo.setFormKey(formKey);
		taskInfo.setProcessName(processDefinition.getName());
		taskInfo.setIsSuspended(task.isSuspended());
		buildVerifyUrl(taskInfo,false);
 */
		return detailVo;
	}
	
	
	@RequestMapping(value="/taskDetailPage")
	public ModelAndView taskDetailPage(String taskId){
		ProcessTaskDetailVo detailVo=new ProcessTaskDetailVo();
		try {
			String userId="kafeitu";
			Task task = activitiTaskService.getTaskById(taskId);
			if(task!=null){
				//获取流程实例
				ProcessInstance processInstance = activitiProcessInstanceService.getProcessInstanceById(task.getProcessInstanceId());
				Map<String, Object> processVariables = processInstance.getProcessVariables();
				String businessKey= processInstance.getBusinessKey();//流程业务编号
				String processStartBy=(String) processVariables.get("startBy");//流程发起人
				Date processStartTime= (Date) processVariables.get("startTime");//创建时间
				//表单key
				String formKey= formService.getTaskFormKey(processInstance.getProcessDefinitionId(), task.getTaskDefinitionKey());
				
				//获取当前节点信息
				ActivityImpl currentActiviti = activitiProcessService.getCurrentActiviti(processInstance.getProcessDefinitionId(), processInstance.getActivityId());
				String taskName=(String) currentActiviti.getProperty("name");//当前节点名称
				detailVo.setProcessStartBy(processStartBy);
				detailVo.setProcessStartTime(processStartTime);
				detailVo.setTaskName(taskName);
				detailVo.setAssignee(userId);
				detailVo.setBusinessKey(businessKey);
				detailVo.setProcessInstanceId(task.getProcessInstanceId());
				detailVo.setFormKey(formKey);
			}else{
				logger.error("任务为空!"+taskId);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info(detailVo);
		String url=null;
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("detailVo", detailVo);
		if(StrUtil.isNotEmpty(detailVo.getFormKey())){
			url="/activiti/layout/comlexTaskDetail";
		}else{
			url="/activiti/layout/simpleTaskDetail";
		}
		return new ModelAndView(url,modelMap);
	}
	
	
	/**
	 * 
	 * @Title: taskVerifyInfos
	 * @Description: 流程历史信息
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 *             : String
	 * @throws
	 */
	@RequestMapping(value = "/taskHistoryInfo")
	@ResponseBody
	public Map<String, Object> taskHistoryInfo(String processInstanceId) throws Exception {
		List<ProcessHistoryVo> historys = new ArrayList<ProcessHistoryVo>();
		List<HistoricTaskInstance> taskVerifyInfos = activitiHistoryService
				.findHistoryTasksByProcessInstanceId(processInstanceId);
		for (HistoricTaskInstance historicTaskInstance : taskVerifyInfos) {
			ProcessHistoryVo historyInfo = new ProcessHistoryVo();
			System.out.println(historicTaskInstance.getFormKey());
			historyInfo.setTaskName(historicTaskInstance.getName());//节点名称
			historyInfo.setAssignee(historicTaskInstance.getAssignee());//节点处理人
			historyInfo.setStartTime(historicTaskInstance.getStartTime());//开始时间
			historyInfo.setEndTime(historicTaskInstance.getEndTime());//结束时间
			historyInfo.setOwner(historicTaskInstance.getOwner());//拥有人
			historyInfo.setDescription(historicTaskInstance.getDescription());//描述
			historys.add(historyInfo);
		}
//		taskService.fillTaskInfo(taskInfos,taskVerifyInfos,processInstanceId);
		ModelMap map=new ModelMap();
		map.put("rows", historys);  
		map.put("total", historys.size()); 
		return map;
	}
	
	
	/*
	private void buildVerifyUrl(TaskInfo taskInfo,boolean isVerified){
		String baseUrl = "/activiti/redirect.do?redirect=layout/layout";
		if(isVerified){
			baseUrl+="Verified";
		}
		
		StringBuilder url = new StringBuilder(baseUrl)
		                         .append("&taskId=").append(taskInfo.getTaskId())
		                         .append("&bussinessId=").append(taskInfo.getBusinessId())
		                         .append("&taskKey=").append(taskInfo.getTaskKey())
		                         .append("&taskName=").append(taskInfo.getTaskName())
		                         .append("&processInstanceId=").append(taskInfo.getProcessInstanceId());
		if(!isVerified){
			url.append("&suspended=").append(taskInfo.getIsSuspended())
              .append("&includeJsp=").append(taskInfo.getFormKey());
		}
		                       
		taskInfo.setVerifyUrl(url.toString());
	}
	
*/
}
