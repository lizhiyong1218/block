
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
public class TestTask {
	
	private static final Logger logger = Logger.getLogger(TestTask.class.getName());
	
    public void run() {
		logger.info("执行同步test任务");
		try {
			logger.info("开始休眠");
			Thread.sleep(1000*60*2);
			logger.info("休眠结束");
		} catch (InterruptedException e) {
			logger.error(e);
		}
		 
    }
    
}
