/**  
* @Title: ActivitiProcessServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月19日 上午10:52:22
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.vo.activiti.ProcessDefInfo;
import com.lzy.block.core.service.activiti.IActivitiProcessInstanceService;
import com.lzy.block.core.service.activiti.IActivitiProcessService;

/**
 * @ClassName: ActivitiProcessServiceImpl
 * @Description: 工作流流程 
 * @author 李志勇
 * @date 2015年1月19日 上午10:52:22
 *
 */
@Service
public class ActivitiProcessServiceImpl implements IActivitiProcessService {

	private static Logger logger = Logger.getLogger(ActivitiProcessServiceImpl.class.getName());
	
	@Resource
	private HistoryService historyService;
	
	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private IActivitiProcessInstanceService activitiProcessInstanceService;
	
	
	/* (非 Javadoc)
	 * <p>Title: getMyStartProcess</p>
	 * <p>Description: </p>
	 * @param userName
	 * @return
	 * @see com.lzy.block.core.service.activiti.IActivitiProcessService#getMyStartProcess(java.lang.String)
	 */
	@Override
	public Pagination<ProcessDefInfo> getMyStartProcess(String userName,PageModel pageModel) {

		int firstResult = (pageModel.getPage() - 1)
				* pageModel.getRows();
		int maxResults = firstResult + pageModel.getRows() - 1;
		
		//流程历史记录查询语句
		HistoricProcessInstanceQuery historyQuery = historyService
				.createHistoricProcessInstanceQuery().startedBy(userName);
		
		ProcessDefinition processDef=null;//流程对象
		long count = historyQuery.count(); //总流程数量
		List<HistoricProcessInstance> historicProIns = new ArrayList<HistoricProcessInstance>();//流程list
		List<ProcessDefInfo> list=new ArrayList<ProcessDefInfo>();//封装为要显示到页面的list
		if (count > 0) {
			//流程历史列表
			historicProIns = historyQuery.listPage(firstResult, maxResults);
			ProcessDefInfo processDefInfo = null;//封装成要显示的vo
			for (HistoricProcessInstance historicProcessInstance : historicProIns) {
				processDefInfo=packageProcessInfo(processDef, historicProcessInstance);
				list.add(processDefInfo);
			}

		}
		Pagination<ProcessDefInfo> pagination = new Pagination<ProcessDefInfo>();
		pagination.setRecordCount(count);
		pagination.setRecordList(list);
		return pagination;
	}

	@Override
	public Pagination<ProcessDefInfo> getMyJoinProcess(String userName,
			PageModel pageModel) throws Exception {

		int firstResult = (pageModel.getPage() - 1)
				* pageModel.getRows();
		int maxResults = firstResult + pageModel.getRows() - 1;
		
		//流程历史记录查询语句
		HistoricProcessInstanceQuery historyQuery = historyService
				.createHistoricProcessInstanceQuery().involvedUser(userName);
		
		ProcessDefinition processDef=null;//流程对象
		long count = historyQuery.count(); //总流程数量
		List<HistoricProcessInstance> historicProIns = new ArrayList<HistoricProcessInstance>();//流程list
		List<ProcessDefInfo> list=new ArrayList<ProcessDefInfo>();//封装为要显示到页面的list
		if (count > 0) {
			//流程历史列表
			historicProIns = historyQuery.listPage(firstResult, maxResults);
			ProcessDefInfo processDefInfo = null;//封装成要显示的vo
			for (HistoricProcessInstance historicProcessInstance : historicProIns) {
				processDefInfo=packageProcessInfo(processDef, historicProcessInstance);
				list.add(processDefInfo);
			}

		}
		Pagination<ProcessDefInfo> pagination = new Pagination<ProcessDefInfo>();
		pagination.setRecordCount(count);
		pagination.setRecordList(list);
		return pagination;
	}
	
