/**  
* @Title: UserServiceTest.java
* @Package org.block.core.user
* @author 李志勇  
* @date 2015年4月14日 下午5:50:13
* @version V1.0  
*/ 
package org.block.core.user;

import javax.annotation.Resource;

import org.block.core.BaseTest;
import org.junit.Test;

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.service.user.IUserService;

/**
 * @ClassName: UserServiceTest
 * @Description: userTest 
 * @author 李志勇
 * @date 2015年4月14日 下午5:50:13
 *
 */
public class UserServiceTest extends BaseTest{
	@Resource
	IUserService userService;
	
	@Test
	public void testGetByName(){
		User byName = userService.getByName("admin");
		logger.info(byName+"=====");
		
	}
	
	@Test
	public void testGetById(){
		User byId = userService.getById(1);
		logger.info(byId);
	}
	
}
