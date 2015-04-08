
 
package com.lzy.block.core.service.dictionary;

import java.util.List;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.core.service.base.IBaseService;

 
/**
 * 
* @ClassName: IDictionaryItemService
* @Description: 数据字典项 
* @author 李志勇
* @date 2014年11月18日 下午2:29:12
*
 */
public interface IDictionaryItemService extends IBaseService<DictionaryItemModel> {
	 
	/**
	 * 批量插入数据字典项
	 * @param datas  
	 * @throws
	 */
	public void insertDictionaryItems(List<?> datas);
	
	
	
}
