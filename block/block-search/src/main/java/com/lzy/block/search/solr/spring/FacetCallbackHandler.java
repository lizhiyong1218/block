package com.lzy.block.search.solr.spring;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.lzy.block.search.solr.page.Pagination;
import com.lzy.block.search.solr.page.SolrPagination;


/**
 * Facet查询回调处理
 * 
 */
public class FacetCallbackHandler<T> extends SolrCallbackHandler<T> {

    public FacetCallbackHandler(SolrTemplate<T> solrTemplate, SolrQuery solrQuery, Pagination<T> pagination) {
        super(solrTemplate, solrQuery, pagination);
    }
    
    public List<T> parseQueryResponse(QueryResponse response) {
        if (pagination instanceof SolrPagination) {
            SolrPagination<T> page = (SolrPagination<T>)pagination;
            page.setFacet(response);
        }
        return super.parseQueryResponse(response);
    }

}
