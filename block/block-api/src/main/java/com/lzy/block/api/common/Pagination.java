package com.lzy.block.api.common;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {
	private static final long serialVersionUID = 5104811017362151385L;

    private int currentPage;
    private int pageSize;
    private long recordCount = 1L;
    private List<T> recordList;
    private int pageCount;
    private int offset;
    private int prePage;
    private int nextPage;
    private boolean hasPrePage;
    private boolean hasNextPage;
    public Pagination() {
        
    }
    
    public  Pagination(int currentPage,int pageSize,long recordCount,List<T> recordList) {
        this.currentPage = currentPage;
        if(currentPage < 1) {
            this.currentPage = 1;
        }
        
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;
        this.prePage = this.currentPage - 1;
        if(this.prePage < 1) {
            this.hasPrePage = false; 
            this.prePage = 1;
        }else {
            this.hasPrePage = true; 
        }
        
        this.pageCount = (int)Math.ceil(recordCount / (double)pageSize);
        if(this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }
        
        this.nextPage = this.currentPage + 1;
        if(this.nextPage > this.pageCount) {
            this.hasNextPage = false; 
            this.nextPage = this.pageCount;
        }else {
            this.hasNextPage = true; 
        }
        
        this.offset = (this.currentPage - 1)*pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }
}
