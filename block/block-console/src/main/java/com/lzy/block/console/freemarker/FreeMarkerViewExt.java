/**
 * 
 */
package com.lzy.block.console.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.lzy.block.console.freemarker.directive.CustomerServiceDirective;

import freemarker.template.SimpleHash;

/**
 * @author lizhiyong
 *
 */
public class FreeMarkerViewExt extends FreeMarkerView{
	
	@Override
	protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		//方便在模板文件中应用${ctx}
		model.put("ctx", request.getContextPath());
		model.put("base", request.getContextPath() + "/themes/default");
//		model.put("user", SessionUser.get());//用户信息
		//客服信息宏命令
		model.put("customerServiceDirective", ContextLoader.getCurrentWebApplicationContext().getBean("customerServiceDirective"));
		return super.buildTemplateModel(model, request, response);
	}
}
