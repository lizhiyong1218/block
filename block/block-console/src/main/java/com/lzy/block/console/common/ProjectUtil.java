/**  
* @Title: ProjectUtil.java
* @Package com.lzy.block.core.common.utils
* @author 李志勇  
* @date 2014年11月17日 下午4:49:57
* @version V1.0  
*/ 
package com.lzy.block.console.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @ClassName: ProjectUtil
 * @Description: 项目工具类 
 * @author 李志勇
 * @date 2014年11月17日 下午4:49:57
 *
 */
public class ProjectUtil {
	private static Logger logger = Logger.getLogger(ProjectUtil.class);
	
	public static final String jsVerison=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	/**
	 * 获得工程js文件路径
	 * @param request
	 * @return  
	 * @throws
	 */
	/*public static String getBootPath(HttpServletRequest request){
		HttpSession session = request.getSession();
		String bootPath=(String) session.getAttribute("bootPath");
		if(bootPath==null){
			bootPath=PropertisUtil.newInstance().getPropValue("bootPath");
			session.setAttribute("bootPath", bootPath);
			logger.info("================bootPath:"+bootPath);
		} 
		return bootPath;
	} */
	
	public static String getJsVersion(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		String jsVersion=(String) session.getAttribute("jsVersion");
//		if(jsVersion==null){
//			SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMddHHmmss");
//			jsVersion=fmt.format(new Date());
//			session.setAttribute("jsVersion", jsVersion);
//			logger.info("================jsVersion:"+jsVersion);
//		} 
		return ProjectUtil.jsVerison;
	}
	
	public static String getBasePath(HttpServletRequest request){
		HttpSession session = request.getSession();
		String basePath=(String) session.getAttribute("basePath");
		if(basePath==null){
			String path = request.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
			session.setAttribute("basePath", basePath);
			logger.info("================basePath:"+basePath);
		} 
		return basePath;
	}
}
