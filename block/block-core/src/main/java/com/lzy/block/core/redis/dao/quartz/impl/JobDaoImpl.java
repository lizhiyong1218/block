/**  
* @Title: JobDaoImpl.java
* @Package com.lzy.block.core.redis.dao.quartz.impl
* @author 李志勇  
* @date 2014年12月12日 下午3:35:12
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.quartz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.lzy.block.api.model.quartz.ScheduleJob;
import com.lzy.block.core.redis.dao.quartz.IJobDao;

/**
 * @ClassName: JobDaoImpl
 * @Description: jobdao 
 * @author 李志勇
 * @date 2014年12月12日 下午3:35:12
 *
 */
@Component
public class JobDaoImpl implements IJobDao {
	
	@Autowired
	private RedisTemplate<String,ScheduleJob>  redisTemplate;
	
	
//	@Override
//	public boolean add(PriceRecord priceRecord) {
//		StringBuffer sb=new StringBuffer();
//		sb.append(priceRecord.getAuctionId()).append(priceRecord.getPrice());
//		ValueOperations<String, PriceRecord> valueops = redisTemplate
//				                .opsForValue();
//		return valueops.setIfAbsent(sb.toString(), priceRecord);
//		
//	}
//
//	@Override
//	public PriceRecord getPrice(String key) {
//		ValueOperations<String, PriceRecord> valueops = redisTemplate.opsForValue();
//		PriceRecord record = valueops.get(key);
//		return record;
//	}

	@Override
	public boolean add(String key,ScheduleJob job) {
		ValueOperations<String, ScheduleJob> valueops = redisTemplate
        .opsForValue();
		return valueops.setIfAbsent(key, job);
	}

	@Override
	public boolean add(List<ScheduleJob> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
		
	}

	@Override
	public void delete(List<String> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(ScheduleJob job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScheduleJob get(String keyId) {
		ValueOperations<String, ScheduleJob> valueops = redisTemplate.opsForValue();
		ScheduleJob record = valueops.get(keyId);
		return record;
	}

}
