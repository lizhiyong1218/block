/**  
* @Title: PriceRecordTest.java
* @Package org.block.core.redis
* @author 李志勇  
* @date 2014年12月5日 上午10:05:34
* @version V1.0  
*/ 
package org.block.core.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.lzy.block.api.model.auction.PriceRecord;
import com.lzy.block.core.redis.dao.auction.IPriceRecordDao;

/**
 * @ClassName: PriceRecordTest
 * @Description: 价格 
 * @author 李志勇
 * @date 2014年12月5日 上午10:05:34
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class PriceRecordTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired 
	IPriceRecordDao priceDao;
	
	
	 
//	@Test
//	public void testAdd() { 
//		PriceRecord priceRecord=new PriceRecord();
//		priceRecord.setRecordId("1");
//		priceRecord.setPrice(100d);
//		priceRecord.setUserId("u1");
//		priceDao.add(priceRecord); 
//	}
//	
//	@Test
//	public void testGet() { 
//		
//		PriceRecord priceRecord=priceDao.getPrice(new Double(100).toString());
//		System.out.println(priceRecord); 
//	}
	
	@Test
	public void testAddInList() {   
		PriceRecord priceRecord=new PriceRecord("1",300d,"user2",new Date(),null);
		long res = priceDao.addRecordInList("pm1", priceRecord);
		System.out.println(res);
	}
	
	@Test
	public void testGetListInZset() { 
		Set<PriceRecord> prices = priceDao.getPricesInZset("pm1", 0, -1);
		for (PriceRecord priceRecord : prices) { 
			System.out.println(priceRecord);
		}
	}
	
	/**
	* @Title: testGetPrice
	* @Description: 出价    
	* void    
	* @throws 
	 */
	@Test
	public synchronized void testAddPrice(){
		
		String auctionId="pm1";
		Date endTime=null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			endTime=sdf.parse("2014-12-20");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		String recordId="";
		Double price=100000d;
		String userId="u2";
		Date priceDate=new Date();
		PriceRecord priceRecord=new PriceRecord(recordId, price, userId, priceDate, auctionId);
		long res= priceDao.addPrice(auctionId, endTime, priceRecord);
		System.out.println(res+"=====");
//		for(int i=0;i<10000;i++){
//			price=(double) i;
//			userId="u"+i;
//			priceRecord=new PriceRecord(recordId, price, userId, priceDate, auctionId);
//			priceDao.addPrice(auctionId, endTime, priceRecord);
//		}
		
	}
	
	@Test
	public void testGetPrice(){
		PriceRecord firstPriceRecord = priceDao.getFirstPriceRecord("pm1");
		System.out.println(firstPriceRecord);
	}
	
	@Test
	public void getRecordInList(){
		List<PriceRecord> recordInList = priceDao.getRecordInList("pm1", 0, 5);
		for (PriceRecord priceRecord : recordInList) {
			System.out.println(priceRecord);
		}
//		System.out.println(recordInList.size());
	}
	 
	
}
