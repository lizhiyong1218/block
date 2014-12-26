/**  
 * @Title: PriceRecordTest.java
 * @Package org.block.core.redis
 * @author 李志勇  
 * @date 2014年12月5日 上午10:05:34
 * @version V1.0  
 */
package org.block.core.redis;


import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.vo.RedisLockModel;
import com.lzy.block.core.redis.IRedisLockService;

/**
 * @ClassName: PriceRecordTest
 * @Description: 价格
 * @author 李志勇
 * @date 2014年12月5日 上午10:05:34
 * 
 */
public class RedisLockTest extends BaseTest {
	@Autowired
	IRedisLockService redisLockService;

	@Test
	public void testAddLock() {
		//创建锁
		String key = "sss";
		RedisLockModel acquireLock = redisLockService.acquireLock(key);
		System.out.println(acquireLock);
		//删除锁
		boolean releaseLock = redisLockService.releaseLock(acquireLock);
		System.out.println(releaseLock);
	}

}
