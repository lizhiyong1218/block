package com.lzy.block.api.model.dictionary;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: DictionaryItemModel
* @Description: 数据字典项 
* @author 李志勇
* @date 2014年11月18日 上午9:37:30
*
 */
public class DictionaryItemModel implements Serializable{
    
	private static final long serialVersionUID = 2648683689620776602L;

	private Integer itemId;

    private String itemValue;

    private String itemLabel;

    private Integer orderNo;

    private String remarks;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private String isavailable;
    
    private String dictionaryValue;
 
	public String getDictionaryValue() {
		return dictionaryValue;
	}

	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}

	public String getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "DictionaryItemModel [itemId=" + itemId + ", itemValue=" + itemValue + ", itemLabel=" + itemLabel
				+ ", orderNo=" + orderNo + ", remarks=" + remarks + ", createBy=" + createBy + ", createTime="
				+ createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + ", isavailable=" + isavailable
				+ "]";
	}

    
}