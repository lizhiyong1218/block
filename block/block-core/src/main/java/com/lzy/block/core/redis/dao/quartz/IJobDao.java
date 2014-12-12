/**  
* @Title: JobDao.java
* @Package com.lzy.block.core.redis.dao
* @author 李志勇  
* @date 2014年12月12日 下午3:26:11
* @version V1.0  
*/ 
package com.lzy.block.core.redis.dao.quartz;

import java.util.List;

import com.lzy.block.api.model.quartz.ScheduleJob;

/**
 * @ClassName: JobDao
 * @Description: quartz任务dao 
 * @author 李志勇
 * @date 2014年12月12日 下午3:26:11
 *
 */
public interface IJobDao {
	
	/** 
     * 新增 
     * <br>------------------------------<br> 
     * @param job 
     * @return 
     */  
    boolean add(String key,ScheduleJob job);  
      
    /** 
     * 批量新增 使用pipeline方式 
     * <br>------------------------------<br> 
     * @param list 
     * @return 
     */  
    boolean add(List<ScheduleJob> list);  
      
    /** 
     * 删除 
     * <br>------------------------------<br> 
     * @param key 
     */  
    void delete(String key);  
      
    /** 
     * 删除多个 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    void delete(List<String> keys);  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     * @param job 
     * @return  
     */  
    boolean update(ScheduleJob job);  
  
    /** 
     * 通过key获取 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return  
     */  
    ScheduleJob get(String keyId);  

}
