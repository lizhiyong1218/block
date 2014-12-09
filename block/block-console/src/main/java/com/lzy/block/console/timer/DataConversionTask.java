/**  
* @Title: DataConversionTask.java
* @Package com.lzy.block.console.timer
* @author 李志勇  
* @date 2014年12月8日 下午2:26:11
* @version V1.0  
*/ 
package com.lzy.block.console.timer;

import org.apache.log4j.Logger;

/**
 * @ClassName: DataConversionTask
 * @Description: DataConversionTask 
 * @author 李志勇
 * @date 2014年12月8日 下午2:26:11
 *
 */
public class DataConversionTask{
    private static final Logger LOG = Logger.getLogger(DataConversionTask.class.getName());
    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("数据转换任务线程开始执行");
        }
    }
}
