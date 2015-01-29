/**  
* @Title: ActivitiProcessInstanceTest.java
* @Package org.block.core.activiti
* @author 李志勇  
* @date 2015年1月26日 下午2:52:06
* @version V1.0  
*/ 
package org.block.core.activiti;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.block.core.BaseTest;
import org.junit.Test;

import com.lzy.block.core.service.activiti.IActivitiProcessInstanceService;

/**
 * @ClassName: ActivitiProcessInstanceTest
 * @Description: ActivitiProcessInstance 
 * @author 李志勇
 * @date 2015年1月26日 下午2:52:06
 *
 */
public class ActivitiProcessInstanceTest extends BaseTest{
	@Resource
	private IActivitiProcessInstanceService activitiProcessInstanceService;
	
	@Test
	public void testStartWorkflow() throws Exception{
		String processDefinitionKey="returnGoods";
		String busnessKey=new Date().toString();
		System.out.println(busnessKey);
		String userId="kafeitu";
		Map<String, Object> variables=new HashMap<String, Object>();
		ProcessInstance startWorkFlow = activitiProcessInstanceService.startWorkFlow(processDefinitionKey, busnessKey, userId, variables);
		System.out.println(startWorkFlow);
	}
	
}
