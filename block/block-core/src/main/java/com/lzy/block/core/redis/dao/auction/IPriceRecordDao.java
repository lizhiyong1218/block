/**  
* @Title: IPriceRecordDao.java
* @Package com.lzy.block.core.redis.dao.user.auction
* @author 李志勇  
* @date 2014年12月5日 上午9:58:40
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.auction;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lzy.block.api.model.auction.PriceRecord;

/**
 * @ClassName: IPriceRecordDao
 * @Description: 价格 
 * @author 李志勇
 * @date 2014年12月5日 上午9:58:40
 *
 */
public interface IPriceRecordDao {
//	boolean add(PriceRecord priceRecord);
//	PriceRecord getPrice(String key);
	public void addPriceInZset(String zsetkey, PriceRecord priceRecord);
	public Set<PriceRecord> getPricesInZset(String zsetkey,long start,long end);
	
	public long addPriceInSet(String setkey, PriceRecord priceRecord);
	
//	boolean addKey(String key);
	
//	public boolean addPriceToSet(String setName,Double price);
	
	public long addRecordInList(String listKey,PriceRecord priceRecord);
	
	public List<PriceRecord> getRecordInList(String listKey,long start,long end);
	
	public PriceRecord getFirstPriceRecord(String listKey); 
	
	public long addPrice(String listKey,Date endTime, PriceRecord priceRecord);
	
}
