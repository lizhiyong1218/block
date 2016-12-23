package com.lzy.block.search.solr.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.lzy.block.search.solr.page.GroupSolrPagination;
import com.lzy.block.search.solr.page.Pagination;

/**
 * Group查询回调处理
 * 
 */
public class GroupCallbackHandler<T> extends SolrCallbackHandler<T> {

    public GroupCallbackHandler(SolrTemplate<T> solrTemplate, SolrQuery solrQuery, Pagination<T> pagination) {
        super(solrTemplate, solrQuery, pagination);
    }
    
    public List<T> parseQueryResponse(QueryResponse response) {
        List<T> allResult = new ArrayList<T>();
        if (pagination instanceof GroupSolrPagination) {
            GroupSolrPagination<T> page = (GroupSolrPagination<T>)pagination;
            
//            List<GroupCommand> values = response.getGroupResponse().getValues();
//            for (GroupCommand groupCommand : values) {
//                String groupName = groupCommand.getName();
//                for (Group group : groupCommand.getValues()) {
//                    String groupValue = group.getGroupValue();
//                    try {
//                        List<T> result = solrTemplate.solrDocumentsToEntities(group.getResult());
//						page.setGroup(groupName+groupValue, result);
//						long numFound = group.getResult().getNumFound();
//						page.setGroupNumber(groupName+groupValue, (int)numFound);
//                        allResult.addAll(result);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            pagination.setRecordCount(allResult.size());
//            return allResult;
            
            GroupResponse groupResponse = response.getGroupResponse();
            if(groupResponse!=null){
            	Map<String, Map<String, List<T>>> groupRes=new HashMap<String, Map<String,List<T>>>();
            	List<GroupCommand> values = groupResponse.getValues();
                for (GroupCommand groupCommand : values) {
                    String groupName = groupCommand.getName();//fieldname
                    Map<String, List<T>> listMap = groupRes.get(groupName);
                    if(listMap==null){
                    	listMap=new HashMap<String, List<T>>();
                    	groupRes.put(groupName, listMap);
                    }
                    for (Group group : groupCommand.getValues()) {
                        String groupValue = group.getGroupValue();//field里面的分组
                        try {
                            List<T> result = solrTemplate.solrDocumentsToEntities(group.getResult());
                            listMap.put(groupValue, result);
//    						page.setGroup(groupName+groupValue, result);
//    						long numFound = group.getResult().getNumFound();
//    						page.setGroupNumber(groupName+groupValue, (int)numFound);
//                          allResult.addAll(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        page.setGroupRes(groupRes);
                    }
                }
                pagination.setRecordCount(allResult.size());
            }
            return allResult;
        }else{
            return super.parseQueryResponse(response);
        }
    }

}
