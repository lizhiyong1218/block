
/**     
 * @FileName: DictionaryItemServiceImpl.java   
 * @Package:com.ceacsz.pms.service.dictionary.impl   
 * @Description: 数据字典项service实现  
 * @author: ZhiYong.Li    
 * @date:2014年8月7日   
 */

package com.lzy.block.core.service.dictionary.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
import com.lzy.block.core.service.base.impl.BaseServiceImpl;
import com.lzy.block.core.service.dictionary.IDictionaryItemService;



 
@Service
public class DictionaryItemServiceImpl extends BaseServiceImpl<DictionaryItemModel> implements IDictionaryItemService{

	@Resource 
	private DictionaryItemMapper dictionaryItemMapper;
	
	private Logger logger=Logger.getLogger(DictionaryItemServiceImpl.class.getName());

	@Override
	public void insertDictionaryItems(List<?> datas) {
		 DictionaryItemModel item=null;
		 DictionaryItemModel existModel=null;//用来查询数据字典项是否存在
		 for (Object object : datas) {
			 item=(DictionaryItemModel) object;
			 existModel= getOneByModel(item);
			 if(existModel==null){
				 item.setIsavailable("1");
				 item.setCreateBy("test");
				 item.setCreateTime(new Date());
				 try {
					  insert(item);
				} catch (Exception e) {
					logger.error("批量导入数据字典项错误:"+item.toString());
				}
			 }else{
				 logger.error("批量导入数据字典项错误,数据字典已经存在!"+existModel.toString());
			 }
		}
	}

	@Override
	protected BaseMapper<DictionaryItemModel> getMapper() {
		return dictionaryItemMapper;
	}
}
