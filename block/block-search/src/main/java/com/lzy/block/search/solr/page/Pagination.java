package com.lzy.block.search.solr.page;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页排序类
 * 
 */
public class Pagination<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected static final int DEFAULT_PAGE = 1;
	public static int DEFAULT_PAGE_SIZE = 20;
	public static int DEFULAT_PAGE_ROOM_GARDEN_SIZE = 30;
	protected List<T> items;
	protected int recordCount;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected int currentPageStartIndex = 0;
	protected int currentPage = DEFAULT_PAGE;
	protected String uri; // 分页页面的URI
	protected String pageTemplate; // 分页模板
	protected Sort sort = Sort.DESC; // 排序方式 DESC/ASC
	protected String sortField; // 排序字段
	protected String queryString;
	private boolean queryRecordCount = true; //是否查询总记录数
	
	public static enum Sort {
		DESC, ASC
	}
	
	public Pagination() {
		this(DEFAULT_PAGE_SIZE, DEFAULT_PAGE, null);
	}
	public Pagination(int pageSize, int page) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("Count should be greater than zero!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
		}
	}
	
	public Pagination(int pageSize, int page, boolean queryRecordCount) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("Count should be greater than zero!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
		}
		this.queryRecordCount = queryRecordCount;
	}
	
	public Pagination(int pageSize, int page, String uri) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("Count should be greater than zero!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
			this.uri = uri;
		}
	}
	
	public static boolean hasResults(Pagination<?> page) {
		if (null != page && null != page.getItems() 
				&& !page.getItems().isEmpty()) {
				return true;			
		}
		return false;
	}
	
	public void setPageSize(int countOnEachPage) {
		this.pageSize = countOnEachPage;
	}

	public List<T> getItems() {
		return items;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public int getCurrentPageStartIndex() {
		currentPageStartIndex = (currentPage - 1) * pageSize;
		return currentPageStartIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}

	public void setRecordCount(int totalCount) {
		this.recordCount = totalCount;
	}

	public int getPageCount() {
		return (recordCount % pageSize == 0) ? (recordCount / pageSize)
				: (recordCount / pageSize) + 1;
	}

	public int getPreviousPage() {
		if(currentPage > 1) return currentPage - 1;
		else return DEFAULT_PAGE;
	}
	
	public int getNextPage() {
		if(currentPage < getPageCount()) return currentPage + 1;
		else return getPageCount();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public boolean isQueryRecordCount() {
		return queryRecordCount;
	}
	
	public void setQueryRecordCount(boolean queryRecordCount) {
		this.queryRecordCount = queryRecordCount;
	}
	
}