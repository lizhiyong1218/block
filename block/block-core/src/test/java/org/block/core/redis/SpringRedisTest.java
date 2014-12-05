/**  
* @Title: RedisUserDaoTest.java
* @Package org.block.core
* @author 李志勇  
* @date 2014年12月2日 下午5:15:08
* @version V1.0  
*/ 
package org.block.core.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class SpringRedisTest extends AbstractTransactionalJUnit4SpringContextTests{
	  @Autowired  
	  protected StringRedisTemplate stringRedisTemplate;  
	
	/** 
     * 新增 
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testAddUser() {    
    	 // String读写
//        stringRedisTemplate.delete("myStr");
//        stringRedisTemplate.opsForValue().set("myStr", "http://yjmyzz.cnblogs.com/");
//        System.out.println(stringRedisTemplate.opsForValue().get("myStr"));
//        System.out.println("---------------");

        // List读写
        stringRedisTemplate.delete("myList");
        stringRedisTemplate.opsForList().rightPush("myList", "A");
        stringRedisTemplate.opsForList().rightPush("myList", "B");
        stringRedisTemplate.opsForList().leftPush("myList", "0");
        List<String> listCache = stringRedisTemplate.opsForList().range(
                "myList", 0, -1);
        for (String s : listCache) {
            System.out.println(s);
        }
        System.out.println("---------------");

//        // Set读写
//        stringRedisTemplate.delete("mySet");
//        stringRedisTemplate.opsForSet().add("mySet", "A");
//        stringRedisTemplate.opsForSet().add("mySet", "B");
//        stringRedisTemplate.opsForSet().add("mySet", "C");
//        Set<String> setCache = stringRedisTemplate.opsForSet().members(
//                "mySet");
//        for (String s : setCache) {
//            System.out.println(s);
//        }
//        System.out.println("---------------");
//
//        // Hash读写
//        stringRedisTemplate.delete("myHash");
//        stringRedisTemplate.opsForHash().put("myHash", "PEK", "北京");
//        stringRedisTemplate.opsForHash().put("myHash", "SHA", "上海虹桥");
//        stringRedisTemplate.opsForHash().put("myHash", "PVG", "浦东");
//        Map<Object, Object> hashCache = stringRedisTemplate.opsForHash()
//                .entries("myHash");
//        for (Map.Entry<Object, Object> entry : hashCache.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }

        System.out.println("---------------");   
    }  
    
    public static void main(String[] args) {
    	ConfigurableApplicationContext ctx = null;
        try {
            ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

            StringRedisTemplate stringRedisTemplate = ctx.getBean("redisTemplate", StringRedisTemplate.class);

            // String读写
            stringRedisTemplate.delete("myStr");
            stringRedisTemplate.opsForValue().set("myStr", "http://yjmyzz.cnblogs.com/");
            System.out.println(stringRedisTemplate.opsForValue().get("myStr"));
            System.out.println("---------------");

            // List读写
            stringRedisTemplate.delete("myList");
            stringRedisTemplate.opsForList().rightPush("myList", "A");
            stringRedisTemplate.opsForList().rightPush("myList", "B");
            stringRedisTemplate.opsForList().leftPush("myList", "0");
            List<String> listCache = stringRedisTemplate.opsForList().range(
                    "myList", 0, -1);
            for (String s : listCache) {
                System.out.println(s);
            }
            System.out.println("---------------");

            // Set读写
            stringRedisTemplate.delete("mySet");
            stringRedisTemplate.opsForSet().add("mySet", "A");
            stringRedisTemplate.opsForSet().add("mySet", "B");
            stringRedisTemplate.opsForSet().add("mySet", "C");
            Set<String> setCache = stringRedisTemplate.opsForSet().members(
                    "mySet");
            for (String s : setCache) {
                System.out.println(s);
            }
            System.out.println("---------------");

            // Hash读写
            stringRedisTemplate.delete("myHash");
            stringRedisTemplate.opsForHash().put("myHash", "PEK", "北京");
            stringRedisTemplate.opsForHash().put("myHash", "SHA", "上海虹桥");
            stringRedisTemplate.opsForHash().put("myHash", "PVG", "浦东");
            Map<Object, Object> hashCache = stringRedisTemplate.opsForHash()
                    .entries("myHash");
            for (Map.Entry<Object, Object> entry : hashCache.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

            System.out.println("---------------");

        } finally {
            if (ctx != null && ctx.isActive()) {
                ctx.close();
            }
        }
	}
      
	
}
