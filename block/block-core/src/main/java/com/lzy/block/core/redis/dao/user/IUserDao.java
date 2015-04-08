/**  
* @Title: IUserDao.java
* @Package com.lzy.block.core.redis.dao
* @author 李志勇  
* @date 2014年12月2日 下午4:58:40
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.user;

import java.util.List;

import com.lzy.block.api.model.user.User;

/**
 * @ClassName: IUserDao
 * @Description: userDao 
 * @author 李志勇
 * @date 2014年12月2日 下午4:58:40
 *
 */
public interface IUserDao {  
    
    /** 
     * 新增 
     * <br>------------------------------<br> 
     * @param user 
     * @return 
     */  
    boolean add(User user);  
      
    /** 
     * 批量新增 使用pipeline方式 
     * <br>------------------------------<br> 
     * @param list 
     * @return 
     */  
    boolean add(List<User> list);  
      
    /** 
     * 删除 
     * <br>------------------------------<br> 
     * @param key 
     */  
    void delete(String key);  
      
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    void delete(List<String> keys);  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     * @param user 
     * @return  
     */  
    boolean update(User user);  
  
    /** 
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return  
     */  
    User get(String keyId);  
    
    public void listOp();
    
    
}  
