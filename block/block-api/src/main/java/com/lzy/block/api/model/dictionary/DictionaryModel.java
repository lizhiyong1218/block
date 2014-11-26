package com.lzy.block.api.model.dictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: DictionaryModel
 * @Description: 数据字典
 * @author 李志勇
 * @date 2014年11月18日 上午9:37:12
 * 
 */
public class DictionaryModel implements Serializable {

	private static final long serialVersionUID = 51032217172910219L;

	private Integer dictionaryId;
	/*数据字典值*/
	private String dictionaryValue;
	/*数据字典名称*/
	private String dictionaryLabel;
	/* */
	private String isavailable;
	/**/
	private String remarks;
	/**/
	private String createBy;
	/**/
	private Date createTime;
	/**/
	private String modifiBy;
	/**/
	private Date modifiTime;

	private List<DictionaryItemModel> items = new ArrayList<DictionaryItemModel>();

	public List<DictionaryItemModel> getItems() {
		return items;
	}

	public void setItems(List<DictionaryItemModel> items) {
		this.items = items;
	}

	public Integer getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getDictionaryValue() {
		return dictionaryValue;
	}

	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}

	public String getDictionaryLabel() {
		return dictionaryLabel;
	}

	public void setDictionaryLabel(String dictionaryLabel) {
		this.dictionaryLabel = dictionaryLabel;
	}

	public String getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
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

	public String getModifiBy() {
		return modifiBy;
	}

	public void setModifiBy(String modifiBy) {
		this.modifiBy = modifiBy;
	}

	public Date getModifiTime() {
		return modifiTime;
	}

	public void setModifiTime(Date modifiTime) {
		this.modifiTime = modifiTime;
	}

	@Override
	public String toString() {
		return "DictionaryModel [dictionaryId=" + dictionaryId
				+ ", dictionaryValue=" + dictionaryValue + ", dictionaryLabel="
				+ dictionaryLabel + ", isavailable=" + isavailable
				+ ", remarks=" + remarks + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifiBy=" + modifiBy
				+ ", modifiTime=" + modifiTime + "]";
	}

}