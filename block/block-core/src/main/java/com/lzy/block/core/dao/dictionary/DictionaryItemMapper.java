package com.lzy.block.core.dao.dictionary;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.dictionary.DictionaryItemModel;


public interface DictionaryItemMapper {
    int deleteByPrimaryKey(Integer itemId);

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
}