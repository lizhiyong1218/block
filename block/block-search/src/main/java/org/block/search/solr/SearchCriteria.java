package org.block.search.solr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.DisMaxParams;

//import com.qfang.common.ContextHolder;
//import com.qfang.common.util.StringUtil;
//import com.qfang.model.enums.CityEnum;
//import com.qfang.model.enums.DecorationEnum;
//import com.qfang.model.enums.PropertyTypeEnum;
//import com.qfang.model.enums.RentTypeEnum;
//import com.qfang.model.enums.RoomBizTypeEnum;
//import com.qfang.model.enums.RoomTypeEnum;
//import com.qfang.model.solr.SolrFacetEntity;
//import com.qfang.search.solr.AbstractSearcher;

/**
 * 售盘查询条件
 * 
 * @author Administrator
 * 
 */
//public abstract class SearchCriteria extends SearchParam {
public abstract class SearchCriteria{//TODO
	private static final long serialVersionUID = 1L;
//	protected CityEnum city;// 城市
	protected String queryType;// 查询类型
	protected String defType;// 默认查询类型
	protected String[] fields;// 返回字段列表
	protected String pf;// 匹配字段
	protected String qf;// 查询字段权重
	protected String bf;// 函数权重
	protected String query;// 查询字符串
	protected String filterQuery;// 过滤查询字符串
	protected String title;// 标题
	protected String keyword; // 关键词（可以包括标题，小区名，位置，区域等）
	protected List<String> andKeywords; // 关键词AND检索
	protected List<String> orKeywords; // 关键词OR检索
	protected String areaId; // 二级区域ID
	protected String parentAreaId; // 大区域ID
	protected List<Integer> parentAreaIds; // 大区域ID
	protected List<Integer> areaIds; // 小区域ID
	protected String businessId; // 二级区域ID
	protected List<Integer> businessIds; // 商圈ID列表
	protected List<String> businessNames; // 商圈名称列表
	protected String officeBusinessId; // 写字楼商圈ID列表
	protected List<Integer> officeBusinessIds; // 写字楼商圈ID列表
	protected BigDecimal priceFrom; // 价格区间开始
	protected BigDecimal priceTo; // 价格区间结束
	protected BigDecimal saleFrom; // 售价区间开始
	protected BigDecimal saleTo; // 售价区间结束
	protected BigDecimal rentFrom; // 租价区间开始
	protected BigDecimal rentTo; // 租价区间结束
	protected Integer areaFrom; // 面积区间开始
	protected Integer areaTo; // 面积区间结束
	protected Integer stationGardenFrom; // 到地铁站距离开始
	protected Integer stationGardenTo; // 到地铁站距离结束
	protected Integer floorFrom;// 层数开始
	protected Integer floorTo;// 层数结束
	protected BigDecimal longitudeFrom;// 经度开始
	protected BigDecimal longitudeTo;// 经度结束
	protected BigDecimal latitudeFrom;// 纬度开始
	protected BigDecimal latitudeTo;// 纬度结束
	protected String orderByField;// 排序字段
	protected String orderBy;// 排序方式
	protected Integer personId;// 经纪人ID
	protected Integer gardenId;// 小区ID
	protected String gardenName;// 小区name
	protected String gardenAddress;// 小区地址
	protected List<String> gardenNames;// 小区名字集合
	protected String subwayStation;// 地铁站点
	protected String subwayLine;// 地铁线路
	protected Integer manyImage;// 多图
	protected String createTime;// 发布时间
	protected String updateTime;// 更新时间
	protected List<Integer> roomIds;
	protected List<Integer> gardenIds;
	protected List<Integer> personIds;
	protected Integer ranking;// 评分
	protected Integer isExcellent;// 是否推荐
	protected BigDecimal unitPriceFrom;// 单价开始
	protected BigDecimal unitPriceTo;// 单价结束
//	protected RoomBizTypeEnum bizType;// 类型
	protected boolean isFacet;// 是否facet查询
	public boolean isGroup;// 是否facet查询
	protected String groupField;// 单个group查询
	protected List<String> groupFields;// 多个group查询
	protected String groupSortByField;
	protected String groupSortBy;
	protected int groupLimit;// 多个group查询
	protected boolean isNotEqGardenIds;// 不等于小区IDS
	protected boolean isNotEqAreaId;// 不等于二级区域ID
	protected boolean isNotEqParentAreaId;// 不等于大区域ID
	protected boolean isNotEqParentAreaIds;// 不等于大区域IDS
	protected boolean isNotEqPrice;// 不等于价格
	protected boolean isNotEqRoomIds;// 不等于房源IDS
	protected boolean isNotEqOfficeBusinessIds;// 不等于写字楼商圈ids
	public boolean updateTimeFlag = false;// 用判断updatetime的查询语句
	protected String createStartDate;// 发布时间开始日期
	protected String createEndDate;// 发布时间结束日期
	protected String location;// 经纬度，格式：31.2315430,121.5765490
	protected BigDecimal distance;// 距离经纬度location的距离
	protected boolean orderByDistance = true;
	protected Integer roomAgeFrom;// 房龄开始
	protected Integer roomAgeTo;// 房龄结束
//	protected PropertyTypeEnum propertyTypeEnum; // 物业类型
//	protected List<SolrFacetEntity> facetFields;
//	protected RentTypeEnum rentType;// 出租方式
//	protected DecorationEnum decoration;// 装修类型
	protected String schoolRoomtype;//学校子类型
	protected String parentSchoolRoomtype;//学校父类型
	protected String schoolGrade;//学校级别
	protected String bigSchoolDistrict;//大学区
	protected String schoolId;//二手房学校ID
	protected Map<String,List<Integer>> schoolIds;//二手房学校IDs
//	protected RoomTypeEnum roomType;// 房间类型
//
//    public RoomTypeEnum getRoomType() {
//        return roomType;
//    }
//
//    public void setRoomType(RoomTypeEnum roomType) {
//        this.roomType = roomType;
//    }
	public Map<String, List<Integer>> getSchoolIds() {
		return schoolIds;
	}

