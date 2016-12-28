package com.lzy.block.search.solr.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.lzy.block.search.solr.page.SolrPagination;


/**
 * Facet查询回调处理
 * 
 */
public class FacetCallbackHandler<T> extends SolrCallbackHandler<T> {

    public FacetCallbackHandler(SolrTemplate<T> solrTemplate, SolrQuery solrQuery, SolrPagination<T> pagination) {
        super(solrTemplate, solrQuery, pagination);
    }
    
    public List<T> parseQueryResponse(QueryResponse response) {
    	if(response!=null){
            List<FacetField> fields = response.getFacetFields();//返回的facet列表
            if(fields!=null&&fields.size()>0){
                Map<String, Map<String, Long>> facetRes=new HashMap<String, Map<String, Long>>();
                Map<String, Long> fieldMap=null;
                String fieldName=null;//字段名称如status
                for (FacetField field : fields) {
                	fieldName=field.getName();
                    fieldMap=facetRes.get(fieldName);
                    if(fieldMap==null){
                    	fieldMap=new HashMap<String, Long>();
                    	facetRes.put(fieldName, fieldMap);
                    }
                    List<Count> fieldValues = field.getValues();
                    for (Count value : fieldValues) {
                        fieldMap.put(value.getName(), value.getCount());
                    }
                }
                pagination.setFacetRes(facetRes);            	
            }    		
    	}
        return super.parseQueryResponse(response);
    }

}
