/**  
* @Title: AbstractBaseRedisDao.java
* @Package com.lzy.block.core.redis
* @author 李志勇  
* @date 2014年12月2日 下午4:55:59
* @version V1.0  
*/ 
package com.lzy.block.core.redis;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.data.redis.serializer.RedisSerializer; 

/**
 * @ClassName: AbstractBaseRedisDao
 * @Description: redis基类 
 * @author 李志勇
 * @date 2014年12月2日 下午4:55:59
 *
 */
public abstract class AbstractBaseRedisDao<K, V> {  
    
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
  
    /** 
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * 获取 RedisSerializer 
     * <br>------------------------------<br> 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
}  
