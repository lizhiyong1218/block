/**  
* @Title: CommonController.java
* @Package com.lzy.block.console.controller.common
* @author 李志勇  
* @date 2014年11月17日 下午2:57:15
* @version V1.0  
*/ 
package com.lzy.block.console.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzy.block.console.common.ProjectUtil;

/**
 * @ClassName: CommonController
 * @Description: 公共控制器 
 * @author 李志勇
 * @date 2014年11月17日 下午2:57:15
 *
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

	private static Logger logger = Logger.getLogger(CommonController.class.getName());	
	
/*	@RequestMapping(value="/toPage")
	public String listAllPage(String pagePath){
		System.out.println("redirect:"+pagePath);
//		 return "redirect:"+pagePath;
		return pagePath;
	}*/
	
	@RequestMapping(value="/toListPage")
	public ModelAndView toListPage(String pagePath, HttpServletRequest request){
		logger.debug("toListPage in");
		logger.info(pagePath+"==");
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("basePath",  ProjectUtil.getBasePath(request));
		modelMap.addAttribute("jsVersion", ProjectUtil.getJsVersion(request));
		return new ModelAndView(pagePath,modelMap);
	}
	
}
