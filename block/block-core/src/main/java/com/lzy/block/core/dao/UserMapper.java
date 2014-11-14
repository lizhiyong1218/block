package com.lzy.block.core.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.User;

/**
 * 
 * @author: ZhiYong.Li  
 * @date:2014年10月13日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有
 */
public interface UserMapper {
	
    public int insert(User user);
    
	
    public void delete(Integer id);
    
    public int update(User user);
    
    
    public User getOneById(Integer id);
    
    public User getByName(String userName) ;
    
    public User getOneByNamePrefix(String name);
    
    public List<User> findAll(User user);
    
    public PageList<User> findAll(User user, PageBounds pageBounds);
    
}