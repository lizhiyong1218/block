package com.lzy.block.core.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lzy.block.core.redis.IRedisCacheClient;

@Service("redisCacheClient")
public class RedisCacheClientImpl implements IRedisCacheClient{

	@Autowired
	private RedisTemplate<String,Object>  redisTemplate;
	
	@Override
	public void listLpush(String key, Object o) {
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		opsForList.leftPush(key, o);
	}

	@Override
	public String listLpop(String key) {
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		return  (String) opsForList.leftPop(key);
	}

	@Override
	public void setKey(String key, Object o) {
		redisTemplate.opsForValue().set(key, o);
	}

	@Override
	public Object getKey(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void removeKey(String key) {
		redisTemplate.delete(key);
	}

 

}
