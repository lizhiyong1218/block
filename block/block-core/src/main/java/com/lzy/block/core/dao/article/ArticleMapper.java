package com.lzy.block.core.dao.article;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.article.Article;


public interface ArticleMapper {
    int deleteByPrimaryKey(Integer blogId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer blogId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    /**
     * 
    * @Title: selectModel
    * @Description: 查询单个对象
    * @param para
    * @return:     
    * DictionaryModel    
    * @throws
     */
    public Article selectModel(Article para);
    
    /**
     * 
    * @Title: getAll
    * @Description: 查询所有对象
    * @param o
    * @return:     
    * List<DictionaryModel>    
    * @throws
     */
    public List<Article> getAll(Article o) ;
    
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
    public PageList<Article> getAll(Article o,
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
    public long getCount(Article record);
}