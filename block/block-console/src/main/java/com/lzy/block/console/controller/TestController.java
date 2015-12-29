/**
 * 
 */
package com.lzy.block.console.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.core.service.dictionary.IDictionaryItemService;

/**
 * @author 测试控制器
 *
 */
@Controller
@RequestMapping("test/")
public class TestController {

	Logger logger=Logger.getLogger(TestController.class);
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	/**
	 * 测试400报错
	 * @return
	 */
	@RequestMapping("test404")
	public String test404(){
		return "xxxxx";
	}
	
	/**
	 * 测试异常页面跳转
	 * @throws Exception
	 */
	@RequestMapping("test500")
	public void test500() throws Exception{
		throw new Exception("异常测试");
	}
	 
	
}
