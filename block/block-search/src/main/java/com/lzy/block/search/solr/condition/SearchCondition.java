package com.lzy.block.search.solr.condition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.DisMaxParams;

import com.lzy.block.search.solr.SolrFacetEntity;
import com.lzy.block.search.solr.searcher.NewHouseSearcher;

/**
 * 售盘查询条件
 * 
 * @author Administrator
 * 
 */
public abstract class SearchCondition{
	protected String queryType;// 查询类型
	protected String defType;// 默认查询类型
	protected String[] fields;// 返回字段列表
	protected String pf;// 匹配字段
	protected String qf;// 查询字段权重
	protected String bf;// 函数权重
	protected String query;// 查询字符串
	protected String filterQuery;// 过滤查询字符串

	protected boolean isFacet;// 是否facet查询
	protected List<SolrFacetEntity> facetFields;
	
	public boolean isGroup;// 是否facet查询
	protected String groupField;// 单个group查询
	protected List<String> groupFields;// 多个group查询
	protected int groupLimit;// 多个group查询
	protected String groupSortByField;
	protected String groupSortBy;
	
	protected String location;// 经纬度，格式：31.2315430,121.5765490
	protected BigDecimal distance;// 距离经纬度location的距离
	protected BigDecimal longitudeFrom;//经度开始
	protected BigDecimal longitudeTo;//经度结束
	protected BigDecimal latitudeFrom;//纬度开始
	protected BigDecimal latitudeTo;//纬度结束
	protected String createStartDate;//创建时间开始日期
	protected String createEndDate;//创建时间结束日期
	
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getDefType() {
		return defType;
	}

