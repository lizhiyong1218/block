/**  
* @Title: RedisConstants.java
* @Package com.lzy.block.api.common
* @author 李志勇  
* @date 2014年12月26日 下午2:46:19
* @version V1.0  
*/ 
package com.lzy.block.api.constant.ehcache;

/**
 * 
* @ClassName: RedisConstants
* @Description: redis常量类 
* @author 李志勇
* @date 2014年12月26日 下午2:46:52
*
 */
public class EhcacheConstants {

	 /***********缓存队列***********/
    /*账号登录人数缓存*/
    public static final String USERINFOCACHE = "userInfoCache";
    /*账号登录人数缓存*/
    public static final String ACCOUNSESSIONCACHE = "accountSessionCache";
	
    /***********缓存KEY前缀***********/
	/*用户名称key前缀*/
	public static final String USERNAMEKEYPRE = "user-info-name-";  
	/*用户id key前缀*/
	public static final String USERIDKEYPRE = "user-info-id-";  
	
	
}
