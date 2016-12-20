package com.lzy.block.search.solr.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.lzy.block.search.solr.condition.SearchCondition;
import com.lzy.block.search.solr.page.Pagination;
import com.lzy.block.search.solr.page.SolrPagination;


/**
 * 集成Spring
 * @param <T>
 */
public abstract class SolrTemplate<T> {
	protected final static Logger logger = Logger.getLogger(SolrTemplate.class);
	private SolrServerFactory solrServerFactory;
	private String solrServerUrl;
	private String shards;
	private String defaultSolrName;//默认查询solr对象名称
	private Integer zkClientTimeout;//请求超时时间
	private Integer zkConnectTimeout;//链接超时时间
	private String defaultZkHost;//默认的zookepp地址
	
	public void setDefaultZkHost(String defaultZkHost) {
		this.defaultZkHost = defaultZkHost;
	}

	public String getDefaultSolrName() {
		return defaultSolrName;
	}

	public void setDefaultSolrName(String defaultSolrName) {
		this.defaultSolrName = defaultSolrName;
	}

	public Integer getZkClientTimeout() {
		return zkClientTimeout;
	}

	public void setZkClientTimeout(Integer zkClientTimeout) {
		this.zkClientTimeout = zkClientTimeout;
	}

	public Integer getZkConnectTimeout() {
		return zkConnectTimeout;
	}

	public void setZkConnectTimeout(Integer zkConnectTimeout) {
		this.zkConnectTimeout = zkConnectTimeout;
	}

	public String getZkHost() {
		return this.defaultZkHost;
	}


	public String getSolrName() {
		return this.defaultSolrName;
	}


	public void setSolrServerFactory(SolrServerFactory solrServerFactory) {
		this.solrServerFactory = solrServerFactory;
	}

	public void setSolrServerUrl(String solrServerUrl) {
		this.solrServerUrl = solrServerUrl;
	}

	public String getSolrServerUrl() {
		return solrServerUrl;
	}

	public void setShards(String shards) {
		this.shards = shards;
	}

