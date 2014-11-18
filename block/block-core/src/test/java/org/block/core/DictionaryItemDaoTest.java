package org.block.core;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
import com.lzy.block.core.dao.dictionary.DictionaryMapper;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class DictionaryItemDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired 
	DictionaryItemMapper dictionaryItemMapper;
	
	@Test
	public void testSave() {
		DictionaryItemModel model=new DictionaryItemModel();
		model.setDictionaryValue("zzzddd");
		dictionaryItemMapper.insert(model);
	}
	
	 
}
