/**  
* @Title: ResultVo.java
* @Package com.lzy.block.api.vo
* @author 李志勇  
* @date 2014年11月19日 下午4:03:59
* @version V1.0  
*/ 
package com.lzy.block.api.vo;

/**
 * @ClassName: ResultVo
 * @Description: controller执行结果vo 
 * @author 李志勇
 * @date 2014年11月19日 下午4:03:59
 *
 */
public class ResultVo {
	// 状态
	private String status;
	// 信息
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResultVo [status=" + status + ", message=" + message + "]";
	}
	
}
