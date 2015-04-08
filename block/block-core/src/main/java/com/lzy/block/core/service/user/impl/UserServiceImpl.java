
/**     
 * @FileName: UserServiceImpl.java   
 * @Package:com.test.service.impl   
 * @Description: 用户service实现  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.dao.user.UserMapper;
import com.lzy.block.core.service.base.impl.BaseServiceImpl;
import com.lzy.block.core.service.user.IUserService;

 
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Resource
	private UserMapper userMapper;
	
	@Override
	@CachePut(value = "user", key = "#user.userId")  
	public User insertUser(User user){
		userMapper.insertSelective(user);
		return user;
	}

	@Override
	@Cacheable(value = "user", key = "#userId")  
	public User getById(Integer userId) {
		 return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@CacheEvict(value = "user", key = "#userId") 
	public int deleteById(Integer userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	
	@Override
	@CachePut(value = "user", key = "#userName")  
	public User getByName(String userName) {
		return userMapper.getByName(userName); 
	}

	@Override
	protected BaseMapper<User> getMapper() {
		return userMapper;
	}
}
