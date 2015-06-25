
/**     
 * @FileName: SyncChipStopDataTask.java   
 * @Description: 同步WPI数据  
 * @author: ZhiYong.Li    
 * @date:2015年5月13日   
 */

package com.lzy.block.console.task.quartz;

import org.apache.log4j.Logger;


/**       
 * @author: ZhiYong.Li  
 * @date:2015年5月13日     
 */

public class SyncChipStopDataTask {
	
	private static final Logger logger = Logger.getLogger(SyncChipStopDataTask.class.getName());
	
    public void run() {
    	logger.info("执行同步SyncChipStopDataTask数据任务");
		 
	}
    
}
