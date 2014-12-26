/**  
* @Title: RedisLockModel.java
* @Package com.lzy.block.core.redis.util.distributedlock
* @author 李志勇  
* @date 2014年12月24日 上午11:38:26
* @version V1.0  
*/ 
package com.lzy.block.api.vo;

import java.io.Serializable;

/**
 * @ClassName: RedisLockModel
 * @Description: 锁对象 
 * @author 李志勇
 * @date 2014年12月24日 上午11:38:26
 *
 */
public class RedisLockModel implements Serializable{
	
	private static final long serialVersionUID = -511749217458480239L;

	//锁id    对应value,用uuid值来标志
    private String lockId;  
  
    //锁name  对应key
     
    private String lockName;  
  
    public RedisLockModel(String lockName){  
        this.lockName = lockName;  
    }

	public String getLockId() {
		return lockId;
	}

	public void setLockId(String lockId) {
		this.lockId = lockId;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	@Override
	public String toString() {
		return "RedisLockModel [lockId=" + lockId + ", lockName=" + lockName
				+ "]";
	}
    
    
    
}
