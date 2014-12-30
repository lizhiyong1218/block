package org.block.core.dictionary;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.ArrayList;
import java.util.List;

import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.service.dictionary.IDictionaryService;
 
public class DictionaryServiceTest extends BaseTest   {
	@Autowired 
	IDictionaryService dictionaryService;
	
	@Test 
	public void testSave() { 
		DictionaryModel dictionaryModel=new DictionaryModel();
		dictionaryModel.setDictionaryValue("zzzddd");
		DictionaryItemModel dictionaryItemModel=new DictionaryItemModel();
		dictionaryItemModel.setDictionaryValue(dictionaryModel.getDictionaryValue());
		List<DictionaryItemModel> items=new ArrayList<DictionaryItemModel>();
		items.add(dictionaryItemModel);
		dictionaryModel.setItems(items);  
		try {
//			dictionaryService.insertTest(dictionaryModel);
			dictionaryService.insert(dictionaryModel);
			System.out.println("===========");
//			dictionaryService.insert(dictionaryModel);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	
	
}
