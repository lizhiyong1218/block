/**  
* @Title: ResultStatus.java
* @Package com.lzy.block.api.constant.common
* @author 李志勇  
* @date 2014年11月19日 下午4:07:03
* @version V1.0  
*/ 
package com.lzy.block.api.constant.activiti;

import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName: ActivitiTaskTypeStatus
 * @Description: 任务状态枚举 
 * @author 李志勇
 * @date 2014年11月19日 下午4:07:03
 *
 */
public enum ActivitiTaskTypeStatus {
	ASSIGNEE("已签收", "101"), CANDIDATE("等待签收", "102");

	private String text;
	private String value;

	private ActivitiTaskTypeStatus(String text, String value) {
		this.text = text;
		this.value = value;
	}

	// 根据value获取中文名称
	public static String getZhName(String value) {
		for (ActivitiTaskTypeStatus state : ActivitiTaskTypeStatus.values()) {
			if (state.value().equals(value)) {
				return state.text;
			}
		}
		return null;
	}

	// 根据text获取value
	public static String getText(String value) {
		for (ActivitiTaskTypeStatus state : ActivitiTaskTypeStatus.values()) {
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
		for (ActivitiTaskTypeStatus state : ActivitiTaskTypeStatus.values()) {
			map.put(state.value, state.text);
		}
		return map;
	}
}
