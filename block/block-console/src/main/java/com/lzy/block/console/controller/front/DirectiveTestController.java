/**
 * 
 */
package com.lzy.block.console.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *	自定义标签
 */
@Controller
@RequestMapping("/front/directiveTest")
public class DirectiveTestController {
	
	@RequestMapping(value="/testPage")
	public String toPage(ModelMap model,String pagePath){
		return "directiveTest";
	}
}
