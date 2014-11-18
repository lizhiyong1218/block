package com.lzy.block.core.dao.dictionary;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.dictionary.DictionaryModel;


public interface DictionaryMapper {
	public int deleteByPrimaryKey(Integer dictionaryId);

    public int insert(DictionaryModel record);

    public int insertSelective(DictionaryModel record);

    public DictionaryModel selectByPrimaryKey(Integer dictionaryId);

    public int updateByPrimaryKeySelective(DictionaryModel record);

    public int updateByPrimaryKey(DictionaryModel record);
    
    public DictionaryModel selectModel(DictionaryModel para);
    
    public List<DictionaryModel> getAll(DictionaryModel o) ;
    
    public PageList<DictionaryModel> getAll(DictionaryModel o,
			PageBounds pageBounds);
    
    public long getCount(DictionaryModel record);
}