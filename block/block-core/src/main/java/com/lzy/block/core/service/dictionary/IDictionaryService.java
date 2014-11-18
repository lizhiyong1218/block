
 

package com.lzy.block.core.service.dictionary;

import java.util.List;

import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.service.IBaseService;



 

public interface IDictionaryService extends IBaseService<DictionaryModel>  {
	
	
	/**
	 * 批量插入数据字典
	 * @param datas  
	 * @throws
	 */
	public void insertDictionarys(List<?> datas);
	
}
