
package com.lzy.block.api.constant.common;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
* @ClassName: AvaliableEnum
* @Description: 数据源枚举 
* @author 李志勇
* @date 2014年11月18日 上午11:53:27
*
 */
public enum DatasourceEnum {
	MASTER("主数据源", "master"), SLAVE("从数据源", "slave");

	private String text;
	private String value;

	private DatasourceEnum(String text, String value) {
		this.text = text;
		this.value = value;
	}

	// 根据value获取中文名称
	public static String getZhName(String value) {
		for (DatasourceEnum state : DatasourceEnum.values()) {
			if (state.value().equals(value)) {
				return state.text;
			}
		}
		return null;
	}

	// 根据text获取value
	public static String getText(String value) {
		for (DatasourceEnum state : DatasourceEnum.values()) {
			if (state.text().equals(value)) {
				return state.value;
			}
		}
		return null;
	}

	public String text() {
		return text;
	}

	public String value() {
		return value;
	}

	public static Map<String, String> getEnumMap() {
		Map<String, String> map = new TreeMap<String, String>();
		for (DatasourceEnum state : DatasourceEnum.values()) {
			map.put(state.value, state.text);
		}
		return map;
	}
	
	public  static boolean existValue(String value){
		return getEnumMap().containsKey(value);
	}
	
}
