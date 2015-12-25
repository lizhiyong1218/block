package com.lzy.block.console.freemarker.directive;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.MapModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 客服信息
 * @author lizhiyong
 *
 */
//@Component("customerServiceDirective")
public class CustomerServiceDirective implements TemplateDirectiveModel{

	private static Logger logger=Logger.getLogger(CustomerServiceDirective.class);
	
//	@Resource
//	CustomerServiceInfoService customerServiceInfoService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> valueMap = new HashMap<String, Object>();//传到页面的值
		try {
			Object cityStr = params.get("city");//从页面传入的值
			Object size = params.get("size");
			logger.debug(cityStr);
			logger.debug(size);
			valueMap.put("qq", "1233"); 
	        if (body != null) {
	            if (loopVars.length > 0) {
	                MapModel model = new MapModel(valueMap, new BeansWrapper());
	                loopVars[0] = model;
	            }
	            body.render(env.getOut());
	        }
		} catch (Exception e) {
			logger.error("获取客服信息出错！",e);
		}
	}

}


