package com.lzy.block.search.solr.condition;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;

import com.lzy.block.api.constant.newhouse.CityEnum;
import com.lzy.block.search.solr.searcher.NewHouseSearcher;


/**
 * 新房频道索引查询
 * 
 * @author Administrator
 * 
 */
public class NewHouseSearchCondition extends SearchCondition {
	
	private Logger logger=Logger.getLogger(NewHouseSearchCondition.class.getName());
	// 城市
	private CityEnum city;
	
	protected String keyword; // 关键词（可以包括标题，小区名，位置，区域等）
	protected List<String> andKeywords; // 关键词AND检索
	protected List<String> orKeywords; // 关键词OR检索
	
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
	private List<String> newhouseFacetFields;
	//售价排序:asc desc
	private String priceSort;
	//开盘时间排序:asc desc
	private String openDateSort;
	
	public CityEnum getCity() {
		return city;
	}

	public void setCity(CityEnum city) {
		this.city = city;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<String> getAndKeywords() {
		return andKeywords;
	}

	public void setAndKeywords(List<String> andKeywords) {
		this.andKeywords = andKeywords;
	}

	public List<String> getOrKeywords() {
		return orKeywords;
	}

	public void setOrKeywords(List<String> orKeywords) {
		this.orKeywords = orKeywords;
	}

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
	

	public String getKeywordString(List<String> keywordList, String operType) {
		StringBuffer keywords = new StringBuffer();
		if (keywordList != null && keywordList.size() > 0) {
			for (String kw : keywordList) {
				if (keywords.length() > 0) {
					keywords.append(" " + operType + " ");
				}
//				kw = StringUtil.solrMetacharacter(kw);
				keywords.append("keyword:\"" + kw + "\"");
			}
		}
		return keywords.toString();
	}
	
	@Override
	public SolrQuery getSolrQuery() {
		SolrQuery solrQuery = super.getSolrQuery();
		if (city != null) {
			solrQuery.addFilterQuery(NewHouseSearcher.CITY+":" + city.toString());
		}
		
		//单个关键字
		if(StringUtils.isNotEmpty(keyword)){
			solrQuery.addFilterQuery("keyword:\"" + keyword+"\"");
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
		
		
		//父区域
		if(StringUtils.isNotBlank(parentAreaName)){
			solrQuery.addFilterQuery(NewHouseSearcher.PARENT_AREA_NAME+":"+parentAreaName);
		}
		
		
		//二级区域
		if(StringUtils.isNotBlank(areaName)){
			solrQuery.addFilterQuery(NewHouseSearcher.AREA_NAME+":"+areaName);
		}
		
		//均价
		if(StringUtils.isNotBlank(avgPrice)){
			solrQuery.addFilterQuery(NewHouseSearcher.AVGPRICE+":"+avgPrice);
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
		
		
		//是否只查团购
		if(onlyGroupbuy!=null&&onlyGroupbuy==true){
			solrQuery.addFilterQuery(NewHouseSearcher.GROUPBUY_PRODUCT_ID +" : * ");
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
		
		/* ID 包括 */
//		if (roomIds != null && roomIds.size() > 0) {
//			StringBuilder query = new StringBuilder();
//			for (Integer roomId : roomIds) {
//				query.append(" " + (isNotEqRoomIds ? "-" : "") + AbstractSearcher.ID + ":" + roomId);
//			}
//			solrQuery.addFilterQuery(query.substring(1));
//		}


		/* 经纪人ID 包括 */
//		if (personIds != null && personIds.size() > 0) {
//			StringBuilder query = new StringBuilder();
//			for (Integer personId : personIds) {
//				query.append(" " + AbstractSearcher.PERSON_ID + ":" + personId);
//			}
//			solrQuery.addFilterQuery(query.substring(1));
//		}

		
		// 价格
//		if (priceFrom != null && priceTo != null) {
//			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[" + priceFrom + " TO " + priceTo + "]");
//		}
//		if (priceFrom != null && priceTo == null) {
//			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[" + priceFrom + " TO *]");
//		}
//		if (priceFrom == null && priceTo != null) {
//			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[* TO " + priceTo + "]");
//		}
		// 发布时间
//		if (StringUtils.isNotEmpty(createTime)) {
//			solrQuery.addFilterQuery(AbstractSearcher.CREATE_TIME + ":[" + createTime + " TO *]");
//		}
//		// 更新时间,这里只取一天的数据
//		if (StringUtils.isNotEmpty(updateTime)) {
//			if (updateTimeFlag) {
//				solrQuery.addFilterQuery(AbstractSearcher.UPDATE_TIME + ":[" + updateTime + "T00:00:000Z TO " + updateTime + "T23:59:590Z]");
//			} else {
//				solrQuery.addFilterQuery(AbstractSearcher.UPDATE_TIME + ":[* TO " + updateTime + "T00:00:000Z]");
//			}
//		}

		
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
