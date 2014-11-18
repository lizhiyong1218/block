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
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.dao.dictionary.DictionaryMapper;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class DictionaryDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired 
	DictionaryMapper dictionaryMapper;
	
	@Test
	public void testSave() {
		DictionaryModel dictionaryModel=new DictionaryModel();
		dictionaryModel.setDictionaryValue("zzzddd");
		dictionaryMapper.insert(dictionaryModel);
	}
	
	@Test
	public void testGetAll() {
		DictionaryModel dictionaryModel=new DictionaryModel();
		dictionaryModel.setDictionaryValue("RMATYPE");
		List<DictionaryModel> all = dictionaryMapper.getAll(dictionaryModel);
		System.out.println("=======");
		for (DictionaryModel dictionaryModel2 : all) {
			System.out.println(dictionaryModel2);
		}
		System.out.println("=======");
	} 
	
	@Test
	public void testGetPage() {
		DictionaryModel dictionaryModel=new DictionaryModel();
		
		PageBounds pageBounds=new PageBounds();
		pageBounds.setPage(2);
		pageBounds.setLimit(5);
		
		PageList<DictionaryModel> all = dictionaryMapper.getAll(dictionaryModel, pageBounds);
		System.out.println("=======");
		for (DictionaryModel dictionaryModel2 : all) {
			System.out.println(dictionaryModel2);
		}
		System.out.println("=======");
	} 
	
	@Test
	public void testGetCount() {
		DictionaryModel dictionaryModel=new DictionaryModel();
		
		System.out.println("=======");
		long count= dictionaryMapper.getCount(dictionaryModel);
		System.out.println(count);
		System.out.println("=======");
	}
	
	
}
