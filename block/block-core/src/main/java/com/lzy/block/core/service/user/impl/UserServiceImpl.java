
/**     
 * @FileName: UserServiceImpl.java   
 * @Package:com.test.service.impl   
 * @Description: 用户service实现  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service.user.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lzy.block.api.constant.ehcache.EhcacheConstants;
import com.lzy.block.api.model.user.User;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.dao.user.UserMapper;
import com.lzy.block.core.service.base.impl.BaseServiceImpl;
import com.lzy.block.core.service.user.IUserService;

 
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Resource
	private UserMapper userMapper;
	
	private static Logger logger=Logger.getLogger(UserServiceImpl.class.getName());
	
	
	@Override
	@CachePut(value = "userInfoCache", key = "'"+EhcacheConstants.USERNAMEKEYPRE+"'+#user.userName")  
//	@CachePut(value = "userInfoCache", key = "#user.userName")  
	public User insertUser(User user){
		userMapper.insertSelective(user);
		return user;
	}

	@Override
	@Cacheable(value = "userInfoCache", key = "'"+EhcacheConstants.USERIDKEYPRE+"'+#userId")  
	public User getById(Integer userId) {
		logger.info("从数据库中获取");
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@CacheEvict(value = "userInfoCache", key = "'"+EhcacheConstants.USERIDKEYPRE+"'+#userId") 
	public int deleteById(Integer userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	
	@Override
//	@CachePut(value = "userInfoCache", key = "#userName")  
	@Cacheable(value = "userInfoCache", key = "'"+EhcacheConstants.USERNAMEKEYPRE+"'+#userName")  
	public User getByName(String userName) {
		logger.info("从数据库中获取");
		return userMapper.getByName(userName); 
	}

	@Override
	protected BaseMapper<User> getMapper() {
		return userMapper;
	}
}
