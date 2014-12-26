package org.block.core.rabbitmq;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */


import java.util.Date;

import javax.annotation.Resource;

import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.lzy.block.api.model.auction.PriceRecord;

 
public class RabbitmqTest extends BaseTest {
	@Resource
	private RabbitTemplate rabbitTemplate;
	
	@Test 
	public void testSendMessager() {   
		PriceRecord priceRecord=new PriceRecord("1", 100d, "tt", new Date(), "11");
		rabbitTemplate.convertAndSend("queues.block.price.quote",priceRecord);  
	}
	
}
