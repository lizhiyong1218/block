package com.lzy.block.core.dao.user;

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.dao.base.BaseMapper;


public interface UserMapper extends BaseMapper<User> {
	
    public User getByName(String userName) ;
    
    public User getOneByNamePrefix(String name);
    
    
}