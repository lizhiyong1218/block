
/**     
 * @FileName: IUserService.java   
 * @Package:com.test.service   
 * @Description: 用户service  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service;

import com.lzy.block.api.model.User;



 

public interface IUserService {
	
	public User insert(User user);
	
	public User getById(Integer userId);
	
	public void deleteById(Integer userId);
	
}
