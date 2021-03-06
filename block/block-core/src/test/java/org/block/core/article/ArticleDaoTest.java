package org.block.core.article;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */


import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.block.api.model.article.Article;
import com.lzy.block.core.dao.article.ArticleMapper;
 
public class ArticleDaoTest  extends BaseTest{
	@Autowired 
	ArticleMapper mapper;
	
	@Test
	public void testGetCount() {
		Article bean=new Article();
		System.out.println("=======");
		long count= mapper.getCount(bean);
		System.out.println(count+"-----------");
		System.out.println("=======");
	}
	
	@Test
	public void testGetPagemodel(){
		Article o=new Article();
		PageBounds pageBounds=new PageBounds(1, 2);
		PageList<Article> all = mapper.getAll(o,pageBounds);
		logger.info(all.getPaginator().getTotalCount());
		for (Article article : all) {
			logger.info(article);
		}
	}
	
	
}
