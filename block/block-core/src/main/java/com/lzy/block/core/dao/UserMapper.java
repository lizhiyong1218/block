package com.lzy.block.core.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.User;


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