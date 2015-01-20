/**  
* @Title: PageModel.java
* @Package com.lzy.block.api.common
* @author 李志勇  
* @date 2015年1月19日 上午10:57:47
* @version V1.0  
*/ 
package com.lzy.block.api.common;

/**
 * @ClassName: PageModel
 * @Description: 分页对象 
 * @author 李志勇
 * @date 2015年1月19日 上午10:57:47
 *
 */
public class PageModel {
	
	/** 页号 */
    private int page=1;
    /** 分页大小 */
    private int rows=Integer.MAX_VALUE;
    
	public PageModel() {
		super();
	}
	
	public PageModel(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageModel [page=" + page + ", rows=" + rows + "]";
	}

	
	
	 
    
}