	public Object execute(SolrCallback solrCallback) {
		SolrServer solrServer = null; 
		if(getZkHost()!=null){//当有皮遏制zkhost的时候 就视为使用了solrcloud
			solrServer = solrServerFactory.getCloudSolrServer(getZkHost(), getSolrName(), zkClientTimeout, zkConnectTimeout);
		}else{
			if(getSolrServerUrl() == null) {
				throw new RuntimeException("solrServerUrl must be specified");
			}
			if(shards == null || shards.trim().equals("")) {
				solrServer = solrServerFactory.getCommonsHttpSolrServer(getSolrServerUrl());
			} else {
				solrServer = solrServerFactory.getShardSolrServer(getSolrServerUrl(), shards, "id");
			}
		}
		try { 
			return solrCallback.doInSolr(solrServer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 根据SolrQuery查询
	 * @param <T>
	 * @param solrQuery
	 * @param pagination
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pagination<T> query(final SolrQuery solrQuery, final Pagination<T> pagination) {
		return (Pagination<T>) execute(new SolrCallbackHandler<T>(this, solrQuery, pagination));
	}
	
    @SuppressWarnings("unchecked")
    public SolrPagination<T> queryFacet(SearchCondition criteria, SolrPagination<T> pagination) {
        pagination.setCriteria(criteria);
	    return (SolrPagination<T>) execute(new FacetCallbackHandler<T>(this, criteria.getSolrQuery(), pagination));
	}
	
	protected List<T> solrDocumentsToEntities(SolrDocumentList solrDocumentList) throws Exception {
		List<T> entities = new ArrayList<T>();
		for(SolrDocument solrDocument : solrDocumentList) {
			entities.add(solrDocumentToEntity(solrDocument));
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
    public Pagination<T> queryGroup(SearchCondition criteria, Pagination<T> pagination) {
        return (Pagination<T>) execute(new GroupCallbackHandler<T>(this, criteria.getSolrQuery(), pagination));
    }

	/**
	 * 子类实现该方法做document和entity之间的转换
	 * 
	 * @param <T>
	 * @param solrDocumentList
	 * @return
	 * @throws Exception
	 */
	public abstract T solrDocumentToEntity(SolrDocument solrDocument) throws Exception;

	/**
	 * 提交数据
	 * @param obj
	 * @param server
	 */
	@SuppressWarnings("serial")
	public void save(final T entity) {
		saveList(new ArrayList<T>(){{add(entity);}});
	}
	
	public abstract SolrInputDocument entityToSolrInputDocument(T entity) throws Exception;
	
	/**
	 * 批量保存
	 * @param <T>
	 * @param entities
	 */
	public void saveList(final List<T> entities) {
		execute(new SolrCallback() {
			public Object doInSolr(SolrServer solrServer) throws Exception {
				solrServer.add(entitiesToSolrInputDocuments(entities));
				solrServer.commit(false, false,true);
				logger.info("save entities successfully :" + entities.size());
				return null;
			}
		});
	}
	
	protected List<SolrInputDocument> entitiesToSolrInputDocuments(List<T> entities) throws Exception {
		List<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();
		for(T entity : entities) {
			SolrInputDocument doc=entityToSolrInputDocument(entity);
			if(doc!=null){
				solrInputDocuments.add(doc);
			}
		}
		return solrInputDocuments;
	}
	
	/**
	 * 提交数据采用批量提交
	 */
	public void updateList(final List<T> entities) {
		if(entities==null || entities.size()==0){
			return;
		}
		saveList(entities);
	}
	
	@SuppressWarnings("serial")
	public void update(final T entity) {
		updateList(new ArrayList<T>(){{add(entity);}});
	}
	
	/**
	 * 根据ID删除索引
	 * 
	 * @param id
	 */
	@SuppressWarnings("serial")
	public void deleteById(final String id) {
		deleteByIds(new ArrayList<String>(){{add(id);}});
	}
	
	/**
	 * 根据ID批量删除索引
	 * 
	 * @param id
	 */
	public void deleteByIds(final List<String> ids) {
		if(ids==null || ids.size()==0){
			return;
		}
		execute(new SolrCallback(){
			public Object doInSolr(SolrServer solrServer) throws Exception {
				solrServer.deleteById(ids);
				solrServer.commit(false, false);
				logger.info("deleteByIds successfully : " + ids.size());
				return null;
			}
		});
	}
	
	/**
	 * 根据query批量删除索引
	 * 
	 * @param id
	 */
	public void deleteByQuery(final String query) {
		execute(new SolrCallback(){
			public Object doInSolr(SolrServer solrServer) throws Exception {
				solrServer.deleteByQuery(query);
				solrServer.commit(false, false);
				logger.info("deleteByQuery successfully : " + query);
				return null;
			}
		});
	}
	
	/**
	 * 删除所有元素
	 * 
	 * @param server
	 */
	public void deleteAll() {
		execute(new SolrCallback(){
			public Object doInSolr(SolrServer solrServer) throws Exception {
				solrServer.deleteByQuery("*:*");
				solrServer.commit(false, false);
				logger.info("deleteAll successfully");
				return null;
			}
		});
	}
	
	/**
	 * 与SOLR SYSTEM PING, 主要是检测solr是否down掉
	 * 
	 * @param server
	 * @return String
	 */
	public String ping() {
		return (String) execute(new SolrCallback(){
			public Object doInSolr(SolrServer solrServer) throws Exception {
				return solrServer.ping().getResponse().toString();
			}
		});
	}
	
	/**
	 * 优化solr数据存储结构
	 * 
	 */
	public void optimize(final int maxSegments) {
		execute(new SolrCallback(){
			public Object doInSolr(SolrServer solrServer) throws Exception {
				logger.info("Optimize solr start ...");
				long now = System.currentTimeMillis();
				solrServer.optimize(false, false, maxSegments);
				logger.info("Optimize solr end, time = " + (System.currentTimeMillis() - now));
				return null;
			}
		});
	}
}
