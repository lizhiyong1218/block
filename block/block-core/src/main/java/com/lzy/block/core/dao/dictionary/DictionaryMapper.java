package com.lzy.block.core.dao.dictionary;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.dictionary.DictionaryModel;


public interface DictionaryMapper {
	/**
	 * 
	* @Title: deleteByPrimaryKey
	* @Description: 根据id删除
	* @param dictionaryId
	* @return:     
	* int    
	* @throws
	 */
	public int deleteByPrimaryKey(Integer dictionaryId);

	/**
	 * 
	* @Title: insert
	* @Description: 插入对象
	* @param record
	* @return:     
	* int    
	* @throws
	 */
    public int insert(DictionaryModel record);

    /**
     * 
    * @Title: insertSelective
    * @Description: 插入对象
    * @param record
    * @return:     
    * int    
    * @throws
     */
    public int insertSelective(DictionaryModel record);

    /**
     * 
    * @Title: selectByPrimaryKey
    * @Description: 根据id查询对象
    * @param dictionaryId
    * @return:     
    * DictionaryModel    
    * @throws
     */
    public DictionaryModel selectByPrimaryKey(Integer dictionaryId);

    /**
     * 
    * @Title: updateByPrimaryKeySelective
    * @Description: 根据id修改部分属性
    * @param record
    * @return:     
    * int    
    * @throws
     */
    public int updateByPrimaryKeySelective(DictionaryModel record);

    /**
     * 
    * @Title: updateByPrimaryKey
    * @Description: 修改对象
    * @param record
    * @return:     
    * int    
    * @throws
     */
    public int updateByPrimaryKey(DictionaryModel record);
    
    /**
     * 
    * @Title: selectModel
    * @Description: 查询单个对象
    * @param para
    * @return:     
    * DictionaryModel    
    * @throws
     */
    public DictionaryModel selectModel(DictionaryModel para);
    
    /**
     * 
    * @Title: getAll
    * @Description: 查询所有对象
    * @param o
    * @return:     
    * List<DictionaryModel>    
    * @throws
     */
    public List<DictionaryModel> getAll(DictionaryModel o) ;
    
    /**
     * 
    * @Title: getAll
    * @Description: 查询分页集合
    * @param o
    * @param pageBounds
    * @return:     
    * PageList<DictionaryModel>    
    * @throws
     */
    public PageList<DictionaryModel> getAll(DictionaryModel o,
			PageBounds pageBounds);
    
    /**
     * 
    * @Title: getCount
    * @Description: 查询总数
    * @param record
    * @return:     
    * long    
    * @throws
     */
    public long getCount(DictionaryModel record);
    
    
}