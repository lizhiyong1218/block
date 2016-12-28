package com.lzy.block.search.solr.vo;

import java.io.Serializable;
import java.util.List;

public class SolrGroupVo <T> implements Serializable{

	private static final long serialVersionUID = 1L;
	/*字段名称*/
	private String fieldName;
	/*字段值*/
	private String fieldValue;
	private List<T> list;
	/*每个字段值分组的总数*/
	private Long count;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
