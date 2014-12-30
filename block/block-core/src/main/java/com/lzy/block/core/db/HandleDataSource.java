/**  
 * @Title: HandleDataSource.java
 * @Package com.lzy.block.core.db
 * @author 李志勇  
 * @date 2014年12月29日 下午3:55:05
 * @version V1.0  
 */
package com.lzy.block.core.db;


/**
 * @ClassName: HandleDataSource
 * @Description: 数据源控制器
 * @author 李志勇
 * @date 2014年12月29日 下午3:55:05
 * 
 */
public class HandleDataSource {
	
	
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSource(String datasource) {
		holder.set(datasource);
	}

	public static String getDataSource() {
		return holder.get();
	} 
	
	public static void clearDataSource() {
		holder.remove();
    }
	
}
