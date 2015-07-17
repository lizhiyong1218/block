/**  
* @Title: WorkFlowTaskTest.java
* @Package org.block.core.activiti
* @author 李志勇  
* @date 2015年1月16日 上午10:58:06
* @version V1.0  
*/ 
package org.block.core.activiti;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.block.core.BaseTest;
import org.junit.Test;

import com.lzy.block.api.vo.activiti.ProcessTaskVo;
import com.lzy.block.core.service.activiti.IActivitiTaskService;

/**
 * @ClassName: WorkFlowTaskTest
 * @Description: 工作流任务测试 
 * @author 李志勇
 * @date 2015年1月16日 上午10:58:06
 *
 */
public class ActivitiTaskTest extends BaseTest {
	
	@Resource
	private IActivitiTaskService activitiTaskService;
	
	@Resource
	private TaskService taskService;
	
	@Test
	public void testGetAllAssignee(){ 
		List<Task> todoList = taskService.createTaskQuery().taskAssignee("kafeitu").active().list() ;
		System.out.println("=============================");
		for (Task task : todoList) {
			System.out.println(task+"============");
		}
	}
	
	@Test
	public void testGetAllCandidateUser(){ 
		List<Task> todoList = taskService.createTaskQuery().taskCandidateUser("kafeitu").active().list() ;
		System.out.println("=============================");
		for (Task task : todoList) {
			System.out.println(task+"============");
		}
	}
	
	@Test
	public void testGetTodoTaskAll(){ 
		List<ProcessTaskVo> todoTask = activitiTaskService.getTodoTask("kafeitu");
		System.out.println("=============================");
		for (ProcessTaskVo processTaskVo : todoTask) {
			System.out.println(processTaskVo+"======>>>>>>>");
		}
		System.out.println("=============================");
	}
	
	/**
	 * 
	* @Title: testClaim
	* @Description: 签收:     
	* void    
	* @throws
	 */
	@Test
	public void testClaim(){ 
		String taskId="11917";
		String userId="kafeitu";
		activitiTaskService.claim(taskId, userId);
	}
	
}
