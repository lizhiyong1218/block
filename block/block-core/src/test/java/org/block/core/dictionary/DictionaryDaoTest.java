package org.block.core.dictionary;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.List;

import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.dao.dictionary.DictionaryMapper;
 
public class DictionaryDaoTest extends BaseTest   {
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
