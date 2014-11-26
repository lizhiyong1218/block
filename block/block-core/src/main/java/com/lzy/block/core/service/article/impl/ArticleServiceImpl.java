/**  
* @Title: ArticleServiceImpl.java
* @Package com.lzy.block.core.service.article.impl
* @author 李志勇  
* @date 2014年11月26日 下午2:23:25
* @version V1.0  
*/ 
package com.lzy.block.core.service.article.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.article.Article;
import com.lzy.block.core.dao.article.ArticleMapper;
import com.lzy.block.core.service.article.IArticleService;

/**
 * @ClassName: ArticleServiceImpl
 * @Description: 文章 
 * @author 李志勇
 * @date 2014年11月26日 下午2:23:25
 *
 */
@Service
public class ArticleServiceImpl implements IArticleService{
	@Resource
	private ArticleMapper articleMapper;
	
	@Override
	public int insert(Article o) throws Exception {
		return articleMapper.insert(o);
	}

	@Override
	public int updateByPrimaryKeySelective(Article o) throws Exception {
		return articleMapper.updateByPrimaryKeyWithBLOBs(o);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) throws Exception {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) throws Exception {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> getAll(Article o) throws Exception {
		return articleMapper.getAll(o);
	}

	@Override
	public Article selectModel(Article o) {
		return articleMapper.selectModel(o);
	}

	@Override
	public Pagination<Article> getPagination(Article o, PageBounds pageBounds) {
		long recordCount=articleMapper.getCount(o);
		List<Article> recordList=new ArrayList<Article>();
		if(recordCount>0){
			recordList= articleMapper.getAll(o, pageBounds);
		}
		Pagination<Article> pagination=new Pagination<Article>(pageBounds.getPage(),pageBounds.getLimit(),recordCount,recordList);
		return pagination;
	}

}
