/**  
 * @Title: ChooseDataSource.java
 * @Package com.lzy.block.core.db
 * @author 李志勇  
 * @date 2014年12月29日 下午3:53:53
 * @version V1.0  
 */
package com.lzy.block.core.db;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: ChooseDataSource
 * @Description: 选择数据源
 * @author 李志勇
 * @date 2014年12月29日 下午3:53:53
 * 
 */
public class ChooseDataSource extends AbstractRoutingDataSource {

	private static Logger logger = Logger.getLogger(ChooseDataSource.class
			.getName());

	@Override
	protected Object determineCurrentLookupKey() {
		logger.debug("determine数据源key:"+HandleDataSource.getDataSource());
		return HandleDataSource.getDataSource();
	}

}
