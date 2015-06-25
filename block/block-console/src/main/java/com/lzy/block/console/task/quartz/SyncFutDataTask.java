
/**     
 * @FileName: SyncFutDataTask.java   
 * @Description: 同步futrue数据  
 * @author: ZhiYong.Li    
 * @date:2015年5月4日   
 */

package com.lzy.block.console.task.quartz;

import org.apache.log4j.Logger;


/**       
 * @author: ZhiYong.Li  
 * @date:2015年5月4日  
 */
public class SyncFutDataTask {
	
	private static final Logger logger = Logger.getLogger(SyncFutDataTask.class.getName());
	
	
    public void run() {
		logger.info("执行同步futrue数据任务");
		 
    }
    
}
