package com.lzy.block.search.solr;

public interface SolrFacetEntity {
    
    String createFacetQuery();
    void setCount(int count);
    int getCount();

}
