package com.lzy.block.search.solr.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 集成Spring
 * 
 * 
 */
public class SolrServerFactoryBean implements FactoryBean<SolrServerFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {
	private final static Logger logger = Logger.getLogger(SolrServerFactoryBean.class);
	private SolrServerFactory solrServerFactory;
	 
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		solrServerFactory = new SolrServerFactory();
		logger.info("******** SolrServerFactory init finished ***********");
	}
	@Override
	public SolrServerFactory getObject() throws Exception {
		if (solrServerFactory == null) {
			afterPropertiesSet();
		}
		return this.solrServerFactory;
	}
	@Override
	public Class<?> getObjectType() {
		return this.solrServerFactory == null ? SolrServerFactory.class : this.solrServerFactory.getClass();
	}
	@Override
	public boolean isSingleton() {
		return true;
	}
}
