/**  
* @Title: Log4jTest.java
* @Package org.block.core.log4j
* @author 李志勇  
* @date 2015年6月10日 下午3:29:29
* @version V1.0  
*/ 
package org.block.core.log4j;

import org.apache.log4j.Logger;
import org.block.core.BaseTest;
import org.junit.Test;

/**
 * @ClassName: Log4jTest
 * @Description: 日志测试 
 * @author 李志勇
 * @date 2015年6月10日 下午3:29:29
 *
 */
public class Log4jTest extends BaseTest{
	
	Logger logger=Logger.getLogger(Log4jTest.class.getName());
	
	@Test
	public void testERREmail(){
		logger.error("邮件测试 ~~~");
	}
	
	@Test
	public void testInfoEmail(){
		logger.info("邮件info测试 ~~~");
	}
	
}
