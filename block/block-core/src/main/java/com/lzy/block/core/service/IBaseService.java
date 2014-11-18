/**  
* @Title: IBaseService.java
* @Package com.lzy.block.core.service
* @author 李志勇  
* @date 2014年11月18日 上午11:46:46
* @version V1.0  
*/ 
package com.lzy.block.core.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.dictionary.DictionaryModel;

/**
 * @ClassName: IBaseService
 * @Description: 公共service接口 
 * @author 李志勇
 * @date 2014年11月18日 上午11:46:46
 *
 */
public interface IBaseService<T> {
	 
	public int insert(T o) throws Exception;

	 
	public int update(T o) throws Exception;

	 
	public void delete(Integer id) throws Exception;
 
	public T getOneById(Integer id) throws Exception;

	public List<T> getAll(T o) throws Exception;
	 
	public T  selectModel(T o);
	
	public Pagination<T> getPagination(T o,PageBounds pageBounds);

}