	public void setDefType(String defType) {
		this.defType = defType;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getQf() {
		return qf;
	}

	public void setQf(String qf) {
		this.qf = qf;
	}

	public String getBf() {
		return bf;
	}

	public void setBf(String bf) {
		this.bf = bf;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getFilterQuery() {
		return filterQuery;
	}

	public void setFilterQuery(String filterQuery) {
		this.filterQuery = filterQuery;
	}


	public boolean isFacet() {
		return isFacet;
	}

	public void setFacet(boolean isFacet) {
		this.isFacet = isFacet;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public List<String> getGroupFields() {
		return groupFields;
	}

	public void setGroupFields(List<String> groupFields) {
		this.groupFields = groupFields;
	}

	public int getGroupLimit() {
		return groupLimit;
	}

	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}

	public String getGroupSortByField() {
		return groupSortByField;
	}

	public void setGroupSortByField(String groupSortByField) {
		this.groupSortByField = groupSortByField;
	}

	public String getGroupSortBy() {
		return groupSortBy;
	}

	public void setGroupSortBy(String groupSortBy) {
		this.groupSortBy = groupSortBy;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getLongitudeFrom() {
		return longitudeFrom;
	}

	public void setLongitudeFrom(BigDecimal longitudeFrom) {
		this.longitudeFrom = longitudeFrom;
	}

	public BigDecimal getLongitudeTo() {
		return longitudeTo;
	}

	public void setLongitudeTo(BigDecimal longitudeTo) {
		this.longitudeTo = longitudeTo;
	}

	public BigDecimal getLatitudeFrom() {
		return latitudeFrom;
	}

	public void setLatitudeFrom(BigDecimal latitudeFrom) {
		this.latitudeFrom = latitudeFrom;
	}

	public BigDecimal getLatitudeTo() {
		return latitudeTo;
	}

	public void setLatitudeTo(BigDecimal latitudeTo) {
		this.latitudeTo = latitudeTo;
	}

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	//---------------------------------------//
	public void setFields(String... fields) {
		this.fields = fields;
	}
	
	public void setFields(List<String> fields) {
		if(fields!=null && fields.size()!=0){
			this.fields = fields.toArray(new String[fields.size()]);
		}
	}
	
	public String[] getFields() {
		return this.fields;
	}

	public void setFacetFields(List<SolrFacetEntity> facetFields) {
		this.facetFields = facetFields;
	}
	
	public List<SolrFacetEntity> getFacetFields() {
		return facetFields;
	}
	
	public void addFacetField(SolrFacetEntity facetField) {
		initFacetFieldList();
		this.facetFields.add(facetField);
	}

	public void addFacetFields(SolrFacetEntity[] values) {
		initFacetFieldList();
		for (SolrFacetEntity solrEnum : values) {
			this.facetFields.add(solrEnum);
		}
	}
	
	private void initFacetFieldList() {
		if (this.facetFields == null) {
			this.facetFields = new ArrayList<SolrFacetEntity>();
		}
	}

	public void clearFacetFields() {
		if (this.facetFields != null) {
			this.facetFields.clear();
		}
	}

	public SolrQuery getSolrQuery() {
		SolrQuery solrQuery = new SolrQuery();
		if (query != null) {
			solrQuery.setQuery(query);
		} else {
			solrQuery.setQuery("*:*");
		}
		 
		if (filterQuery != null) {
			solrQuery.setFilterQueries(filterQuery);
		}

		if (null != defType) {
			solrQuery.setParam("defType", defType);
		}

		if (null != queryType) {
			solrQuery.setQueryType(queryType);
		}

		if (null != pf) {
			solrQuery.setParam(DisMaxParams.PF, pf);
		}

		if (null != qf) {
			solrQuery.setParam(DisMaxParams.QF, qf);
		}

		if (null != bf) {
			solrQuery.setParam(DisMaxParams.BF, bf);
		}

		if (null != fields) {
			solrQuery.setFields(fields);
		}

		if (facetFields != null) {
			solrQuery.setFacet(true);
			for (SolrFacetEntity facetField : facetFields) {
				if(facetField!=null){
					solrQuery.addFacetQuery(facetField.createFacetQuery());
				}
			}
		}
		if (isGroup) {
			solrQuery.add("group", "true");
			if (groupFields != null) {
				for (String groupField : groupFields) {
					solrQuery.add("group.field", groupField);
				}
			}
			solrQuery.add("group.field", groupField);
			solrQuery.add("group.limit", String.valueOf(groupLimit));
			if (groupSortByField != null) {
				String groupSortByTemp = groupSortBy != null ? groupSortBy : "asc";
				solrQuery.add("group.sort",groupSortByField+" " + groupSortByTemp);
			}
		}
		
		//距离location坐标distance公里内数据
        if (location!=null&&!location.trim().equals("")) {
            if (null != distance) {
            	solrQuery.addFilterQuery("{!geofilt pt=" + location + " sfield=location d=" + distance.doubleValue() + "}");
            }
        }
		
		// 经纬度范围
		if (latitudeFrom != null && latitudeTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LATITUDE + ":[" + latitudeFrom + " TO " + latitudeTo + "]");
		}
		if (latitudeFrom != null && latitudeTo == null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LATITUDE + ":[" + latitudeFrom + " TO *]");
		}
		if (latitudeFrom == null && latitudeTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LATITUDE + ":[* TO " + latitudeTo + "]");
		}
		
		if (longitudeFrom != null && longitudeTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LONGITUDE + ":[" + longitudeFrom + " TO " + longitudeTo + "]");
		}
		if (longitudeFrom != null && longitudeTo == null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LONGITUDE + ":[" + longitudeFrom + " TO *]");
		}
		if (longitudeFrom == null && longitudeTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.LONGITUDE + ":[* TO " + longitudeTo + "]");
		}
		
		if (StringUtils.isNotEmpty(createStartDate) && StringUtils.isNotEmpty(createEndDate)) {
			solrQuery.addFilterQuery(NewHouseSearcher.CREATE_TIME + ":[" + createStartDate + "T00:00:000Z TO " + createEndDate + "T00:00:000Z]");
		} else {
			if (StringUtils.isNotEmpty(createStartDate)) {
				solrQuery.addFilterQuery(NewHouseSearcher.CREATE_TIME + ":[" + createStartDate + "T00:00:000Z TO *");
			}
			if (StringUtils.isNotEmpty(createEndDate)) {
				solrQuery.addFilterQuery(NewHouseSearcher.CREATE_TIME + ":[*" + " TO " + createEndDate + "T00:00:000Z]");
			}
		}
		
		return solrQuery;
	}
}