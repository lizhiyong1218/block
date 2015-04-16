/**  
* @Title: UserServiceTest.java
* @Package org.block.core.user
* @author 李志勇  
* @date 2015年4月14日 下午5:50:13
* @version V1.0  
*/ 
package org.block.core.ehcache;

import javax.annotation.Resource;

import org.block.core.BaseTest;
import org.junit.Test;

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.service.user.IUserService;

/**
 * @ClassName: UserServiceTest
 * @Description: EhcacheTest 
 * @author 李志勇
 * @date 2015年4月14日 下午5:50:13
 *
 */
public class EhcacheTest extends BaseTest{
	@Resource
	IUserService userService;
	
	@Test
	public void testCacheable(){
		User byName = userService.getByName("admin");
		logger.info(byName+"=====");
		User byName2 = userService.getByName("admin");
		logger.info(byName2);
	}
	
	@Test
	public void testCachePut(){
		User user=new User();
		user.setUserName("lzy");
		userService.insertUser(user);
		User byName = userService.getByName("lzy");
		logger.info(byName);
	}
	
	@Test
	public void testCacheEvict(){
		User byId1 = userService.getById(3);
		logger.info(byId1);
		userService.deleteById(3);
		User byId2 = userService.getById(3);
		logger.info(byId2);
	}
	
}
