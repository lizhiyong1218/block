package com.lzy.block.core.redis;

public interface IRedisCacheClient {
	public void listLpush(String key,Object o);
	
	public String listLpop(String key);
	
	public void setKey(String key,Object o);
	
	public Object getKey(String key);
	
	public void removeKey(String key);
}
