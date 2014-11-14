
/**     
 * @FileName: UserServiceImpl.java   
 * @Package:com.test.service.impl   
 * @Description: 用户service实现  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lzy.block.api.model.User;
import com.lzy.block.core.dao.UserMapper;
import com.lzy.block.core.service.IUserService;


/**       
 * @author: ZhiYong.Li  
 * @date:2014年10月16日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有   
 */
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;
	
	@Override
	@CachePut(value = "user", key = "#user.userId")  
	public User insert(User user){
		userMapper.insert(user);
		return user;
	}

	@Override
	@Cacheable(value = "user", key = "#userId")  
	public User getById(Integer userId) {
		 return userMapper.getOneById(userId);
	}

	@Override
	@CacheEvict(value = "user", key = "#userId") 
	public void deleteById(Integer userId) {
		userMapper.delete(userId);
	}
}
