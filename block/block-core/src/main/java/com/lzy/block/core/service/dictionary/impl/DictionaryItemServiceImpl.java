
/**     
 * @FileName: DictionaryItemServiceImpl.java   
 * @Package:com.ceacsz.pms.service.dictionary.impl   
 * @Description: 数据字典项service实现  
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
import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.core.dao.dictionary.DictionaryItemMapper;
import com.lzy.block.core.service.dictionary.IDictionaryItemService;



 
@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService{

	@Resource 
	private DictionaryItemMapper dictionaryItemMapper;
	
	private Logger logger=Logger.getLogger(DictionaryItemServiceImpl.class.getName());
	 
	@Override
	public int insert(DictionaryItemModel o) throws Exception {
		return dictionaryItemMapper.insert(o);
	}

	@Override
	public int update(DictionaryItemModel o) throws Exception {
		return dictionaryItemMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void delete(Integer id) throws Exception {
		dictionaryItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public DictionaryItemModel getOneById(Integer id) throws Exception {
		return dictionaryItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DictionaryItemModel> getAll(DictionaryItemModel o)
			throws Exception {
		return dictionaryItemMapper.getAll(o);
	}

	@Override
	public DictionaryItemModel selectModel(DictionaryItemModel o) {
		return dictionaryItemMapper.selectModel(o);
	}

	@Override
	public Pagination<DictionaryItemModel> getPagination(DictionaryItemModel o,
			PageBounds pageBounds) {
		long recordCount=dictionaryItemMapper.getCount(o);
		List<DictionaryItemModel> recordList=new ArrayList<DictionaryItemModel>();
		if(recordCount>0){
			recordList= dictionaryItemMapper.getAll(o, pageBounds);
		}
		Pagination<DictionaryItemModel> pagination=new Pagination<DictionaryItemModel>(pageBounds.getPage(),pageBounds.getLimit(),recordCount,recordList);
		return pagination;
	}

	@Override
	public void insertDictionaryItems(List<?> datas) {
		 DictionaryItemModel item=null;
		 DictionaryItemModel existModel=null;//用来查询数据字典项是否存在
		 for (Object object : datas) {
			 item=(DictionaryItemModel) object;
			 existModel= dictionaryItemMapper.selectModel(item);
			 if(existModel==null){
				 item.setIsavailable("1");
				 item.setCreateBy("test");
				 item.setCreateTime(new Date());
				 try {
					 dictionaryItemMapper.insert(item);
				} catch (Exception e) {
					logger.error("批量导入数据字典项错误:"+item.toString());
				}
			 }else{
				 logger.error("批量导入数据字典项错误,数据字典已经存在!"+existModel.toString());
			 }
		}
	}
}
