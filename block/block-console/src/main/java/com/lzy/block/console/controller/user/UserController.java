/**  
* @Title: UserController.java
* @Package com.lzy.block.console.controller.user
* @author 李志勇  
* @date 2014年11月14日 下午3:29:24
* @version V1.0  
*/ 
package com.lzy.block.console.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.User;

/**
 * @ClassName: UserController
 * @Description: 用户信息 
 * @author 李志勇
 * @date 2014年11月14日 下午3:29:24
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	
	@RequestMapping(value="/userList",produces={"application/json;charset=UTF-8"})
	public String listAllPage(){
		return "/user/userList";
	}
	

	/**
	 * 视频列表
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@RequestMapping(value="/getList",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object>  showList(Integer page,Integer rows){
		Pagination<User> pagination=new Pagination<User>();
		User user=null;
		List<User> list=new ArrayList<User>();
		for(int i=0;i<10;i++){
			user=new User();
			user.setUserId(i);
			user.setUserName("name"+i);
			user.setUserPwd("pwd"+i);
			list.add(user);
		}
		pagination.setRecordList(list);
		pagination.setRecordCount(50);
		ModelMap map=new ModelMap();
		map.put("rows", pagination.getRecordList());  
		map.put("total", pagination.getRecordCount()); 
		return map;
	}
	
	 
	
}
