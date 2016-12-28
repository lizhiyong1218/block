package com.lzy.block.search.solr.spring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.lzy.block.search.solr.page.SolrPagination;
import com.lzy.block.search.solr.page.SolrPagination.Sort;


public class SolrCallbackHandler<T> implements SolrCallback {
    
    protected SolrPagination<T> pagination;
    protected SolrQuery solrQuery;
    protected SolrTemplate<T> solrTemplate;

    public SolrCallbackHandler(SolrTemplate<T> solrTemplate, SolrQuery solrQuery, SolrPagination<T> pagination){
        this.solrTemplate = solrTemplate;
        this.solrQuery = solrQuery;
        this.pagination = pagination;
    }

    public Object doInSolr(SolrServer solrServer) throws Exception {
        if(pagination != null && pagination.getSortField() != null) {
        	solrQuery.addSort(SortClause.create(pagination.getSortField(), pagination.getSort().equals(Sort.DESC) ? ORDER.desc : ORDER.asc));
            //solrQuery.addSortField(pagination.getSortField(), pagination.getSort().equals(Sort.DESC) ? ORDER.desc : ORDER.asc);
        }
        solrQuery.setStart(pagination.getCurrentPageStartIndex());
        solrQuery.setRows(pagination.getPageSize());
        QueryResponse response = solrServer.query(solrQuery, METHOD.POST);
        List<T> items = parseQueryResponse(response);
        pagination.setItems(items);
        return pagination;
    }

    public List<T> parseQueryResponse(QueryResponse response) {
        SolrDocumentList solrDocumentList = response.getResults();
        setHighlighting(solrDocumentList, response.getHighlighting());
        List<T> items = null;
        try {
            items = solrTemplate.solrDocumentsToEntities(solrDocumentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pagination.setRecordCount(Long.valueOf(response.getResults().getNumFound()).intValue());
        return items;
    }
    
    private void setHighlighting(SolrDocumentList solrDocumentList, Map<String, Map<String, List<String>>> highlightMap) {
		if (highlightMap != null) {
			try {
				List<String> fieldList = new ArrayList<String>();
				fieldList.add("title");
				fieldList.add("gardenName");
				fieldList.add("gardenAddress");

				Iterator<SolrDocument> iterator = solrDocumentList.iterator();
				while (iterator.hasNext()) {
					SolrDocument doc = iterator.next();
					if (doc.containsKey("id")) {
						String id = doc.getFieldValue("id").toString();
						Map<String, List<String>> resultMap = (Map<String, List<String>>) highlightMap.get(id);
						if (resultMap != null && resultMap.size() > 0) {
							for (String field : fieldList) {
								if (doc.containsKey(field)) {
									List<String> list = resultMap.get(field);
									if (list != null && list.size() > 0) {
										doc.setField(field, list.get(0));
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
}
