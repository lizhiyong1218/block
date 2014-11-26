/**  
* @Title: ArticleController.java
* @Package com.lzy.block.console.controller.article
* @author 李志勇  
* @date 2014年11月26日 下午2:44:35
* @version V1.0  
*/ 
package com.lzy.block.console.controller.article;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.article.Article;
import com.lzy.block.core.service.article.IArticleService;

/**
 * @ClassName: ArticleController
 * @Description: 文章 
 * @author 李志勇
 * @date 2014年11月26日 下午2:44:35
 *
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	private static Logger logger = Logger.getLogger(ArticleController.class.getName());
	
	@Resource
	private IArticleService articleService;
	
	@ResponseBody
	@RequestMapping(value="/articleList")
	public  Map<String, Object>  dictionaryList(Article article,int page,int rows) {
		Pagination<Article> pm = new Pagination<Article>();
		try {				
			PageBounds pageBounds=new PageBounds(page, rows);
			pm = articleService.getPagination (article, pageBounds);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
}
