/**  
* @Title: IActivitiService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月12日 下午4:33:24
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IActivitiService
 * @Description: 工作流service 
 * @author 李志勇
 * @date 2015年1月12日 下午4:33:24
 *
 */
public interface IActivitiService {
	/**
	 * 1.生成部署信息
	 * 2.生成流程图片
	 * @Title: deploy
	 * @Description: 部署工作流
	 * @param fileInputStream
	 * @param fileName
	 * @param exportDir
	 * @throws Exception
	 *             : void
	 * @throws
	 */
	public void addDeploy(InputStream fileInputStream,String fileName,String exportDir)throws Exception;
	
	/**
	 * 
	 * @Title: getUserGroupJsonTree
	 * @Description: 获取任务分配人信息
	 * @return: String
	 * @throws
	 */
	public String getUserGroupJsonTree() throws Exception;

	/**
	 * 
	 * @Title: traceTaskDefinations
	 * @Description: 获取流程任务信息
	 * @param processDefinitionId  流程id
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	List<Map<String, Object>> traceTaskDefinations(String processDefinitionId);
}
