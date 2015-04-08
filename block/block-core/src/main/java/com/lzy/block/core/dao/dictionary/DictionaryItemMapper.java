package com.lzy.block.core.dao.dictionary;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.core.dao.base.BaseMapper;


public interface DictionaryItemMapper extends BaseMapper<DictionaryItemModel>{
    /*int deleteByPrimaryKey(Integer itemId);

    int insert(DictionaryItemModel record);

    int insertSelective(DictionaryItemModel record);

    DictionaryItemModel selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(DictionaryItemModel record);

    int updateByPrimaryKey(DictionaryItemModel record);
    
    public DictionaryItemModel selectModel(DictionaryItemModel para);
    
    public List<DictionaryItemModel> getAll(DictionaryItemModel o) ;
    
    public PageList<DictionaryItemModel> getAll(DictionaryItemModel o,
			PageBounds pageBounds);
    
    public long getCount(DictionaryItemModel record);
    */
    public void deleteByDictionaryValue(String dictionaryValue);
}