/**  
* @Title: RedisLockUtil.java
* @Package com.lzy.block.core.redis.util
* @author 李志勇  
* @date 2014年12月24日 下午5:53:27
* @version V1.0  
*/ 
package com.lzy.block.core.redis.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;


import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.lzy.block.api.common.StrUtil;
import com.lzy.block.api.constant.redis.RedisConstants;
import com.lzy.block.api.vo.RedisLockModel;
import com.lzy.block.core.redis.IRedisLockService;


/**
 * @ClassName: RedisLockUtil
 * @Description: 锁工具 
 * @author 李志勇
 * @date 2014年12月24日 下午5:53:27
 *
 */
@Service
public class RedisLockServiceImpl implements IRedisLockService{
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockServiceImpl.class);
	
	@Autowired
	private RedisTemplate<String,String>  redisTemplate;
	
	/**
	 * 
	* @Title: acquireLock
	* @Description: 获取锁
	* @param key 
	* @param maxWait  最长的添加锁时的等待时间(单位:ms)
	* @param expiredTime  设置锁的过期时间(单位:s) 
	* @return:     
	* RedisLockModel    
	* @throws
	 */
	@Override
	public  RedisLockModel acquireLock(final String key,final Integer maxWait,final Integer expiredTime) {  
        //随即获取一个uuid，作为一个key的标识区别与，同名lock  
        final String uuid = UUID.randomUUID().toString();  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] keyByte  = serializer.serialize(key);  
                byte[] valueByte = serializer.serialize(uuid);  
                
                //获取锁的最多等待时间
                long expireAt = System.currentTimeMillis()+maxWait;  
                //循环判断锁是否一直存在  
                while (System.currentTimeMillis() < expireAt){  
                    LOGGER.debug("try lock key={} ", key);  
                    if (connection.setNX(keyByte, valueByte)) { //设置成功 
                    	connection.expire(keyByte, expiredTime);
                        LOGGER.debug("get lock, key={}, expire in seconds={}", key, expiredTime);  
                        return true;  
                    } else {  
                        // 存在锁（会一直执行（在最大等待时间范围内）等待该锁释放）  
                        String desc = connection.get(keyByte).toString();  
                        LOGGER.debug("key={} locked by another business={}", key, desc);  
                    }  
  
                    try{  
                        TimeUnit.MILLISECONDS.sleep(20);  
                    }catch (InterruptedException e) {  
                        Thread.currentThread().interrupt();  
                        LOGGER.error("sleep error !"+e);
                    }  
                }  
                //在最大等待时间范围内未获取到锁  
                return false;  
            }  
        });  
        RedisLockModel redisLockModel = new RedisLockModel(key);  
        redisLockModel.setLockId(result ? uuid : null);  
        return redisLockModel;  
    }
	
	
	/** 
     * 通过获得的锁编号判断锁添加是否成功 
     * @param redisLockModel    redis锁对象 
     * @return  Boolean 
     * 返回是否添加成功 
     */  
	@Override
    public  boolean ACQUIRE_RESULT(RedisLockModel redisLockModel){  
    	return StrUtil.isNotEmpty(redisLockModel.getLockId());
    }
	
    /**
     * 
    * @Title: releaseLock
    * @Description: 删除锁
    * @param redisLockModel
    * @return:     
    * boolean    
    * @throws
     */
    @Override
    public  boolean releaseLock(final RedisLockModel redisLockModel) {
    	return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				 RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                 byte[] keyByte  = serializer.serialize(redisLockModel.getLockName());  
				
				 //防止多个服务器处理同一个key需要watch操作(相当于是禁止了其他client处理这个key)  
//				connection.watch(keyByte);  
				LOGGER.info(redisLockModel.getLockId()+"======="+serializer.deserialize(connection.get(keyByte)));
				//如果锁没有过期,则锁的值必然没有改变  
                if (redisLockModel.getLockId().equals(serializer.deserialize(connection.get(keyByte)))) {
                    //删除锁  
                	long n= connection.del(keyByte);
                	LOGGER.debug("del key:"+redisLockModel.getLockName()+"-->"+n);
                    return true;  
                }  
//                //锁已经过期了，释放watch操作  
//                connection.unwatch();  
                return false;  
			}
		});
    }


	@Override
	public RedisLockModel acquireLock(String key) {
		return acquireLock(key, RedisConstants.DEFAULT_GET_LOCK_WATTIME, RedisConstants.DEFAULT_SINGLE_EXPIRE_TIME);
	}
	
}
