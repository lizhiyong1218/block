package org.block.search.solr;

import java.util.List;

import com.lzy.block.api.common.Pagination;

//import com.qfang.common.page.Pagination;
//import com.qfang.model.Entity;
//import com.qfang.search.page.SolrPagination;

//public interface Searcher<T extends Entity<?>> {
public interface Searcher<T> {//TODO

	public static final String ID = "id"; // ID
	public static final String TITLE = "title"; // 标题
	public static final String BIZ_TYPE = "bizType";// 商业类型
	public static final String GARDEN_NAME = "gardenName"; // 楼盘名
	public static final String GARDEN_ALIAS = "gardenAlias";// 楼盘别名 |登记名
	public static final String GARDEN_ADDRESS = "gardenAddress"; // 地址
	public static final String AREA_ID = "areaId"; // 区域ID
	public static final String AREA_NAME = "areaName"; // 区域名称
	public static final String PRICE = "price"; // 售价
	public static final String RENT_CURRENT_PRICE = "rentCurrentPrice"; // 租房均价
	public static final String UNIT_PRICE = "unitPrice"; // 单价
	public static final String AREA = "area"; // 面积
	public static final String FLOOR = "floor"; // 楼层
	public static final String TOTAL_FLOOR = "totalFloor"; // 总楼层
	public static final String CREATE_TIME = "createTime"; // 发布日期
	public static final String UPDATE_TIME = "updateTime"; // 发布日期
	public static final String COMPLETION_DATE = "completionDate"; // 竣工日期
	public static final String KEYWORD = "keyword"; // 关键词（ 包含标题，楼盘名，地址，区域名称）
	public static final String CREATEYEAR = "createYear";// 创建年代
	public static final String INDEXPICTUREURL = "indexPictureUrl";// 导图
	public static final String LAST_FOLLOW_TIME = "lastFollowTime";// 最后更新时间
	public static final String PERSON_NAME = "personName";// 经济人
	public static final String PERSON_ID = "personId";// 经济ID
	public static final String PERSON_COMPANY = "personCompany";// 所属公司
	public static final String PARENT_AREA_ID = "parentAreaId";// 大区域
	public static final String PARENT_AREA_NAME = "parentAreaName";// 大片区名称
	public static final String GARDEN_LONGITUDE = "longitude";// 经度
	public static final String GARDEN_LATITUDE = "latitude";// 纬度
	public static final String GARDEN_ID = "gardenId";// 小区ID
	public static final String GARDEN_SUBWAY_STATION = "subwayStation";// 地铁站点
	public static final String GARDEN_SUBWAY_LINE = "subwayLine";// 地铁线路
	public static final String GARDEN_SUBWAY_STATION_DESC = "subwayStationDesc";// 地铁站点描述
	public static final String GARDEN_SUBWAY_LINE_DESC = "subwayLineDesc";// 地铁线路描述
	public static final String GARDEN_BUS_STATION_DESC = "busStationDesc";// 公交站点描述
	public static final String RECOMMEND = "recommend";// 推荐
	public static final String MANY_IMAGE = "manyImage";// 多图
	public static final String CAREATE_TIME = "createTime";// 创建时间
	public static final String RANDING = "ranking";// 权重值
	public static final String REFRESH_TIME = "refreshTime";// 刷新时间
	public static final String IS_EXCELLENT = "isExcellent";// 是否推荐
	public static final String COMPLETIONDATE = "completionDate"; // 竣工日期
	public static final String PROPERTYCHARGE = "propertyCharge"; // 物业管理费
	public static final String ISCHECK = "isCheck";// 是否核查盘 1 是 其他 不是
	public static final String REFRESHVALUE = "refreshValue";// 刷新值 用于排序
	public static final String CITY = "city";// 城市
	public static final String PARENT_AREA_IDS = "parentAreaIds";// 商圈对应父区域ID列表
	public static final String PARENT_AREA_NAMES = "parentAreaNames";// 商圈对应父区域名称列表
	public static final String GARDEN_EXPERT = "gardenExpert";// 商圈对应父区域名称列表
	

	void reIndexAll();

	void save(T entity);

	void saveList(List<T> entityList);

	void update(T entity);

	void updateList(List<T> entityList);

	void deleteById(String id);

	void deleteByIds(List<String> ids);

	void deleteByQuery(final String query);

	Pagination<T> query(SearchCriteria criteria, Pagination<T> pagination);

	SolrPagination<T> queryFacet(SearchCriteria criteria, SolrPagination<T> pagination);

	void optimize(int maxSegments);

	void deleteAll();
}
