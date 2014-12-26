/**  
* @Title: IRedisLockService.java
* @Package com.lzy.block.core.redis
* @author 李志勇  
* @date 2014年12月25日 下午5:26:43
* @version V1.0  
*/ 
package com.lzy.block.core.redis;

import com.lzy.block.api.vo.RedisLockModel;


/**
 * @ClassName: IRedisLockService
 * @Description: redis锁 
 * @author 李志勇
 * @date 2014年12月25日 下午5:26:43
 *
 */
public interface IRedisLockService {
	
	/**
	 * 
	* @Title: acquireLock
	* @Description: 获取锁
	* @param key 
	* @param maxWait  最长的添加锁时的等待时间(单位:ms)
	* @param expiredTime  设置锁的过期时间(单位:s?) 
	* @return:     
	* RedisLockModel    
	* @throws
	 */
	public RedisLockModel acquireLock(final String key,final Integer maxWait,final Integer expiredTime);
	
	/**
	 * 
	* @Title: acquireLock
	* @Description: 获取锁
	* @param key
	* @return:     
	* RedisLockModel    
	* @throws
	 */
	public RedisLockModel acquireLock(final String key);
	
	
	 /**
     * 
    * @Title: releaseLock
    * @Description: 删除锁
    * @param redisLockModel
    * @return:     
    * boolean    
    * @throws
     */
	public boolean releaseLock(final RedisLockModel redisLockModel);
	
	/** 
     * 通过获得的锁编号判断锁添加是否成功 
     * @param redisLockModel    redis锁对象 
     * @return  Boolean 
     * 返回是否添加成功 
     */ 
	public boolean ACQUIRE_RESULT(RedisLockModel redisLockModel);
}
