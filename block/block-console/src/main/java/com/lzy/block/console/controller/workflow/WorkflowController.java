/**  
* @Title: WorkflowController.java
* @Package com.lzy.block.console.workflow
* @author 李志勇  
* @date 2015年1月9日 上午10:14:20
* @version V1.0  
*/ 
package com.lzy.block.console.controller.workflow;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.common.StrUtil;
import com.lzy.block.api.constant.common.ResultStatus;
import com.lzy.block.api.vo.ResultVo;
import com.lzy.block.api.vo.activiti.ProcessDefInfo;
import com.lzy.block.console.common.ProcessDefinitionCache;
import com.lzy.block.core.service.activiti.IActivitiService;

/**
 * @ClassName: WorkflowController
 * @Description: 工作流控制器 
 * @author 李志勇
 * @date 2015年1月9日 上午10:14:20
 *
 */
@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {
	
	private static Logger logger = Logger.getLogger(WorkflowController.class.getName());
	
	@Autowired
	protected RepositoryService repositoryService;
	/*@Autowired
	private FormService formService;*/
	@Resource
	private IActivitiService activitiService;
	
	
	/**
	 * 
	* @Title: dictionaryList
	* @Description: 获取流程部署信息
	* @param processName
	* @param page
	* @param rows
	* @return:     
	* Map<String,Object>    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/process/list")
	public  Map<String, Object>  workflowList(String processName,int page,int rows) {
		Pagination<ProcessDefInfo> pm = new Pagination<ProcessDefInfo>();
		try {				
			
			//查询工作流部署信息
			ProcessDefinitionQuery processDefinitionQuery = repositoryService
					.createProcessDefinitionQuery().orderByDeploymentId().desc();
			if(StrUtil.isNotEmpty(processName)){
				processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike("%"+processName+"%");
			}
			List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(page, rows);
			
			//如果不将ProcessDefinition转换为ProcessDefInfo,在easyui datagrid渲染时报错
			List<ProcessDefInfo> ProcessDefInfos = new ArrayList<ProcessDefInfo>();
			ProcessDefInfo defInfo=null;
			for (ProcessDefinition processDefinition : processDefinitionList) {
				/*String formKey = formService.getStartFormKey(processDefinition
						.getId())*/;
				defInfo = new ProcessDefInfo();
				defInfo.setId(processDefinition.getId());
//				defInfo.setStartFormKey(formKey);
				defInfo.setCategory(processDefinition.getCategory());
				defInfo.setName(processDefinition.getName());
				defInfo.setVersion(processDefinition.getVersion());
				defInfo.setResourceName(processDefinition.getResourceName());
				defInfo.setDiagramResourceName(processDefinition.getDiagramResourceName());
				defInfo.setDeploymentId(processDefinition.getDeploymentId());
				ProcessDefInfos.add(defInfo);
			} 
			pm.setRecordList(ProcessDefInfos);
			pm.setRecordCount(processDefinitionQuery.count());
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
	 * @Title: loadDeployment
	 * @Description: 读取文件(流程图，流程xml文件)
	 * @param processDefinitionId 流程id
	 * @param resourceType 文件类型
	 * @param response
	 * @throws Exception
	 *             : void
	 * @throws
	 */
	@RequestMapping(value = "/resource/read")
	public void loadDeployment(
			@RequestParam("processDefinitionId") String processDefinitionId,
			@RequestParam("resourceType") String resourceType,
			HttpServletResponse response) throws Exception {
		ProcessDefinition processDefinition =
				ProcessDefinitionCache.get(processDefinitionId, repositoryService);
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		response.setCharacterEncoding("UTF-8");
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 
	 * @Title: deploy
	 * @Description: 部署新流程
	 * @param file
	 * @return: ResultVo
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/process/deploy")
    public ResultVo deploy(HttpServletRequest request,@RequestParam(value = "houseMaps", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String exportPath=getForlderPath(request);
		ResultVo res=new ResultVo();
		try {
			InputStream fileInputStream = file.getInputStream();
			activitiService.addDeploy(fileInputStream, fileName,
					exportPath);
			res.setMessage("部署成功!");
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error("部署失败:",e);
			res.setMessage("部署失败!"+e.getMessage());
			res.setStatus(ResultStatus.FAILURE.value());
		}
		return res;
    }
	
	/**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId 流程部署ID
     */
    @RequestMapping(value = "/process/delete")
    @ResponseBody
    public ResultVo delete(@RequestParam("deploymentId") String deploymentId) {
    	ResultVo res=new ResultVo();
        try {
			repositoryService.deleteDeployment(deploymentId, true);
			logger.error("删除流程成功!id:"+deploymentId);
			res.setMessage("删除流程成功!");
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error("删除流程失败!id:"+deploymentId,e);
			res.setMessage("删除失败!"+e.getMessage());
			res.setStatus(ResultStatus.FAILURE.value());
		}
        return res;
    }
    
	/**
	 * 
	 * @Title: taskAssigneeConfigPage
	 * @Description: 跳转到配置页面
	 * @param processDefinitionId
	 * @return: ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/taskAssigneeConfigPage")
	public ModelAndView taskAssigneeConfigPage(String processDefinitionId){
		ModelMap modelMap=new ModelMap();
		logger.info("processDefinitionId:"+processDefinitionId);
		modelMap.addAttribute("processDefinitionId", processDefinitionId);
		return new ModelAndView("/activiti/taskAssigneeConfig",modelMap);
	}
    
    @ResponseBody
    @RequestMapping("/selectUser")
	public ResultVo selectUser() {
    	ResultVo res=new ResultVo();
		try {
			 res.setMessage(activitiService.getUserGroupJsonTree());
			 res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error("获取组织结构失败！"+ e.getMessage());
			res.setMessage("取组织结构失败!"+e.getMessage());
			res.setStatus(ResultStatus.FAILURE.value());
		}
		return res;
	}  
    
    
    /**
	 * 输出跟踪流程用户任务信息
	 * 
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/process/traceTaskDefinations")
	@ResponseBody
	public Map<String, Object> traceTaskDefinations(
			@RequestParam("processDefinitionId") String processDefinitionId) throws Exception {
		List<Map<String, Object>> taskDefinations = activitiService
				.traceTaskDefinations(processDefinitionId);
		ModelMap map=new ModelMap();
		map.put("taskDefinations", taskDefinations);  
		return map;
	}
    
    
    /**
	 * 获得生成 文件的保存目录
	 */
	private String getForlderPath(HttpServletRequest request){
		 String forlderName = "/activiti/";  
	     String realPathDir = request.getSession().getServletContext().getRealPath(forlderName);     
	     File forlder = new File(realPathDir);       
	     if(!forlder.exists()){
	    	 forlder.mkdirs();  
	     }
	     logger.debug(forlder.getAbsolutePath());
	     return realPathDir;
	}
	
}
