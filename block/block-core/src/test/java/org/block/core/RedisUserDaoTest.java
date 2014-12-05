/**  
* @Title: RedisUserDaoTest.java
* @Package org.block.core
* @author 李志勇  
* @date 2014年12月2日 下午5:15:08
* @version V1.0  
*/ 
package org.block.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.lzy.block.api.model.User;
import com.lzy.block.core.redis.dao.user.IUserDao;

/**
 * @ClassName: RedisUserDaoTest
 * @Description: redis userDao测试 
 * @author 李志勇
 * @date 2014年12月2日 下午5:15:08
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class RedisUserDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource
	private IUserDao userDao;
	
	/** 
     * 新增 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUser() {    
        User user = new User();   
        user.setUserId(1);  
        user.setUserName("java2000_wl22");  
        user.setUserPwd("testpwd");
        boolean result = userDao.add(user);  
        Assert.assertTrue(result);   
    }  
      
    /** 
     * 批量新增 普通方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers1() {  
        List<User> list = new ArrayList<User>();  
        for (int i = 10; i < 50000; i++) {  
            User user = new User();  
            user.setUserId( i);  
            user.setUserName("java2000_wl" + i);  
            list.add(user);  
        }  
        long begin = System.currentTimeMillis();  
        for (User user : list) {  
            userDao.add(user);  
        }  
        System.out.println(System.currentTimeMillis() -  begin);  
    }  
      
    /** 
     * 批量新增 pipeline方式 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUsers2() {  
        List<User> list = new ArrayList<User>();  
        for (int i = 10; i < 50000; i++) {  
            User user = new User();  
            user.setUserId( i);  
            user.setUserName("java2000_wl" + i);  
            list.add(user);  
        }   
        long begin = System.currentTimeMillis();  
        boolean result = userDao.add(list);  
        System.out.println(System.currentTimeMillis() - begin);  
        Assert.assertTrue(result);  
    }  
      
    /** 
     * 修改 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testUpdate() {  
        User user = new User();  
        user.setUserId(1);  
        user.setUserName("new_password");  
        boolean result = userDao.update(user);  
        Assert.assertTrue(result);  
    }  
      
    /** 
     * 通过key删除单个 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDelete() {  
        String key = "1";  
        userDao.delete(key);  
    }  
      
    /** 
     * 批量删除 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testDeletes() {  
        List<String> list = new ArrayList<String>();  
        for (int i = 10; i < 50000; i++) {   
            list.add(i+"");  
        }  
        userDao.delete(list);  
    }  
      
    /** 
     * 获取 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testGetUser() {  
        String id = "1";  
        User user = userDao.get(id);  
        System.out.println(user);
        Assert.assertNotNull(user);  
//        Assert.assertEquals(user.getUserName(), "java2000_wl");  
    }  
   
    @Test  
    public void testOpList() {  
    	userDao.listOp(); 
    }   
	
}
