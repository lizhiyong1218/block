
/**     
 * @FileName: Media.java   
 * @Package:com.test.entity   
 * @author: ZhiYong.Li    
 * @date:2014年10月13日   
 */

package com.lzy.block.api.model;

import java.io.Serializable;

/**       
 * @author: ZhiYong.Li  
 * @date:2014年10月13日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有   
 */

public class User implements Serializable{
	private static final long serialVersionUID = 2734339282011057392L;
	private Integer userId;
	private String userName;
	private String userPwd;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + "]";
	}
	
	
}
