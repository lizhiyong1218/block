package com.lzy.block.search.solr.spring;

import org.apache.solr.client.solrj.SolrServer;


/**
 * SolrServer回调
 * 
 * @author Hou Peiqin
 *
 */
public interface SolrCallback {
	Object doInSolr(SolrServer solrServer) throws Exception;
}