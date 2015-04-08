
/**     
 * @FileName: Media.java   
 * @Package:com.test.entity   
 * @author: ZhiYong.Li    
 * @date:2014年10月13日   
 */

package com.lzy.block.api.model.user;

import java.io.Serializable;

 
/**
 * 
* @ClassName: User
* @Description: 用户 
* @author 李志勇
* @date 2014年11月18日 上午11:54:11
*
 */
public class User implements Serializable{
	private static final long serialVersionUID = 2734339282011057392L;
	private Integer userId;
	private String userName;
	private String userPwd;
	
	public User(){
		
	}
	
	public User(Integer userId,String userName,String userPwd){
		super();
		this.userId=userId;
		this.userName=userName;
		this.userPwd=userPwd;
	}
	
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
