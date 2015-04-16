/**  
* @Title: LoginController.java
* @Package com.lzy.block.console.controller.shiro
* @author 李志勇  
* @date 2015年4月14日 上午11:46:18
* @version V1.0  
*/ 
package com.lzy.block.console.controller.shiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzy.block.api.exception.CheckingCodeException;

/**
 * @ClassName: LoginController
 * @Description: 登录controller 
 * @author 李志勇
 * @date 2015年4月14日 上午11:46:18
 *
 */
@Controller
public class LoginController {
	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if(CheckingCodeException.class.getName().equals(exceptionClassName)){
			error="验证码错误!";
		}else if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户不存在!";
		}else if(LockedAccountException.class.getName().equals(exceptionClassName)){
			error="账号被锁定!";
		}else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		if (req.getParameter("forceLogout") != null) {
			model.addAttribute("error", "您已经被管理员强制退出，请重新登录");
		}
		return "/shiro/login";
	}
	/*
	@RequestMapping(value = "/authenticated")
	public String authenticated(HttpServletRequest req, Model model) {
		System.out.println("11111111");
		return "http://www.baidu.com";
	}
	*/
}
