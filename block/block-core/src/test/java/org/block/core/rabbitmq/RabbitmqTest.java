package org.block.core.rabbitmq;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.lzy.block.api.model.auction.PriceRecord;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class RabbitmqTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private RabbitTemplate rabbitTemplate;
	
	@Test 
	public void testSendMessager() {   
		PriceRecord priceRecord=new PriceRecord("1", 100d, "tt", new Date(), "11");
		rabbitTemplate.convertAndSend("queues.block.price.quote",priceRecord);  
	}
	
}
