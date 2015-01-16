/**  
* @Title: BaseTest.java
* @Package org.block.core
* @author 李志勇  
* @date 2014年12月26日 下午3:38:25
* @version V1.0  
*/ 
package org.block.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @ClassName: BaseTest
 * @Description: 测试基类 
 * @author 李志勇
 * @date 2014年12月26日 下午3:38:25
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
 
}
