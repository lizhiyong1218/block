
/**     
 * @FileName: IUserService.java   
 * @Package:com.test.service   
 * @Description: 用户service  
 * @author: ZhiYong.Li    
 * @date:2014年10月16日   
 */

package com.lzy.block.core.service.user;

import com.lzy.block.api.model.user.User;
import com.lzy.block.core.service.base.IBaseService;

public interface IUserService extends IBaseService<User>{
	
	public User insertUser(User user);
	
	public User getById(Integer userId);
	
	public User getByName(String userName);
}
