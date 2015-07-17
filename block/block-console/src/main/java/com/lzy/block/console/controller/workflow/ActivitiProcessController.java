/**  
* @Title: ActivitiProcessController.java
* @Package com.lzy.block.console.controller.workflow
* @author 李志勇  
* @date 2015年1月20日 下午5:10:40
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
import com.lzy.block.api.vo.activiti.ProcessDefInfo;
import com.lzy.block.core.service.activiti.IActivitiProcessService;

/**
 * @ClassName: ActivitiProcessController
 * @Description: 工作流流程 
 * @author 李志勇
 * @date 2015年1月20日 下午5:10:40
 *
 */
@Controller
@RequestMapping(value = "/activitiProcess")
public class ActivitiProcessController {
	
	private static Logger logger = Logger.getLogger(ActivitiProcessController.class.getName());
	
	@Resource
	private IActivitiProcessService activitiProcessService;
	
	/**
	 * 
	 * @Title: myStartProcess
	 * @Description: 我发起的流程
	 * @param processName
	 * @param pageModel
	 * @return: Map<String,Object>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/myStartProcess")
	public  Map<String, Object>  myStartProcess(String processName,PageModel pageModel) {
		Pagination<ProcessDefInfo> pm = new Pagination<ProcessDefInfo>();
		try {				
			pm = activitiProcessService.getMyStartProcess("kafeitu", pageModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}

	/**
	 * 
	 * @Title: finishProcess
	 * @Description: 我参与的已经完成流程
	 * @param processName
	 * @param pageModel
	 * @return: Map<String,Object>
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/finishProcess")
	public  Map<String, Object>  finishProcess(String processName,PageModel pageModel) {
		Pagination<ProcessDefInfo> pm = new Pagination<ProcessDefInfo>();
		try {				
			pm = activitiProcessService.getMyJoinFinishedProcess("kafeitu", pageModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/myJoinProcess")
	public  Map<String, Object>  myJoinProcess(String processName,PageModel pageModel) {
		Pagination<ProcessDefInfo> pm = new Pagination<ProcessDefInfo>();
		try {				
			pm = activitiProcessService.getMyJoinProcess("kafeitu", pageModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
}
