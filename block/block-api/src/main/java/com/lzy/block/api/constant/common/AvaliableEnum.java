
 

package com.lzy.block.api.constant.common;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
* @ClassName: AvaliableEnum
* @Description: 是否可用枚举 
* @author 李志勇
* @date 2014年11月18日 上午11:53:27
*
 */
public enum AvaliableEnum {
	AVAILABLE("可用", "1"), DISAVAILABLE("不可用", "0");

	private String text;
	private String value;

	private AvaliableEnum(String text, String value) {
		this.text = text;
		this.value = value;
	}

	// 根据value获取中文名称
	public static String getZhName(String value) {
		for (AvaliableEnum state : AvaliableEnum.values()) {
			if (state.value().equals(value)) {
				return state.text;
			}
		}
		return null;
	}

	// 根据text获取value
	public static String getText(String value) {
		for (AvaliableEnum state : AvaliableEnum.values()) {
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
		for (AvaliableEnum state : AvaliableEnum.values()) {
			map.put(state.value, state.text);
		}
		return map;
	}
}
