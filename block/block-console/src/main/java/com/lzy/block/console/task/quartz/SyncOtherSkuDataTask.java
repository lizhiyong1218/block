
/**     
 * @FileName: SyncWpiDataTask.java   
 * @Description: 同步ATS,WMS,BOOKPRICE数据  
 * @author: ZhiYong.Li    
 * @date:2015年5月13日   
 */

package com.lzy.block.console.task.quartz;

import org.apache.log4j.Logger;


/**       
 * @author: ZhiYong.Li  
 * @date:2015年5月13日     
 */

public class SyncOtherSkuDataTask {
	
	private static final Logger logger = Logger.getLogger(SyncOtherSkuDataTask.class.getName());
	
	
    public void run() {
		logger.info("执行同步ATS,WMS,BOOKPRICE Sku数据任务开始");
		 
    }
    
}
