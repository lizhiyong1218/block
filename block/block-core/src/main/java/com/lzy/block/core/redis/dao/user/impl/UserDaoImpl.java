/**  
* @Title: UserDaoImpl.java
* @Package com.lzy.block.core.redis.dao.user.impl
* @author 李志勇  
* @date 2014年12月2日 下午5:01:04
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;  
import org.springframework.data.redis.connection.RedisConnection;  
import org.springframework.data.redis.core.RedisCallback;  
import org.springframework.data.redis.serializer.RedisSerializer;  
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert; 

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.redis.AbstractBaseRedisDao;
import com.lzy.block.core.redis.dao.user.IUserDao;

/**
 * @ClassName: UserDaoImpl
 * @Description: user redis实现 
 * @author 李志勇
 * @date 2014年12月2日 下午5:01:04
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseRedisDao<String, User> implements IUserDao{

	/**  
     * 新增 
     *<br>------------------------------<br> 
     * @param user 
     * @return 
     */  
    public boolean add(final User user) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(user.getUserId().toString());  
                byte[] name = serializer.serialize(user.toString());  
                return connection.setNX(key, name);  
            }  
        });  
        return result;  
    }  
      
    /** 
     * 批量新增 使用pipeline方式   
     *<br>------------------------------<br> 
     *@param list 
     *@return 
     */  
    public boolean add(final List<User> list) {  
        Assert.notEmpty(list);  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                for (User user : list) {  
                    byte[] key  = serializer.serialize(user.getUserId().toString());  
                    byte[] name = serializer.serialize(user.getUserName());  
                    connection.setNX(key, name);  
                }  
                return true;  
            }  
        }, false, true);  
        return result;  
    }  
      
    /**  
     * 删除 
     * <br>------------------------------<br> 
     * @param key 
     */  
    public void delete(String key) {  
        List<String> list = new ArrayList<String>();  
        list.add(key);  
        delete(list);  
    }  
  
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    public void delete(List<String> keys) {  
        redisTemplate.delete(keys);  
    }  
  
    /** 
     * 修改  
     * <br>------------------------------<br> 
     * @param user 
     * @return  
     */  
    public boolean update(final User user) {  
        String key = user.getUserId().toString();  
        if (get(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        }  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(user.getUserId().toString());  
                byte[] name = serializer.serialize(user.getUserName());  
                connection.set(key, name);  
                return true;  
            }  
        });  
        return result;  
    }  
  
    /**  
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return 
     */  
    public User get(final String keyId) {  
        User result = redisTemplate.execute(new RedisCallback<User>() {  
            public User doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(keyId);  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String name = serializer.deserialize(value);  
                return new User(Integer.parseInt(keyId), name, null);  
            }  
        });  
        return result;  
    } 
    
    public void listOp(){
    	User user=new User();
    	user.setUserId(1);
    	user.setUserName("test");
    	user.setUserPwd("pwd");
    	redisTemplate.opsForList().rightPush("users",user );
    }
    

}
