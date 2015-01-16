/**  
* @Title: WorkFlowTaskController.java
* @Package com.lzy.block.console.controller.workflow
* @author 李志勇  
* @date 2015年1月16日 上午10:07:52
* @version V1.0  
*/ 
package com.lzy.block.console.controller.workflow;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.api.vo.ResultVo;
import com.lzy.block.api.vo.activiti.OrgStructModel;
import com.lzy.block.core.common.JsonUtils;

/**
 * @ClassName: WorkFlowTaskController
 * @Description: 工作流任务控制器 
 * @author 李志勇
 * @date 2015年1月16日 上午10:07:52
 *
 */
@Controller
@RequestMapping(value = "/workflowTask")
public class WorkFlowTaskController {
	
	private static Logger logger = Logger.getLogger(WorkFlowTaskController.class.getName());
	
	@RequestMapping(value = "/taskAssignDetail")
	public @ResponseBody String taskAssignDetail(OrgStructModel orgStructModel){
		ResultVo returnVo = new ResultVo();
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

}
