/**  
* @Title: Session.java
* @Package com.lzy.block.api.model.shiro
* @author 李志勇  
* @date 2015年4月20日 上午9:53:47
* @version V1.0  
*/ 
package com.lzy.block.api.model.shiro;

import java.util.Date;

/**
 * @ClassName: Session
 * @Description: shiroSession 
 * @author 李志勇
 * @date 2015年4月20日 上午9:53:47
 *
 */
public class Session {
	private String sessionId;
	private String host;
	private Date lastAccessTime;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
}
