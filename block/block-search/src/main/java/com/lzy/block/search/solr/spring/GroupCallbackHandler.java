package com.lzy.block.search.solr.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.lzy.block.search.solr.page.SolrPagination;
import com.lzy.block.search.solr.vo.SolrGroupVo;

/**
 * Group查询回调处理
 * 
 */
public class GroupCallbackHandler<T> extends SolrCallbackHandler<T> {

    public GroupCallbackHandler(SolrTemplate<T> solrTemplate, SolrQuery solrQuery, SolrPagination<T> pagination) {
        super(solrTemplate, solrQuery, pagination);
    }
    
    public List<T> parseQueryResponse(QueryResponse response) {
        if (response!=null) {
            GroupResponse groupResponse = response.getGroupResponse();
            if(groupResponse!=null&&groupResponse.getValues()!=null&&groupResponse.getValues().size()>0){
            	Map<String, Map<String, SolrGroupVo<T>>> groupRes=new HashMap<String, Map<String, SolrGroupVo<T>>>();
            	Map<String, SolrGroupVo<T>> fieldMap=null;
            	String filedName =null;
            	String fieldValue=null;
            	SolrGroupVo<T> solrGroupVo=null;
            	for (GroupCommand groupCommand : groupResponse.getValues()) {
                    filedName = groupCommand.getName();//字段名称如status
                    fieldMap = groupRes.get(filedName);
                    if(fieldMap==null){
                    	fieldMap=new HashMap<String, SolrGroupVo<T>>();
                    	groupRes.put(filedName, fieldMap);
                    }
                    for (Group group : groupCommand.getValues()) {
                        fieldValue = group.getGroupValue();//字段的值如：on_sale,sale_out
                        solrGroupVo=new SolrGroupVo<T>();
                        try {
                        	//字段的分组集合
                        	solrGroupVo.setList(solrTemplate.solrDocumentsToEntities(group.getResult()));
                        	//字段的分组总数
                        	solrGroupVo.setCount(group.getResult().getNumFound());
                        	solrGroupVo.setFieldName(filedName);
                        	solrGroupVo.setFieldValue(fieldValue);
                        	fieldMap.put(fieldValue, solrGroupVo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            	pagination.setGroupRes(groupRes);
            }
        }
        return null;
    }

}
