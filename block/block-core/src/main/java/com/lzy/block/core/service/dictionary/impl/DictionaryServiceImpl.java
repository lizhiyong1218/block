
/**     
 * @FileName: DictionaryImpl.java   
 * @Package:com.ceacsz.pms.service.dictionary.impl   
 * @Description: 数据字典service实现  
 * @author: ZhiYong.Li    
 * @date:2014年8月7日   
 */

package com.lzy.block.core.service.dictionary.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
import com.lzy.block.core.dao.dictionary.DictionaryMapper;
import com.lzy.block.core.db.DataSource;
import com.lzy.block.core.service.base.impl.BaseServiceImpl;
import com.lzy.block.core.service.dictionary.IDictionaryService;

 
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryModel> implements IDictionaryService{
	@Resource
	private DictionaryMapper dictionaryMapper;
	@Resource
	private DictionaryItemMapper dictionaryItemMapper;
	
	private Logger logger=Logger.getLogger(DictionaryServiceImpl.class.getName());
	 

	@Override
	@DataSource("master")
	public void insertDictionarys(List<?> datas){
		DictionaryModel dictionary=null;
		DictionaryModel existModel=null;//用来查询数据字典是否存在
		for (Object object : datas) {
			dictionary=(DictionaryModel) object;
			existModel= getOneByModel(dictionary);
			if(existModel==null){
				try {
					dictionary.setIsavailable("1");
					dictionary.setCreateBy("test");
					dictionary.setCreateTime(new Date());
					dictionaryMapper.insertSelective(dictionary);
				} catch (Exception e) {
					logger.error("批量导入数据字典错误:"+dictionary.toString());
				}
			}else{
				logger.error("批量导入数据字典错误,数据字典已经存在!"+existModel.toString());
			}
		}
	}

	@Override
	public void deleteDictionary(Integer dictionaryId, String dictionaryValue) {
		dictionaryMapper.deleteByPrimaryKey(dictionaryId);
		dictionaryItemMapper.deleteByDictionaryValue(dictionaryValue);
	}


	@Override
	protected BaseMapper<DictionaryModel> getMapper() {
		return dictionaryMapper;
	} 

}