	public void setSchoolIds(Map<String, List<Integer>> schoolIds) {
		this.schoolIds = schoolIds;
	}

	public String getSchoolRoomtype() {
		return schoolRoomtype;
	}

	public void setSchoolRoomtype(String schoolRoomtype) {
		this.schoolRoomtype = schoolRoomtype;
	}
	

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getOfficeBusinessId() {
		return officeBusinessId;
	}

	public void setOfficeBusinessId(String officeBusinessId) {
		this.officeBusinessId = officeBusinessId;
	}
	
	
	public List<Integer> getOfficeBusinessIds() {
		return officeBusinessIds;
	}

	public void setOfficeBusinessIds(List<Integer> officeBusinessIds) {
		this.officeBusinessIds = officeBusinessIds;
	}

	public List<Integer> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Integer> businessIds) {
		this.businessIds = businessIds;
	}

	public List<String> getBusinessNames() {
		return businessNames;
	}

	public void setBusinessNames(List<String> businessNames) {
		this.businessNames = businessNames;
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

	public String getQueryType() {
		return queryType;
	}

	public Integer getStationGardenFrom() {
		return stationGardenFrom;
	}

	public void setStationGardenFrom(Integer stationGardenFrom) {
		this.stationGardenFrom = stationGardenFrom;
	}

	public Integer getStationGardenTo() {
		return stationGardenTo;
	}

	public void setStationGardenTo(Integer stationGardenTo) {
		this.stationGardenTo = stationGardenTo;
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
	
	public String[] getFields() {
		return fields;
	}

	public void setFields(String... fields) {
		this.fields = fields;
	}

	public void setFields(List<String> fields) {
		if(fields!=null && fields.size()!=0){
			this.fields = fields.toArray(new String[fields.size()]);
		}
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
	
//	public CityEnum getCity() {
//		if(city!=null)return city;
//		String dataSource = ContextHolder.getDataSource();
//		if (dataSource == null)
//			return CityEnum.SHENZHEN;
//		return CityEnum.valueOf(dataSource);
//	}
//
//	public void setCity(CityEnum city) {
//		this.city = city;
//	}

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

	public List<Integer> getPersonIds() {
		return personIds;
	}

	public void setPersonIds(List<Integer> personIds) {
		this.personIds = personIds;
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

	public Integer getIsExcellent() {
		return isExcellent;
	}

	public void setIsExcellent(Integer isExcellent) {
		this.isExcellent = isExcellent;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public List<Integer> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(List<Integer> roomIds) {
		this.roomIds = roomIds;
	}

	public List<String> getGardenNames() {
		return gardenNames;
	}

	public void setGardenNames(List<String> gardenNames) {
		this.gardenNames = gardenNames;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

//	public RentTypeEnum getRentType() {
//		return rentType;
//	}
//
//	public void setRentType(RentTypeEnum rentType) {
//		this.rentType = rentType;
//	}
//
//	public DecorationEnum getDecoration() {
//		return decoration;
//	}
//
//	public void setDecoration(DecorationEnum decoration) {
//		this.decoration = decoration;
//	}

	public Integer getManyImage() {
		return manyImage;
	}

	public void setManyImage(Integer manyImage) {
		this.manyImage = manyImage;
	}

	public String getSubwayStation() {
		return subwayStation;
	}

	public void setSubwayStation(String subwayStation) {
		this.subwayStation = subwayStation;
	}

	public String getSubwayLine() {
		return subwayLine;
	}

	public void setSubwayLine(String subwayLine) {
		this.subwayLine = subwayLine;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getGardenId() {
		return gardenId;
	}

	public void setGardenId(Integer gardenId) {
		this.gardenId = gardenId;
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

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public List<Integer> getParentAreaIds() {
		return parentAreaIds;
	}

	public void setParentAreaIds(List<Integer> parentAreaIds) {
		this.parentAreaIds = parentAreaIds;
	}

	public List<Integer> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<Integer> areaIds) {
		this.areaIds = areaIds;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	public Integer getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(Integer areaFrom) {
		this.areaFrom = areaFrom;
	}

	public Integer getAreaTo() {
		return areaTo;
	}

	public void setAreaTo(Integer areaTo) {
		this.areaTo = areaTo;
	}

	public Integer getFloorTo() {
		return floorTo;
	}

	public void setFloorTo(Integer floorTo) {
		this.floorTo = floorTo;
	}

	public Integer getFloorFrom() {
		return floorFrom;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFloorFrom(Integer floorFrom) {
		this.floorFrom = floorFrom;
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

	public boolean isNotEqParentAreaIds() {
		return isNotEqParentAreaIds;
	}

	public void setNotEqParentAreaIds(boolean isNotEqParentAreaIds) {
		this.isNotEqParentAreaIds = isNotEqParentAreaIds;
	}

	public void setUnitPriceFrom(BigDecimal unitPriceFrom) {
		this.unitPriceFrom = unitPriceFrom;
	}

	public BigDecimal getUnitPriceFrom() {
		return unitPriceFrom;
	}

	public void setUnitPriceTo(BigDecimal unitPriceTo) {
		this.unitPriceTo = unitPriceTo;
	}

	public BigDecimal getUnitPriceTo() {
		return unitPriceTo;
	}

//	public void setBizType(RoomBizTypeEnum bizType) {
//		this.bizType = bizType;
//	}
//
//	public RoomBizTypeEnum getBizType() {
//		return bizType;
//	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public String getGardenName() {
		return gardenName;
	}

	public String getGardenAddress() {
		return gardenAddress;
	}

	public void setGardenAddress(String gardenAddress) {
		this.gardenAddress = gardenAddress;
	}

	public void setGardenIds(List<Integer> gardenIds) {
		this.gardenIds = gardenIds;
	}

	public List<Integer> getGardenIds() {
		return gardenIds;
	}

//	public void addFacetField(SolrFacetEntity facetField) {
//		initFacetFieldList();
//		facetFields.add(facetField);
//	}
//
//	public void addFacetFields(SolrFacetEntity[] values) {
//		initFacetFieldList();
//		for (SolrFacetEntity solrEnum : values) {
//			facetFields.add(solrEnum);
//		}
//	}
//
//	private void initFacetFieldList() {
//		if (facetFields == null) {
//			facetFields = new ArrayList<SolrFacetEntity>();
//		}
//	}
//
//	public List<SolrFacetEntity> getFacetFields() {
//		return facetFields;
//	}
//
//	public void clearFacetFields() {
//		if (facetFields != null) {
//			facetFields.clear();
//		}
//	}

	public void setFacet(boolean isFacet) {
		this.isFacet = isFacet;
	}

	public boolean isFacet() {
		return isFacet;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroupFields(List<String> groupFields) {
		this.groupFields = groupFields;
	}

	public List<String> getGroupFields() {
		return groupFields;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}

	public int getGroupLimit() {
		return groupLimit;
	}

	public boolean isNotEqGardenIds() {
		return isNotEqGardenIds;
	}

	public void setNotEqGardenIds(boolean isNotEqGardenIds) {
		this.isNotEqGardenIds = isNotEqGardenIds;
	}

	public boolean isNotEqAreaId() {
		return isNotEqAreaId;
	}

	public void setNotEqAreaId(boolean isNotEqAreaId) {
		this.isNotEqAreaId = isNotEqAreaId;
	}

	public boolean isNotEqParentAreaId() {
		return isNotEqParentAreaId;
	}

	public void setNotEqParentAreaId(boolean isNotEqParentAreaId) {
		this.isNotEqParentAreaId = isNotEqParentAreaId;
	}

	public boolean isNotEqPrice() {
		return isNotEqPrice;
	}

	public void setNotEqPrice(boolean isNotEqPrice) {
		this.isNotEqPrice = isNotEqPrice;
	}

	public boolean isNotEqRoomIds() {
		return isNotEqRoomIds;
	}

	public void setNotEqRoomIds(boolean isNotEqRoomIds) {
		this.isNotEqRoomIds = isNotEqRoomIds;
	}

	public Integer getRoomAgeFrom() {
		return roomAgeFrom;
	}

	public void setRoomAgeFrom(Integer roomAgeFrom) {
		this.roomAgeFrom = roomAgeFrom;
	}

	public Integer getRoomAgeTo() {
		return roomAgeTo;
	}

	public void setRoomAgeTo(Integer roomAgeTo) {
		this.roomAgeTo = roomAgeTo;
	}

//	public PropertyTypeEnum getPropertyTypeEnum() {
//		return propertyTypeEnum;
//	}
//
//	public void setPropertyTypeEnum(PropertyTypeEnum propertyTypeEnum) {
//		this.propertyTypeEnum = propertyTypeEnum;
//	}

//	public String getGardenSolr() {
//		return getSolrName("garden");
//	}

//	public String getSolrName(String name) {
//		if (StringUtils.equals(name, "sale") || StringUtils.equals(name, "rent")) {
//			// 佛山，中山，东莞，惠州 转端口城市solr名称不一样
//			CityEnum city = getCity();
//			switch( city ) {
//			case FOSHAN:
//			case ZHONGSHAN:
//				return "zhuhai_" + name + "_port";
//			case DONGGUAN:
//			case HUIZHOU:
//				return "shenzhen_" + name + "_port";
//			case ZH:
//				return "zhuhai_" + name;
//			}
//		}
//		return getCity().getDataSource().toString().toLowerCase() + "_" + name;
//	}

	public BigDecimal getRentFrom() {
		return rentFrom;
	}

	public void setRentFrom(BigDecimal rentFrom) {
		this.rentFrom = rentFrom;
	}

	public BigDecimal getRentTo() {
		return rentTo;
	}

	public void setRentTo(BigDecimal rentTo) {
		this.rentTo = rentTo;
	}

	public BigDecimal getSaleFrom() {
		return saleFrom;
	}

	public void setSaleFrom(BigDecimal saleFrom) {
		this.saleFrom = saleFrom;
	}

	public BigDecimal getSaleTo() {
		return saleTo;
	}

	public void setSaleTo(BigDecimal saleTo) {
		this.saleTo = saleTo;
	}

	public boolean isUpdateTimeFlag() {
		return updateTimeFlag;
	}

	public void setUpdateTimeFlag(boolean updateTimeFlag) {
		this.updateTimeFlag = updateTimeFlag;
	}

//	public void setFacetFields(List<SolrFacetEntity> facetFields) {
//		this.facetFields = facetFields;
//	}

	public boolean isNotEqOfficeBusinessIds() {
		return isNotEqOfficeBusinessIds;
	}

	public void setNotEqOfficeBusinessIds(boolean isNotEqOfficeBusinessIds) {
		this.isNotEqOfficeBusinessIds = isNotEqOfficeBusinessIds;
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

	public boolean isOrderByDistance() {
		return orderByDistance;
	}

	public void setOrderByDistance(boolean orderByDistance) {
		this.orderByDistance = orderByDistance;
	}
	
	public String getParentSchoolRoomtype() {
		return parentSchoolRoomtype;
	}

	public void setParentSchoolRoomtype(String parentSchoolRoomtype) {
		this.parentSchoolRoomtype = parentSchoolRoomtype;
	}

	public String getSchoolGrade() {
		return schoolGrade;
	}

	public void setSchoolGrade(String schoolGrade) {
		this.schoolGrade = schoolGrade;
	}

	public String getBigSchoolDistrict() {
		return bigSchoolDistrict;
	}

	public void setBigSchoolDistrict(String bigSchoolDistrict) {
		this.bigSchoolDistrict = bigSchoolDistrict;
	}

//	public String getKeywordString(List<String> keywordList, String operType) {
//		StringBuffer keywords = new StringBuffer();
//		if (keywordList != null && keywordList.size() > 0) {
//			for (String kw : keywordList) {
//				if (keywords.length() > 0) {
//					keywords.append(" " + operType + " ");
//				}
//				kw = StringUtil.solrMetacharacter(kw);
//				keywords.append("keyword:\"" + kw + "\"");
//			}
//		}
//		return keywords.toString();
//	}

	public SolrQuery getSolrQuery() {
		SolrQuery solrQuery = new SolrQuery();
		if (query != null) {
			solrQuery.setQuery(query);
		} else {
			solrQuery.setQuery("*:*");
		}
//		if (city != null) {
//			solrQuery.addFilterQuery("city:" + city.toString());
//		}
		if (filterQuery != null) {
			solrQuery.setFilterQueries(filterQuery);
		}

		if (null != defType) {
			solrQuery.setParam("defType", defType);
		}

//		if (null != queryType) {
//			solrQuery.setQueryType(queryType);
//		}

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
			//solrQuery.setParam(CommonParams.FL, fl);
			solrQuery.setFields(fields);
		}


		/* 关键词AND检索 */
//		if (andKeywords != null && andKeywords.size() > 0) {
//			for (String keyword : andKeywords) {
//				StringBuilder sb = new StringBuilder();
//				String kw = StringUtil.solrMetacharacter(keyword);
//				sb.append("keyword:\"" + kw + "\"");
//				solrQuery.addFilterQuery(sb.toString());
//			}
//		}
//
//		/* 关键词OR检索 */
//		if (orKeywords != null && orKeywords.size() > 0) {
//			StringBuilder sb = new StringBuilder();
//			for (String keyword : orKeywords) {
//				String kw = StringUtil.solrMetacharacter(keyword);
//				if (sb.length() > 0) {
//					sb.append(" OR ");
//				}
//				sb.append("keyword:\"" + kw + "\"");
//			}
//			solrQuery.addFilterQuery(sb.toString());
//		}
		/* ID 包括 */
		if (roomIds != null && roomIds.size() > 0) {
			StringBuilder query = new StringBuilder();
			for (Integer roomId : roomIds) {
				query.append(" " + (isNotEqRoomIds ? "-" : "") + AbstractSearcher.ID + ":" + roomId);
			}
			solrQuery.addFilterQuery(query.substring(1));
		}


		/* 经纪人ID 包括 */
		if (personIds != null && personIds.size() > 0) {
			StringBuilder query = new StringBuilder();
			for (Integer personId : personIds) {
				query.append(" " + AbstractSearcher.PERSON_ID + ":" + personId);
			}
			solrQuery.addFilterQuery(query.substring(1));
		}

		
		// 价格
		if (priceFrom != null && priceTo != null) {
			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[" + priceFrom + " TO " + priceTo + "]");
		}
		if (priceFrom != null && priceTo == null) {
			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[" + priceFrom + " TO *]");
		}
		if (priceFrom == null && priceTo != null) {
			solrQuery.addFilterQuery((isNotEqPrice ? "-" : "") + AbstractSearcher.PRICE + ":[* TO " + priceTo + "]");
		}
		// 单价
		if (unitPriceFrom != null && unitPriceTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.UNIT_PRICE + ":[" + unitPriceFrom + " TO " + unitPriceTo + "]");
		}
		if (unitPriceFrom != null && unitPriceTo == null) {
			solrQuery.addFilterQuery(AbstractSearcher.UNIT_PRICE + ":[" + unitPriceFrom + " TO *]");
		}
		if (unitPriceFrom == null && unitPriceTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.UNIT_PRICE + ":[* TO " + unitPriceTo + "]");
		}

		// 面积
		if (areaFrom != null && areaTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.AREA + ":[" + areaFrom + " TO " + areaTo + "]");
		}
		if (areaFrom != null && areaTo == null) {
			solrQuery.addFilterQuery(AbstractSearcher.AREA + ":[" + areaFrom + " TO *]");
		}
		if (areaFrom == null && areaTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.AREA + ":[* TO " + areaTo + "]");
		}

		// 楼层
		if (floorFrom != null && floorTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.FLOOR + ":[" + floorFrom + " TO " + floorTo + "]");
		}
		if (floorFrom != null && floorTo == null) {
			solrQuery.addFilterQuery(AbstractSearcher.FLOOR + ":[" + floorFrom + " TO *]");
		}
		if (floorFrom == null && floorTo != null) {
			solrQuery.addFilterQuery(AbstractSearcher.FLOOR + ":[* TO " + floorTo + "]");
		}

		// 经济人
		if (personId != null) {
			solrQuery.addFilterQuery(AbstractSearcher.PERSON_ID + ":" + personId);
		}
		// 小区
		if (gardenId != null) {
			solrQuery.addFilterQuery(AbstractSearcher.GARDEN_ID + ":" + gardenId);
		}




		// 多图
		if (manyImage != null) {
			solrQuery.addFilterQuery(AbstractSearcher.MANY_IMAGE + ":" + manyImage);
		}
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
//		if (StringUtils.isNotEmpty(createStartDate) && StringUtils.isNotEmpty(createEndDate)) {
//			solrQuery.addFilterQuery(AbstractSearcher.CREATE_TIME + ":[" + createStartDate + "T00:00:000Z TO " + createEndDate + "T00:00:000Z]");
//		} else {
//			if (StringUtils.isNotEmpty(createStartDate)) {
//				solrQuery.addFilterQuery(AbstractSearcher.CREATE_TIME + ":[" + createStartDate + "T00:00:000Z TO *");
//			}
//			if (StringUtils.isNotEmpty(createEndDate)) {
//				solrQuery.addFilterQuery(AbstractSearcher.CREATE_TIME + ":[*" + " TO " + createEndDate + "T00:00:000Z]");
//			}
//		}
//		// 装修
//		if (decoration != null) {
//			solrQuery.addFilterQuery(AbstractSearcher.DECORATION + ":" + decoration);
//		}
//		// 出租方式
//		if (rentType != null) {
//			solrQuery.addFilterQuery(AbstractSearcher.RENT_TYPE + ":" + rentType);
//		}
//		if (ranking != null) {
//			solrQuery.addFilterQuery(AbstractSearcher.RANDING + ":" + ranking);
//		}
//		if (isExcellent != null) {
//			solrQuery.addFilterQuery(AbstractSearcher.IS_EXCELLENT + ":" + isExcellent);
//		}
//		if (facetFields != null) {
//			solrQuery.setFacet(true);
//			for (SolrFacetEntity facetField : facetFields) {
//				if(facetField!=null){
//					solrQuery.addFacetQuery(facetField.createFacetQuery());
//				}
//			}
//		}
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
		
		return solrQuery;
	}
	

}