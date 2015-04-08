/**  
* @Title: IBaseService.java
* @Package com.lzy.block.core.service
* @author 李志勇  
* @date 2014年11月18日 上午11:46:46
* @version V1.0  
*/ 
package com.lzy.block.core.service.base;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;

/**
 * @ClassName: IBaseService
 * @Description: 公共service接口 
 * @author 李志勇
 * @date 2014年11月18日 上午11:46:46
 *
 */
public interface IBaseService<T> {
	
	/**
	 * 
	 * @Title: insert
	 * @Description: 新增对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	public int insert(T record);
	
	/**
	 * 
	 * @Title: deleteById
	 * @Description: 根据id删除对象
	 * @param id
	 * @return: int
	 * @throws
	 */
	public int deleteById(Integer id);
	
	/**
	 * 
	 * @Title: update
	 * @Description: 修改对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	public int update(T record);

	/**
	 * 
	 * @Title: getOneById
	 * @Description: 根据id查询对象
	 * @param id
	 * @return: T
	 * @throws
	 */
	public T getOneById(Integer id);
	
	/**
	 * 
	 * @Title: getOneByModel
	 * @Description: 查询单个对象
	 * @param para
	 * @return:
	 * @throws
	 */
    public T getOneByModel(T para);
    
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
	 * @Title: getPagination
	 * @Description: 获取分页对象
	 * @param o
	 * @param pageBounds
	 * @return: Pagination<T>
	 * @throws
	 */
    public Pagination<T> getPagination(T o, PageBounds pageBounds) ;
    
    
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
