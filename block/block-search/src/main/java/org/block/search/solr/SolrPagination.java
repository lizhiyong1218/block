package org.block.search.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.QueryResponse;

import com.lzy.block.api.common.Pagination;

//import com.qfang.common.page.Pagination;
//import com.qfang.model.solr.SolrFacetEntity;
//import com.qfang.search.SearchCriteria;

public class SolrPagination<T> extends Pagination<T>{

	private static final long serialVersionUID = 1L;
	protected SearchCriteria criteria;
    public SolrPagination() {
        super();
    }
//    public SolrPagination(int pageSize, int page) {
//        super(pageSize, page);
//    }
//    public SolrPagination(int pageSize, int page, String uri) {
//        super(pageSize, page, uri);
//    }
    
//    public void setFacet(QueryResponse response) {
//        Map<String, Integer> facetQuery = response.getFacetQuery();
//        List<SolrFacetEntity> facetFields = criteria.getFacetFields();
//        if (null != facetFields) {
//	        for (SolrFacetEntity solrEnum : facetFields) {
//	            Integer count = facetQuery.get(solrEnum.createFacetQuery());
//	            solrEnum.setCount(count == null ? 0 : count);
//	        }
//        }
//    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

//    public List<SolrFacetEntity> getSolrEnums() {
//        return criteria.getFacetFields();
//    }
}
