/**  
* @Title: ActivitiProcessTest.java
* @Package org.block.core.activiti
* @author 李志勇  
* @date 2015年1月19日 下午1:31:11
* @version V1.0  
*/ 
package org.block.core.activiti;

import javax.annotation.Resource;

import org.activiti.engine.repository.ProcessDefinition;
import org.block.core.BaseTest;
import org.junit.Test;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.vo.activiti.ProcessDefInfo;
import com.lzy.block.core.service.activiti.IActivitiProcessService;

/**
 * @ClassName: ActivitiProcessTest
 * @Description: 流程测试 
 * @author 李志勇
 * @date 2015年1月19日 下午1:31:11
 *
 */
public class ActivitiProcessTest extends BaseTest{
	
	@Resource
	private IActivitiProcessService activitiProcessService;
	
	@Test
	public void testMyStartProcess(){
		PageModel pageModel=new PageModel(1,5);
		try {
			 activitiProcessService.getMyStartProcess("kafeitu", pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMyJoinProcess(){
		PageModel pageModel=new PageModel(1,5);
		try {
			 activitiProcessService.getMyJoinProcess("kafeitu", pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMyJoinFinishedProcess(){
		PageModel pageModel=new PageModel(1,5);
		try {
			 Pagination<ProcessDefInfo> myJoinFinishedProcess = activitiProcessService.getMyJoinFinishedProcess("kafeitu", pageModel);
			 for(ProcessDefInfo info:myJoinFinishedProcess.getRecordList()){
				 System.out.println(info+">>>>>>>>>>>>>>>>>>");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetProcessById(){
		try {
			ProcessDefinition process= activitiProcessService.getProcessById("returnGoods:2:11715");
			System.out.println(process+"===============");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void testGetProcessByKey(){
		try {
			ProcessDefinition process= activitiProcessService.getProcessById("leave-dynamic-from:1:23");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
