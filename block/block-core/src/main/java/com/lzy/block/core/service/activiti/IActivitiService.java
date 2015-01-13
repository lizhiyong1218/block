/**  
* @Title: IActivitiService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月12日 下午4:33:24
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.io.InputStream;

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
}
