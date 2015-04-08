/**  
* @Title: BaseMapper.java
* @Package com.lzy.block.core.dao.base
* @author 李志勇  
* @date 2015年4月8日 上午9:47:50
* @version V1.0  
*/ 
package com.lzy.block.core.dao.base;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * @ClassName: BaseMapper
 * @Description: 基础mapper 
 * @author 李志勇
 * @date 2015年4月8日 上午9:47:50
 *
 */
public interface BaseMapper<T> {

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 新增对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	public int insertSelective(T record);
	
	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据id删除对象
	 * @param id
	 * @return: int
	 * @throws
	 */
	public int deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据id查询对象
	 * @param id
	 * @return: T
	 * @throws
	 */
	public T selectByPrimaryKey(Integer id);
	
	/**
	 * 
	 * @Title: selectModel
	 * @Description: 查询单个对象
	 * @param para
	 * @return:
	 * @throws
	 */
    public T selectModel(T para);
    
	/**
	 * 
	 * @Title: getAll
	 * @Description: 查询所有对象
	 * @param o
	 * @return:
	 * @throws
	 */
    public List<T> getAll(T o) ;
    
	/**
	 * 
	 * @Title: getAll
	 * @Description: 查询分页集合
	 * @param o
	 * @param pageBounds
	 * @return:
	 * @throws
	 */
    public PageList<T> getAll(T o,PageBounds pageBounds);
    
	/**
	 * 
	 * @Title: getCount
	 * @Description: 查询总数
	 * @param record
	 * @return: long
	 * @throws
	 */
    public long getCount(T record);
	
}
