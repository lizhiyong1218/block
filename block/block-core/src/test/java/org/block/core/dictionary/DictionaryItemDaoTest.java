package org.block.core.dictionary;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
 
public class DictionaryItemDaoTest extends BaseTest {
	@Autowired 
	DictionaryItemMapper dictionaryItemMapper;
	
	@Test
	public void testSave() {
		DictionaryItemModel model=new DictionaryItemModel();
		model.setDictionaryValue("zzzddd");
		dictionaryItemMapper.insert(model);
	}
	
	 
}
