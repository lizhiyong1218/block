
/**     
 * @FileName: SyncWpiDataTask.java   
 * @Package:com.iceasy.cmc.manager.web.task   
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

public class SyncWpiDataTask {
	
	private static final Logger logger = Logger.getLogger(SyncWpiDataTask.class.getName());
	
	
    public void run() {
		logger.info("执行同步Wpi数据任务");
		 
    }
    
}
