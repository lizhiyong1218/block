package com.lzy.block.search.solr.enums;

/**
 * 数据中心(机房)
 */
public enum DataCenterEnum {
	
	EAST("华东"),
	SOUTH("华南"),
	NORTH("华北"),
	BEIJING("北京"); // 图片服务器--北京
	
	private String desc;
	
	private DataCenterEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
