/**  
* @Title: ActivitiServiceImpl.java
* @Package com.lzy.block.core.service.activiti.impl
* @author 李志勇  
* @date 2015年1月12日 下午4:34:43
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzy.block.core.service.activiti.IActivitiService;
import com.lzy.block.core.utils.WorkFlowUtil;

/**
 * @ClassName: ActivitiServiceImpl
 * @Description: 工作流实现类 
 * @author 李志勇
 * @date 2015年1月12日 下午4:34:43
 *
 */
@Service
public class ActivitiServiceImpl implements IActivitiService {
	
	private static Logger logger = Logger.getLogger(ActivitiServiceImpl.class.getName());
	
	@Autowired
	protected RepositoryService repositoryService;

	/* (非 Javadoc)
	 * <p>Title: deploy</p>
	 * <p>Description:部署工作流 </p>
	 * @param fileInputStream
	 * @param fileName
	 * @param exportDir
	 * @throws Exception
	 * @see com.lzy.block.core.service.activiti.IActivitiService#deploy(java.io.InputStream, java.lang.String, java.lang.String)
	 */
	@Override
	public void addDeploy(InputStream fileInputStream, String fileName,
			String exportDir) throws Exception {
		
		logger.info("部署流程开始!");
		
		/**
		 * 生成部署信息
		 */
		Deployment deployment = null;
		String extension = FilenameUtils.getExtension(fileName);
		if (extension.equals("zip") || extension.equals("bar")) {
			ZipInputStream zip = new ZipInputStream(fileInputStream);
			deployment = repositoryService.createDeployment()
					.addZipInputStream(zip).deploy();
		} else {
			deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
		}
		
		/**
		 * 获取流程信息
		 */
		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).list();
		
		/**
		 * 生成流程图
		 */
		for (ProcessDefinition processDefinition : list) {
			try {
				WorkFlowUtil.exportDiagramToFile(repositoryService,
						processDefinition, exportDir);
			} catch (IOException e) {
				logger.error("导出图片失败!"+e);
				throw e;
			}
		}
		
		logger.info("部署流程结束!");

	}

	@Override
	public String getUserGroupJsonTree() throws Exception {
		 
		return null;
	}
	

	/**
	 * 流程跟踪图
	 * 
	 * @param processDefinitionId流程定义ID
	 * @return 封装了各种节点信息
	 */
	@Override
	public List<Map<String, Object>> traceTaskDefinations(String processDefinitionId) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<Map<String, Object>> taskInfos = new ArrayList<Map<String, Object>>();
		Map<String, TaskDefinition> taskDefinitions = processDefinition.getTaskDefinitions();
        for (TaskDefinition taskDefinition : taskDefinitions.values()) {
        	Map<String,Object> taskInfo = new HashMap<String,Object>();
        	taskInfo.put("taskKey", taskDefinition.getKey());
        	taskInfo.put("taskName", taskDefinition.getNameExpression().getExpressionText());
        	if(taskDefinition.getAssigneeExpression()!=null){
        		taskInfo.put("assignee", taskDefinition.getAssigneeExpression().getExpressionText());
        	}
			Set<Expression> candidateUserIdExpressions = taskDefinition.getCandidateUserIdExpressions();
			Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
			
			Map<String,Object> cadidateUsers = new HashMap<String,Object>();
			for (Expression expression : candidateUserIdExpressions) {
				cadidateUsers.put("userName", expression.getExpressionText());
			}
			taskInfo.put("cadidateUsers", cadidateUsers);
			
			Map<String,Object> cadidateGroups = new HashMap<String,Object>();
			for (Expression expression : candidateGroupIdExpressions) {
				cadidateGroups.put("groupName", expression.getExpressionText());
			}
			taskInfo.put("cadidateGroups", cadidateGroups);
			taskInfos.add(taskInfo);
		}	
		return taskInfos;
	}

}
