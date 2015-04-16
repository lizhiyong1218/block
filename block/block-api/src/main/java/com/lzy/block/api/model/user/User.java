
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
	private String password;
	private Integer organizationId; //所属公司
    private String salt; //加密密码的盐
    private String roleIds; //拥有的角色列表
    private boolean locked;
	
	
	public User(){
		
	}
	
	public User(Integer userId,String userName,String userPwd){
		super();
		this.userId=userId;
		this.userName=userName;
		this.password=userPwd;
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
		return password;
	}
	public void setUserPwd(String userPwd) {
		this.password = userPwd;
	}
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public String getCredentialsSalt() {
	        return userName + salt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", organizationId=" + organizationId
				+ ", salt=" + salt + ", roleIds=" + roleIds + ", locked="
				+ locked + "]";
	}

	 
	
	
}
