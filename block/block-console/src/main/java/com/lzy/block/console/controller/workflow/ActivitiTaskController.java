/**  
* @Title: WorkFlowTaskController.java
* @Package com.lzy.block.console.controller.workflow
* @author 李志勇  
* @date 2015年1月16日 上午10:07:52
* @version V1.0  
*/ 
package com.lzy.block.console.controller.workflow;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.api.common.PageModel;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.vo.activiti.ProcessTaskVo;
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
	
	@RequestMapping(value = "/taskAssignDetail")
	public @ResponseBody String taskAssignDetail( ){
//		ResultVo returnVo = new ResultVo();
		/*try {
			List<OrgStructModel> all = orgStructService.getAll(orgStructModel);
			return JsonUtils.toJson(all);
		} catch (Exception e) {
			logger.info("查询分配审核人和候选人以及候选分组失败！");
			returnVo.setResponseCode(ResponseCode.FAIL);
			returnVo.setResponseMsg("查询分配审核人和候选人以及候选分组失败！");
		}*/
		return "";
	}
	
	
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
	

}
