/**  
* @Title: PriceRecord.java
* @Package com.lzy.block.api.model.auction
* @author 李志勇  
* @date 2014年12月5日 上午9:50:29
* @version V1.0  
*/ 
package com.lzy.block.api.model.auction;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: PriceRecord
 * @Description: 出价记录 
 * @author 李志勇
 * @date 2014年12月5日 上午9:50:29
 *
 */
public class PriceRecord implements Serializable{
	/**
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 7589285673907902572L;
	/*价格id*/
	private String recordId;
	/*价格*/
	private Double price;
	/*出价用户id*/
	private String userId;
	/*出价时间*/
	private Date priceDate;
	/*拍卖id*/
	private String auctionId;
	
	public PriceRecord() {
		super();
	}
	
	public PriceRecord(String recordId, Double price, String userId,
			Date priceDate, String auctionId) {
		super();
		this.recordId = recordId;
		this.price = price;
		this.userId = userId;
		this.priceDate = priceDate;
		this.auctionId = auctionId;
	}


	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	
	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	@Override
	public String toString() {
		return "PriceRecord [recordId=" + recordId + ", price=" + price
				+ ", userId=" + userId + ", priceDate=" + priceDate
				+ ", auctionId=" + auctionId + "]";
	}
	
}
