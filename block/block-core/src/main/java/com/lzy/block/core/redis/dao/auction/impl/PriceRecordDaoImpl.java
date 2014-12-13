/**  
* @Title: PriceRecordDaoImpl.java
* @Package com.lzy.block.core.redis.dao.auction.impl
* @author 李志勇  
* @date 2014年12月5日 上午10:00:11
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.auction.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import com.lzy.block.api.common.StrUtil;
import com.lzy.block.api.model.auction.PriceRecord;
import com.lzy.block.core.redis.dao.auction.IPriceRecordDao;

/**
 * @ClassName: PriceRecordDaoImpl
 * @Description: 出价记录 
 * @author 李志勇
 * @date 2014年12月5日 上午10:00:11
 *
 */
@Repository
public class PriceRecordDaoImpl implements IPriceRecordDao{
	
	private static Logger logger = Logger.getLogger(PriceRecordDaoImpl.class.getName());
	
	@Autowired
	private RedisTemplate<String,PriceRecord>  redisTemplate;
//	private RedisTemplate<Serializable,Serializable>  redisTemplate;
	
//	@Override
//	public boolean add(PriceRecord priceRecord) {
//		StringBuffer sb=new StringBuffer();
//		sb.append(priceRecord.getAuctionId()).append(priceRecord.getPrice());
//		ValueOperations<String, PriceRecord> valueops = redisTemplate
//				                .opsForValue();
//		return valueops.setIfAbsent(sb.toString(), priceRecord);
//		
//	}
//
//	@Override
//	public PriceRecord getPrice(String key) {
//		ValueOperations<String, PriceRecord> valueops = redisTemplate.opsForValue();
//		PriceRecord record = valueops.get(key);
//		return record;
//	}
	
	/**
	 * 往zset中存数据
	 */
	@Override
	public void addPriceInZset(String key,PriceRecord priceRecord){
		 ZSetOperations<String, PriceRecord> opsForZSet = redisTemplate.opsForZSet();
		 Boolean res = opsForZSet.add(key, priceRecord, priceRecord.getPrice());
		 System.out.println(res);
	}
	
	/**
	 * 从zset中取数据
	 */
	@Override
	public Set<PriceRecord> getPricesInZset(String key,long start,long end){
		ZSetOperations<String, PriceRecord> opsForZSet = redisTemplate.opsForZSet();
		Set<PriceRecord> set = opsForZSet.range(key, start, end);
		return set;
	}

	/**
	 * 添加记录到set中
	 */
	@Override
	public long addPriceInSet(String setKey, PriceRecord priceRecord) {
		SetOperations<String, PriceRecord> opsForSet = redisTemplate.opsForSet();
		Long res = opsForSet.add(setKey, priceRecord);
		return res;
	}
	
	/**
	 * 添加记录到list中
	 */
	@Override
	public long addRecordInList(String listKey, PriceRecord priceRecord) {
		
		
		ListOperations<String, PriceRecord> opsForList = redisTemplate.opsForList();
		
		Long count =opsForList.leftPush(listKey, priceRecord);
		
		return count;
	}
	
	/**
	 * 获得记录列表
	 */
	@Override 
	public List<PriceRecord> getRecordInList(String listKey,long start,long end){
		ListOperations<String, PriceRecord> opsForList = redisTemplate.opsForList();
		List<PriceRecord> list = opsForList.range(listKey, start, end);
		return list;
	}
	
	/**
	 * 
	* @Title: getFirstPriceRecord
	* @Description: 获得最高价
	* @param listKey
	* @return:     
	* PriceRecord    
	* @throws
	 */
	@Override
	public PriceRecord getFirstPriceRecord(String listKey){
		PriceRecord priceRecord=null;
		List<PriceRecord> list= getRecordInList(listKey, 0, 0);
		if(list.size()>0){
			priceRecord=list.get(0);
		}else{
			logger.warn("获得第一个价格记录为空!");
		}
		return priceRecord;
	}
	
	
	/**
	 * 
	 * 1.要保证有价格list的key
	 * 2.从list中获取第一个元素 即为最高价记录和最新价格记录,如果没有则记录该条记录
	 * 	 a.判断新增的报价是否大于最高价
	 * 	 b.判断新增报价的用户id是否等于最新价格记录的用户id
	 *   c.判断报价时间是否超过结束时间
	 * 3.将记录插到list	 
	 * 
	 * 
	* @Title: givePrice
	* @Description: 报价
	* @param priceRecord
	* @return:     
	* String    
	* @throws
	 */
	@Override
	public  long addPrice(String listKey,Date endTime, PriceRecord priceRecord){
		logger.info(priceRecord);
		long res = 0;
		if(validatePrice(listKey, endTime, priceRecord)){
			
			redisTemplate.multi();
			
			PriceRecord firstPriceRecord = getFirstPriceRecord(listKey);
			if(firstPriceRecord!=null){//list列表中有出价记录
				if(priceRecord.getPrice()>firstPriceRecord.getPrice()){
					if(!priceRecord.getUserId().equals(firstPriceRecord.getUserId())){
						if(priceRecord.getPriceDate().getTime()<endTime.getTime()){
							res=addRecordInList(listKey, priceRecord);
						}else{
							logger.error("新增报价的时间:"+priceRecord.getPriceDate()+"超过结束时间:"+endTime);
						}
					}else{
						logger.error("新增报价的userId:"+priceRecord.getUserId()+"与最高记录的userId相等:"+firstPriceRecord.getAuctionId());
					}
				}else{
					logger.error("新增报价:"+priceRecord.getPrice()+"小于或者等于最高价:"+firstPriceRecord.getPrice());
				}
			}else{//在所有人出价之前list是空的,这个时候只需要将该出价记录增加到list中
				res=addRecordInList(listKey, priceRecord);
			}
			
			redisTemplate.exec();
			
		}else{
			logger.error("验证价格信息失败!");
		}
		return res;
	}
	
	/**
	 * 
	* @Title: validatePrice
	* @Description: 验证价格
	* @param auctionId
	* @param endTime
	* @param priceRecord
	* @return:     
	* boolean    
	* @throws
	 */
	private boolean validatePrice(String auctionId,Date endTime,PriceRecord priceRecord){
		if(StrUtil.isNotEmpty(auctionId)){
			if(endTime!=null){
				if(priceRecord.getPrice()>0){
					if(StrUtil.isNotEmpty(priceRecord.getUserId())){
						if(priceRecord.getPriceDate()!=null){
							return true;
						}else{
							logger.error("价格时间为空!");
						}
					}else{
						logger.error("userId为空!");
					}
				}else{
					logger.error("价格<=0！price："+priceRecord.getPrice());
				}
			}else{
				logger.error("结束时间为空!");
			}
		}else{
			logger.error("listKey为空!");
		}
		return false;
	}
	
}
