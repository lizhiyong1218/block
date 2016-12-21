package com.lzy.block.search.solr.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文变量
 * 
 * @author Hou Peiqin
 * 
 */
public class ContextHolder {
	private static final ThreadLocal<Map<String, Object>> contextHolder = new ThreadLocal<Map<String, Object>>();
	private static final String DATA_SOURCE = "DATA_SOURCE";
	private static final String CURRENT_CITY = "CURRENT_CITY";
	public static void setDataSource(String dataSource) {
		Map<String, Object> holder = contextHolder.get();
		if(holder == null) {
			holder = new HashMap<String, Object>();
			contextHolder.set(holder);
		}
		if(dataSource != null) dataSource = dataSource.toUpperCase();
		holder.put(DATA_SOURCE, dataSource);
	}

	public static String getDataSource() {
		Map<String, Object> holder = contextHolder.get();
		if(holder == null) return null;
		return (String) holder.get(DATA_SOURCE);
	}
	
	public static void setCurrentCity(String currentCity) {
		Map<String, Object> holder = contextHolder.get();
		if(holder == null) {
			holder = new HashMap<String, Object>();
			contextHolder.set(holder);
		}
		if(currentCity != null) currentCity = currentCity.toUpperCase();
		holder.put(CURRENT_CITY, currentCity);
	}
	
	/**
	 * 通过域名访问地址获取城市
	 * @return
	 */
	public static String getCurrentCity() {
		Map<String, Object> holder = contextHolder.get();
		if(holder == null) return null;
		return (String) holder.get(CURRENT_CITY);
	}
	public static void clear() {
		contextHolder.remove();
	}
	
	
}
