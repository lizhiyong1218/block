/**  
* @Title: PriceRecordConsumer.java
* @Package com.lzy.block.core.rabbitmq.consumer
* @author 李志勇  
* @date 2014年12月9日 下午1:50:16
* @version V1.0  
*/ 
package com.lzy.block.core.rabbitmq.consumer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lzy.block.api.model.auction.PriceRecord;

/**
 * @ClassName: PriceRecordConsumer
 * @Description: 出价记录消费者 
 * @author 李志勇
 * @date 2014年12月9日 下午1:50:16
 *
 */
@Component
public class PriceRecordConsumer {
	private static Logger logger = Logger.getLogger(PriceRecordConsumer.class.getName());
	
	public void priceRecordDeal(PriceRecord priceRecord ) {
		logger.info("消费记录");
		System.out.println(priceRecord+"==");
	}

}
