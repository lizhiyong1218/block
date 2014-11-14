
/**     
 * @FileName: IUserService.java   
 * @Package:com.test.service   
 * @Description: 用户service  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service;

import com.lzy.block.api.model.User;



/**       
 * @author: ZhiYong.Li  
 * @date:2014年10月16日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有   
 */

public interface IUserService {
	
	public User insert(User user);
	
	public User getById(Integer userId);
	
	public void deleteById(Integer userId);
	
}
