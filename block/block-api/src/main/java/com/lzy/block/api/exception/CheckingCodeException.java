/**  
* @Title: CheckingCodeException.java
* @Package com.lzy.block.core.exception
* @author 李志勇  
* @date 2015年4月14日 下午3:25:54
* @version V1.0  
*/ 
package com.lzy.block.api.exception;

/**
 * @ClassName: CheckingCodeException
 * @Description: 验证码异常 
 * @author 李志勇
 * @date 2015年4月14日 下午3:25:54
 *
 */
public class CheckingCodeException extends RuntimeException{

	private static final long serialVersionUID = 891783825868338480L;

	public CheckingCodeException(String msg){
		super(msg);
	}
	
}
