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
	
	Logger aloneLogger=Logger.getLogger("AlongTest");
	
	/**
	 * 测试邮件
	 */
	@Test
	public void testfatalEmail(){
		logger.fatal("邮件info测试 ~~~");
	}
	
	
	@Test
	public void testInfoLogger(){
		logger.info("info日志~~~~~");
	}
	
	/**
	 * 测试数据库
	 */
	@Test
	public void testdatabaseLogger(){
		logger.error("数据库日志~~~~~");
	}
	
	/**
	 * 测试单独存放日志到指定文件
	 */
	@Test
	public void testaloneLogger(){
		aloneLogger.info("单独日志~~~~~");
	}
	
}
