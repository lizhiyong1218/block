package com.lzy.block.search.solr.condition;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.params.DisMaxParams;

import com.lzy.block.search.solr.searcher.NewHouseSearcher;


/**
 * 新房频道索引查询
 * 
 * @author Administrator
 * 
 */
public class NewHouseSearchCondition extends SearchCondition {
	
	private Logger logger=Logger.getLogger(NewHouseSearchCondition.class.getName());
	
	//父区域名称
	private String parentAreaName;
	//区域名称
	private String areaName;
	//均价
	private String avgPrice;
	//销售状态
	private String saleStatus;
	//楼盘特色
	private List<String> features;
	//地铁线
	private String lineName;
	//地铁站
	private String subwayStation;
	//小区状态
	private String gardenStatus;
	//新房状态
	private String newHouseStatus;
	//公交线
	private List<String> busLines;
	//公交站Id
	private List<String> busStationIds;
	//公交站名称
	private List<String> busStationNames;
	// 经度
	private BigDecimal longitude;
	// 纬度
	private BigDecimal latitude; 
	//全拼
	private String quanpin;
	//简拼
	private String jianpin;
	//拼音
	private String pinyin;
	//是否只查团购
	private Boolean onlyGroupbuy;
	
	private String location;// 经纬度，格式：31.2315430,121.5765490
	private BigDecimal distance;// 距离经纬度location的距离
	
	private List<String> newhouseFacetFields;
	
	//售价排序:asc desc
	private String priceSort;
	//开盘时间排序:asc desc
	private String openDateSort;
	
	public String getGardenStatus() {
		return gardenStatus;
	}

	public void setGardenStatus(String gardenStatus) {
		this.gardenStatus = gardenStatus;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
	}

	public String getOpenDateSort() {
		return openDateSort;
	}

	public void setOpenDateSort(String openDateSort) {
		this.openDateSort = openDateSort;
	}
	
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSubwayStation() {
		return subwayStation;
	}

	public void setSubwayStation(String subwayStation) {
		this.subwayStation = subwayStation;
	}

	public String getNewHouseStatus() {
		return newHouseStatus;
	}

