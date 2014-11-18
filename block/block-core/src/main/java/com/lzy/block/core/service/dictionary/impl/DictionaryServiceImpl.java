
/**     
 * @FileName: DictionaryImpl.java   
 * @Package:com.ceacsz.pms.service.dictionary.impl   
 * @Description: 数据字典service实现  
 * @author: ZhiYong.Li    
 * @date:2014年8月7日   
 */

package com.lzy.block.core.service.dictionary.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.constant.common.AvaliableEnum;
import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
import com.lzy.block.core.dao.dictionary.DictionaryMapper;
import com.lzy.block.core.service.dictionary.IDictionaryService;



 
@Service
public class DictionaryServiceImpl implements IDictionaryService{
	@Resource
	private DictionaryMapper dictionaryMapper;
	@Resource
	private DictionaryItemMapper dictionaryItemMapper;
	
	private Logger logger=Logger.getLogger(DictionaryServiceImpl.class.getName());
	 
	@Override
	public int insert(DictionaryModel o) throws Exception {
		List<DictionaryItemModel> items=o.getItems();
		for (DictionaryItemModel dictionaryItemModel : items) {
			dictionaryItemModel.setDictionaryValue(o.getDictionaryValue());
			dictionaryItemModel.setIsavailable(AvaliableEnum.AVAILABLE.value());
			dictionaryItemModel.setCreateBy(o.getCreateBy());
			dictionaryItemModel.setCreateTime(o.getCreateTime());
			dictionaryItemMapper.insert(dictionaryItemModel);
		}
		return dictionaryMapper.insert(o);
	}

	@Override
	public int update(DictionaryModel o) throws Exception {
		return  dictionaryMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void delete(Integer id) throws Exception {
		dictionaryMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public DictionaryModel getOneById(Integer id) throws Exception {
		return  dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DictionaryModel> getAll(DictionaryModel o) throws Exception {
		return  dictionaryMapper.getAll(o);
	}

	 
	
	@Override
	public void insertDictionarys(List<?> datas){
		DictionaryModel dictionary=null;
		DictionaryModel existModel=null;//用来查询数据字典是否存在
		for (Object object : datas) {
			dictionary=(DictionaryModel) object;
			existModel= dictionaryMapper.selectModel(dictionary);
			if(existModel==null){
				try {
					dictionary.setIsavailable("1");
					dictionary.setCreateBy("test");
					dictionary.setCreateTime(new Date());
					dictionaryMapper.insert(dictionary);
				} catch (Exception e) {
					logger.error("批量导入数据字典错误:"+dictionary.toString());
				}
			}else{
				logger.error("批量导入数据字典错误,数据字典已经存在!"+existModel.toString());
			}
		}
	}

	 

	@Override
	public DictionaryModel selectModel(DictionaryModel o) {
		return dictionaryMapper.selectModel(o);
	}

	@Override
	public Pagination<DictionaryModel> getPagination(DictionaryModel o,
			PageBounds pageBounds) {
		long recordCount=dictionaryMapper.getCount(o);
		List<DictionaryModel> recordList=new ArrayList<DictionaryModel>();
		if(recordCount>0){
			recordList= dictionaryMapper.getAll(o, pageBounds);
		}
		Pagination<DictionaryModel> pagination=new Pagination<DictionaryModel>(pageBounds.getPage(),pageBounds.getLimit(),recordCount,recordList);
		return pagination;
	} 

}