	@Override
	public Pagination<ProcessDefInfo> getMyJoinFinishedProcess(String userName,
			PageModel pageModel) throws Exception {
		int firstResult = (pageModel.getPage() - 1)
				* pageModel.getRows();
		int maxResults = firstResult + pageModel.getRows() - 1;
		
		//流程历史记录查询语句
		HistoricProcessInstanceQuery historyQuery = historyService
				.createHistoricProcessInstanceQuery().involvedUser(userName).finished();
		
		ProcessDefinition processDef=null;//流程对象
		long count = historyQuery.count(); //总流程数量
		List<HistoricProcessInstance> historicProIns = new ArrayList<HistoricProcessInstance>();//流程list
		List<ProcessDefInfo> list=new ArrayList<ProcessDefInfo>();//封装为要显示到页面的list
		if (count > 0) {
			//流程历史列表
			historicProIns = historyQuery.listPage(firstResult, maxResults);
			ProcessDefInfo processDefInfo = null;//封装成要显示的vo
			for (HistoricProcessInstance historicProcessInstance : historicProIns) {
				processDefInfo=packageProcessInfo(processDef, historicProcessInstance);
				list.add(processDefInfo);
			}

		}
		Pagination<ProcessDefInfo> pagination = new Pagination<ProcessDefInfo>();
		pagination.setRecordCount(count);
		pagination.setRecordList(list);
		return pagination;
	}
	
	
	
	/**
	 * 
	 * @Title: packageProcessInfo
	 * @Description: 将流程信息以及流程历史记录组合成要显示的流程信息
	 * @param processDef
	 * @param historicProcessInstance
	 * @return: ProcessDefInfo
	 * @throws
	 */
	private ProcessDefInfo packageProcessInfo(ProcessDefinition processDef,HistoricProcessInstance historicProcessInstance){
		ProcessDefInfo processDefInfo = new ProcessDefInfo();
		//通过流程id获取流程对象
		processDef = getProcessDefById(historicProcessInstance.getProcessDefinitionId());
		processDefInfo.setId(historicProcessInstance.getProcessDefinitionId());
		processDefInfo.setName(processDef.getName());
		processDefInfo.setCategory(processDef.getCategory());
		processDefInfo.setVersion(processDef.getVersion());
		processDefInfo.setResourceName(processDef.getResourceName());
		processDefInfo.setDiagramResourceName(processDef.getDiagramResourceName());
		processDefInfo.setDeploymentId(processDef.getDeploymentId());
		processDefInfo.setProcessKey(processDef.getKey());
		processDefInfo.setStartUser(historicProcessInstance
				.getStartUserId());
		processDefInfo.setStartTime(historicProcessInstance
				.getStartTime());
		processDefInfo.setEndTime(historicProcessInstance.getEndTime());
		
		try {
			//通过流程历史id获取流程实例对象
			ProcessInstance	processInstance = activitiProcessInstanceService.getProcessInstanceById(historicProcessInstance.getId());
			if (processInstance != null) {
				processDefInfo.setActivitiId(processInstance.getActivityId());
				processDefInfo.setProcessInstanceId(processInstance.getId());
				ActivityImpl currentActiviti = getCurrentActiviti(processDef.getId(), processInstance.getActivityId());
				if(currentActiviti!=null){
					processDefInfo.setCurrentNode((String)currentActiviti.getProperty("name"));// 当前节点名称	
				}
			}
		} catch (Exception e) {
			logger.error("获取流程实例失败:"+e);
		}
		return processDefInfo;
	}
	
	/**
	 * 
	 * @Title: getCurrentActiviti
	 * @Description: 获取当前节点
	 * @param processInstance
	 * @param processDef
	 * @return: ActivityImpl
	 * @throws
	 */
	public ActivityImpl getCurrentActiviti(String processDefId,String activitiId ){
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefId); 
		List<ActivityImpl> activitiList = def.getActivities();//获得当前任务的所有节点
		for(ActivityImpl activity:activitiList){
			 if(activitiId.equals(activity.getId())){ 
				 logger.debug("当前节点："+activity.getProperty("name"));
				 return activity;
			 }
		}
		return null;
	}
	
	
	@Override
	public ProcessDefinition getProcessDefById(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	 

	

}