	public void setNewHouseStatus(String newHouseStatus) {
		this.newHouseStatus = newHouseStatus;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public List<String> getBusLines() {
		return busLines;
	}

	public void setBusLines(List<String> busLines) {
		this.busLines = busLines;
	}

	public List<String> getBusStationIds() {
		return busStationIds;
	}

	public void setBusStationIds(List<String> busStationIds) {
		this.busStationIds = busStationIds;
	}

	public List<String> getBusStationNames() {
		return busStationNames;
	}

	public void setBusStationNames(List<String> busStationNames) {
		this.busStationNames = busStationNames;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getQuanpin() {
		return quanpin;
	}

	public void setQuanpin(String quanpin) {
		this.quanpin = quanpin;
	}

	public String getJianpin() {
		return jianpin;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
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
	

	public List<String> getNewhouseFacetFields() {
		return newhouseFacetFields;
	}

	public void setNewhouseFacetFields(List<String> newhouseFacetFields) {
		this.newhouseFacetFields = newhouseFacetFields;
	}

	public Boolean getOnlyGroupbuy() {
		return onlyGroupbuy;
	}

	public void setOnlyGroupbuy(Boolean onlyGroupbuy) {
		this.onlyGroupbuy = onlyGroupbuy;
	}
	
	@Override
	public SolrQuery getSolrQuery() {
		SolrQuery solrQuery = new SolrQuery();
		if (query != null) {
			solrQuery.setQuery(query);
		} else {
			solrQuery.setQuery("*:*");
		}
		if(StringUtils.isNotEmpty(keyword)){
			solrQuery.addFilterQuery("keyword:\"" + keyword+"\"");
		}
		if (city != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.CITY+":" + city.toString());
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
		
		/* 关键词AND检索 */
		if (andKeywords != null && andKeywords.size() > 0) {
			String keywords = getKeywordString(andKeywords, "AND");
			solrQuery.addFilterQuery(keywords.toString());
		}

		/* 关键词OR检索 */
		if (orKeywords != null && orKeywords.size() > 0) {
			String keywords = getKeywordString(orKeywords, "OR");
			solrQuery.addFilterQuery(keywords.toString());
		}
		
		if(newhouseFacetFields!=null&&newhouseFacetFields.size()>0){
			solrQuery.setFacet(true);
			for (String field : newhouseFacetFields) {
				solrQuery.set("facet.field", field);
			}
			solrQuery.setFacetMinCount(1);
		}
		
		if(StringUtils.isNotEmpty(parentAreaId)){
			solrQuery.addFilterQuery(NewHouseSearcher.PARENT_AREA_Id+":"+parentAreaId);
		}
		
		//父区域
		if(StringUtils.isNotBlank(parentAreaName)){
			solrQuery.addFilterQuery(NewHouseSearcher.PARENT_AREA_NAME+":"+parentAreaName);
		}
		
		if(StringUtils.isNotEmpty(areaId)){
			solrQuery.addFilterQuery(NewHouseSearcher.AREA_Id+":"+areaId);
		}
		
		//二级区域
		if(StringUtils.isNotBlank(areaName)){
			solrQuery.addFilterQuery(NewHouseSearcher.AREA_NAME+":"+areaName);
		}
		
		//均价
		if(StringUtils.isNotBlank(avgPrice)){
			solrQuery.addFilterQuery(NewHouseSearcher.AVGPRICE+":"+avgPrice);
		}
		
		if (priceFrom != null && priceTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.AVGPRICE + ":[" + priceFrom + " TO " + priceTo + "]");
		}
		if (priceFrom != null && priceTo == null) {
			solrQuery.addFilterQuery(NewHouseSearcher.AVGPRICE + ":[" + priceFrom + " TO *]");
		}
		if (priceFrom == null && priceTo != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.AVGPRICE + ":[* TO " + priceTo + "]");
		}
		
		
		// 物业类型
		if (propertyTypeEnum != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.PROPERTY_TYPE + ":" + propertyTypeEnum);
		}
		
		//销售状态
		if(StringUtils.isNotBlank(saleStatus)){
			solrQuery.addFilterQuery(NewHouseSearcher.SALE_STATUS + ":" + saleStatus);
		}
		
		//新房状态
		if(StringUtils.isNotBlank(newHouseStatus)){
			solrQuery.addFilterQuery(NewHouseSearcher.NEWHOUSE_STATUS + ":" + newHouseStatus);
		}
		
		//地铁线
		if(StringUtils.isNotBlank(lineName)){
			solrQuery.addFilterQuery(NewHouseSearcher.LINE_NAME + ":" + lineName);
		}
		
		//地铁站
		if(StringUtils.isNotBlank(subwayStation)){
			solrQuery.addFilterQuery(NewHouseSearcher.SUBWAY_STATION + ":" + subwayStation);
		}
		
		//楼盘特色
		if(features!=null&&features.size()>0){
			for (String str : features) {
				solrQuery.addFilterQuery(NewHouseSearcher.FEATURE+":"+str);
			}
		}
		
		//户型
//		if(layoutTypes!=null && layoutTypes.size()>0){
//			StringBuffer stringBuffer=new StringBuffer("(");
//			for (LayoutTypeEnum layoutType : layoutTypes) {
//				stringBuffer.append(NewHouseSearcher.LAYOUT_TYPE);
//				stringBuffer.append(":");
//				stringBuffer.append(layoutType.getKey());
//				stringBuffer.append(" OR ");
//			}
//			String str=stringBuffer.toString();
//			str=str.substring(0, str.lastIndexOf("OR"));
//			str+=")";
//			solrQuery.addFilterQuery(str);
//		}
		
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
		
		//是否只查团购
		if(onlyGroupbuy!=null&&onlyGroupbuy==true){
			solrQuery.addFilterQuery(NewHouseSearcher.GROUPBUY_PRODUCT_ID +" : * ");
		}
		
		if(gardenId!=null&&gardenId>0){
			solrQuery.addFilterQuery(NewHouseSearcher.GARDEN_ID +" :  " +gardenId);
		}
		
		//小区状态
//		if(StringUtils.isNotEmpty(gardenStatus)){ //查询新房索引时不管
//			solrQuery.addFilterQuery(NewHouseSearcher.GARDEN_STATUS+":"+gardenStatus);
//		}
		if(gardenName!=null){
			solrQuery.addFilterQuery(NewHouseSearcher.GARDEN_NAME+":\""+gardenName+"\"");
		}
		//全拼
		if(StringUtils.isNotEmpty(quanpin)){
			solrQuery.addFilterQuery(NewHouseSearcher.QUANGPIN+":"+quanpin);
		}
		
		//简拼
		if(StringUtils.isNotEmpty(jianpin)){
			solrQuery.addFilterQuery(NewHouseSearcher.JIANPIN+":"+jianpin);
		}
		//拼音
		if(StringUtils.isNotEmpty(pinyin)){
			solrQuery.addFilterQuery(NewHouseSearcher.PINYIN+":"+pinyin);
		}
		
		//按售价排序
		if(StringUtils.isNotEmpty(priceSort)){
			if(priceSort.toLowerCase().equals("asc")){
				solrQuery.addSortField(NewHouseSearcher.AVGPRICE, ORDER.asc);
			}else if(priceSort.toLowerCase().equals("desc")){
				solrQuery.addSortField(NewHouseSearcher.AVGPRICE, ORDER.desc);
			}
		}
		
		//按开盘时间排序
		if(StringUtils.isNotEmpty(openDateSort)){
			if(openDateSort.toLowerCase().equals("asc")){
				solrQuery.addSortField(NewHouseSearcher.OPEN_DATE, ORDER.asc);
			}else if(openDateSort.toLowerCase().equals("desc")){
				solrQuery.addSortField(NewHouseSearcher.OPEN_DATE, ORDER.desc);
			}
		}
		
		//默认排序
		if(!StringUtils.isNotEmpty(openDateSort)&&!StringUtils.isNotEmpty(priceSort)){
//			//按置顶号升序排序
//			solrQuery.addSortField(NewHouseSearcher.STICK_NO, ORDER.asc);
//			//按点击数升序排序
//			solrQuery.addSortField(NewHouseSearcher.CLICK_COUNT, ORDER.desc);
//			//按创建时间降序排序
//			solrQuery.addSortField(NewHouseSearcher.CREATE_TIME, ORDER.desc);
			
    		String order="newHouseSort(saleStatus,createTime,gardenId,newhouseType)";
    		solrQuery.addSortField(order, ORDER.desc);
			
		}
		
		
		logger.debug(solrQuery.toString().replace("%3A", ":"));
		return solrQuery;
	}
}
