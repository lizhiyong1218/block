package com.lzy.block.core.service.base.impl;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.core.dao.base.BaseMapper;
import com.lzy.block.core.db.DataSource;
import com.lzy.block.core.service.base.IBaseService;

/**
 * 
 * @ClassName: BaseServiceImpl
 * @Description: 公共service实现
 * @author 李志勇
 * @date 2015年4月8日 上午9:42:15
 * 
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T>{

	protected abstract  BaseMapper<T> getMapper();
	
	/**
	 * 
	 * @Title: insert
	 * @Description: 新增对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	@Override
	public int insert(T record){
		return getMapper().insertSelective(record);
	}
	
	/**
	 * 
	 * @Title: deleteById
	 * @Description: 根据id删除对象
	 * @param id
	 * @return: int
	 * @throws
	 */
	@Override
	public int deleteById(Integer id){
		return getMapper().deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 修改对象
	 * @param record
	 * @return: int
	 * @throws
	 */
	@Override
	public int update(T record){
		return getMapper().updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	 * @Title: getOneById
	 * @Description: 根据id查询对象
	 * @param id
	 * @return: T
	 * @throws
	 */
	@Override
	public T getOneById(Integer id){
		return getMapper().selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Title: getOneByModel
	 * @Description: 查询单个对象
	 * @param para
	 * @return:
	 * @throws
	 */
	@Override
    public T getOneByModel(T para){
    	return getMapper().selectModel(para);
    }
    
	/**
	 * 
	 * @Title: getAll
	 * @Description: 查询所有对象
	 * @param o
	 * @return:
	 * @throws
	 */
    @Override
    public List<T> getAll(T o) {
    	return getMapper().getAll(o);
    }
    
	/**
	 * 
	 * @Title: getPagination
	 * @Description: 获取分页对象
	 * @param o
	 * @param pageBounds
	 * @return: Pagination<T>
	 * @throws
	 */
    @Override
    @DataSource("slave")
    public Pagination<T> getPagination(T o, PageBounds pageBounds) {
		PageList<T> pageList = getMapper().getAll(o, pageBounds);
		Pagination<T> pageModel = new Pagination<T>();
		pageModel.setRecordCount(pageList.getPaginator().getTotalCount());
		pageModel.setRecordList(pageList);
		return pageModel;
    }
    
    
	/**
	 * 
	 * @Title: getCount
	 * @Description: 查询总数
	 * @param record
	 * @return: long
	 * @throws
	 */
    @Override
    public long getCount(T record){
    	return getMapper().getCount(record);
    }
	 
}
