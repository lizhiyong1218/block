package com.lzy.block.search.solr.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSolrPagination<T> extends Pagination<T> {

	private static final long serialVersionUID = 1L;
	
	private Map<String, List<T>> groupResult = new HashMap<String, List<T>>();
    /**
     * 分组后的数量
     */
    private Map<String, Integer> groupNumber = new HashMap<String, Integer>();
    
    public GroupSolrPagination() {
        super();
    }

    public GroupSolrPagination(int pageSize, int page) {
        super(pageSize, page);
    }

    public GroupSolrPagination(int pageSize, int page, String uri) {
        super(pageSize, page, uri);
    }

    public void setGroup(String groupName, List<T> result) {
        groupResult.put(groupName, result);
    }
    
    public void setGroupNumber(String groupName,Integer number){
    	groupNumber.put(groupName, number);
    }
    
    public void setGroupResult(Map<String, List<T>> groupResult) {
        this.groupResult = groupResult;
    }

    public Map<String, List<T>> getGroupResult() {
        return groupResult;
    }
    
    public Map<String, Integer> getGroupNumber() {
        return groupNumber;
    }

}
