/**  
* @Title: ITraceService.java
* @Package com.lzy.block.core.service.activiti
* @author 李志勇  
* @date 2015年1月20日 上午11:29:49
* @version V1.0  
*/ 
package com.lzy.block.core.service.activiti;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ITraceService
 * @Description: 工作流路线 
 * @author 李志勇
 * @date 2015年1月20日 上午11:29:49
 *
 */
public interface IActivitiTraceService {

	public List<Map<String, Object>> traceProcess(String processInstanceId);

}
