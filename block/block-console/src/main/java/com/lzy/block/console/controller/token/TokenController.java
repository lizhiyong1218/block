package com.lzy.block.console.controller.token;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/token")
public class TokenController {
	
	@RequestMapping(value="/tokenTest",produces={"application/json;charset=UTF-8"})
	public String tokenTest(){
		return "/token/tokenTest";
	}
	
	@RequestMapping(value="/subform",produces={"application/json;charset=UTF-8"})
	public String subform(){
		return "/token/res";
	}
}
