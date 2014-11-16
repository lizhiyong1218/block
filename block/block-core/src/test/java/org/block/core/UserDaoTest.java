package org.block.core;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.User;
import com.lzy.block.core.dao.UserMapper;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired 
	UserMapper userMapper;
	
	@Test
	public void testSave() {
		User user=new User();
		user.setUserName("lzy");
		user.setUserPwd("zzz");
		userMapper.insert(user);
		System.out.println(user.getUserId()+"=================");
		System.out.println(1);
	}
	
	@Test
	public void testDelete() {
		userMapper.delete(3); 
	}
	
	@Test
	public void testUpdate() {
		User user=new User();
		user.setUserId(4);
		user.setUserName("发大发");
		userMapper.update(user); 
	}
	
	@Test
	public void testGetOneByid() {
		User user=userMapper.getOneById(14); 
		System.out.println(user);
	}
	
	@Test
	public void testGetOneByRmaNoPrefix() {
		User user=userMapper.getOneByNamePrefix("lz"); 
		System.out.println(user);
	}
	
	@Test
	public void testFindAll(){
		User user=new User();
		user.setUserName("lzy");
		List<User> list=userMapper.findAll(user);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}
	
	@Test
	public void testFindAllPage(){
		User user=new User();
		PageBounds page=new PageBounds();
		page.setPage(4);
		page.setLimit(2);
		PageList<User> pl=userMapper.findAll(user,page);
		for (User user2 : pl) {
			System.out.println(user2.toString()+"============");
		}
		System.out.println(pl.getPaginator().getTotalCount());
	}
}
