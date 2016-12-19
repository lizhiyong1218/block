/**
 * 
 */
package org.block.search.solr;

import java.awt.geom.Area;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.block.search.solr.spring.SolrTemplate;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.newhouse.NewHouse;
//import com.qfang.common.ContextHolder;
//import com.qfang.common.page.Pagination;
//import com.qfang.model.Area;
//import com.qfang.model.Garden;
//import com.qfang.model.GroupBuyProduct;
//import com.qfang.model.NewHouse;
//import com.qfang.model.NewHouse.FeatureEnum;
//import com.qfang.model.enums.CityEnum;
//import com.qfang.model.enums.DataSourceEnum;
//import com.qfang.model.enums.LayoutTypeEnum;
//import com.qfang.model.enums.SaleStatusEnum;
//import com.qfang.search.SearchCriteria;
//import com.qfang.search.Searcher;
//import com.qfang.search.solr.spring.SolrTemplate;

/**
 * 新房搜索
 * @author lizhiyong
 * 
 */
public class NewHouseSearcher extends SolrTemplate<NewHouse> implements Searcher<NewHouse>{
	
	private static Logger logger=Logger.getLogger(NewHouseSearcher.class.getName());
	
	private HashMap<String, String> zkHostS;
	private HashMap<String, String> solrNames;
	public static final String ORDER_ASC = "asc";// 升序
	public static final String ORDER_DESC = "desc";// 降序
	public static final String NEWHOUSE_ID = "newhouseId";//新房id
	public static final String SALE_STATUS = "saleStatus";//销售状态
	public static final String OPEN_DATE = "openDate";//开盘时间
	public static final String CREATE_TIME = "createTime";//开盘时间
	public static final String UPDATE_TIME = "updateTime";//开盘时间
	public static final String TEL = "tel";//咨询电话
	public static final String HOUSE_TYPE = "houseType";//主推户型
	public static final String PROPERTY_TYPE = "propertyType";//物业类型
	public static final String DECORATION  = "decoration";//装修情况
	public static final String AVGPRICE = "avgPrice";//均价
	public static final String SOURCE = "source";//来源
	public static final String AROUNDFACILITIES = "aroundFacilities";//周边配套
	public static final String NEWHOUSE_STATUS = "newhouseStatus";//新房状态
	public static final String CLICK_COUNT = "clickCount";//点击次数
	public static final String GROUPBUY_PRODUCT_ID = "groupBuyProductId";//团购id
	public static final String CUSTOMERCOUNT = "customerCount";//报名人数
	public static final String PARENT_AREA_NAME = "parentAreaName";//父区域名称
	public static final String PARENT_AREA_Id = "parentAreaId";//父区域名称
	public static final String AREA_Id = "areaId";//区域id
	public static final String AREA_NAME = "areaName";//区域名称
	public static final String GARDEN_ID = "gardenId";//小区id
	public static final String GARDEN_NAME = "gardenName";//小区名称
	public static final String GARDEN_INDEX_PICTURE_URL = "gardenIndexPictureUrl";//小区图片路径
	public static final String GARDEN_ADDRESS = "gardenAddress";//小区地址
	public static final String GARDEN_STATUS = "gardenStatus";//小区状态
	public static final String CITY = "city";//城市
	public static final String LINE_NAME = "lineName";//地铁线
	public static final String SUBWAY_STATION="subwayStation";//地铁站
	public static final String SUBWAY_DISTANCE = "subwayDistance";//站点距离
	public static final String FEATURE="features";//楼盘特色
	public static final String HOMEPICTRUEURL="homePictureUrl";//运营平台设置的新房图片
	public static final String UPDATEINDEXTIME="updateIndexTime";//索引更新时间
	public static final String SUPPORT_HOUSELOAN="supportHouseLoan";//是否支持Q时贷
	public static final String LAYOUT_TYPE="layoutType";//户型
	
	public static final String FINISHTIME="finishTime";//交房时间
	public static final String NEWHOUSETYPE="newhouseType";//新房类型
	
	public static final String FAVORABLE_TITLE="favorableTitle";//团购标题
	public static final String NEARSUBWAYINFO="nearSubwayInfo";//最近地铁站信息
	public static final String BUSS_LINES="busLines";//公交线
	public static final String BUSS_STATION_IDS="busStationIds";//公交站Id
	public static final String BUSS_STATION_NAMES="busStationNames";//公交站名称
	public static final String LONGITUDE="longitude";// 经度
	public static final String LATITUDE="latitude";// 纬度
	public static final String LOCATION="location";// 位置
	public static final String QUANGPIN="quanpin";//全拼
	public static final String JIANPIN="jianpin";//简拼
	public static final String PINYIN="pinyin";//拼音
	
	public static final String STICK_NO="stickNo";//置顶排序
	public static final String STICK_STARTDATE="stickStartDate";//置顶开始时间
	public static final String STICK_ENDDATE="stickEndDate";//置顶结束时间
	
	public static Pattern hzpattern = Pattern.compile("[\u4e00-\u9fa5]");
	
	private HashMap<String, String> solrUrls;
	
	@Override
	public void reIndexAll() {
		
	}

//	@Override
//	public Pagination<NewHouse> query(SearchCriteria criteria,
//			Pagination<NewHouse> pagination) {
//		if (criteria.isGroup()) {
//			return queryGroup(criteria, pagination);
//		}
//		return query(criteria.getSolrQuery(), pagination);
//	}

	@Override
	public NewHouse solrDocumentToEntity(SolrDocument solrDocument)
			throws Exception {
		if (solrDocument == null) {
			return null;
		}
		NewHouse newHouse=new NewHouse();
//		newHouse.setId((Integer)solrDocument.get(NEWHOUSE_ID));//新房id
//		newHouse.setHouseType((String)solrDocument.get(HOUSE_TYPE));
//		newHouse.setSaleStatus(SaleStatusEnum.valueOf((String)solrDocument.get(SALE_STATUS)));
//		newHouse.setAroundFacilities((String)solrDocument.get(AROUNDFACILITIES));
//		newHouse.setOpenDate((Date) solrDocument.get(OPEN_DATE));
//		newHouse.setCreateTime((Date) solrDocument.get(CREATE_TIME));
//		newHouse.setUpdateTime((Date) solrDocument.get(UPDATE_TIME));
//		newHouse.setClickCount((Integer)solrDocument.get(CLICK_COUNT));
//		newHouse.setTel((String)solrDocument.get(TEL));
//		newHouse.setAvgPrice((Double)solrDocument.get(AVGPRICE));
//		newHouse.setHomePictureUrl((String)solrDocument.get(HOMEPICTRUEURL));
//		if(solrDocument.get(SUPPORT_HOUSELOAN)!=null){
//			newHouse.setSupportHouseLoan((Boolean)solrDocument.get(SUPPORT_HOUSELOAN));
//		}
//		if(solrDocument.get(FEATURE)!=null){//楼盘特色
//			@SuppressWarnings("unchecked")
//			List<String> features = (List<String>) solrDocument.get(FEATURE);;
//			newHouse.setFeatures(features);
//			if(features!=null&&features.size()>0){
//				List<FeatureEnum> featureEnums=new ArrayList<NewHouse.FeatureEnum>();
//				FeatureEnum featureEnum=null;
//				for (String featrue : features) {
//					try {
//						featureEnum = FeatureEnum.valueOf(featrue);
//						featureEnums.add(featureEnum);
//					} catch (Exception e) {
//						 
//					}
//				}
//				newHouse.setFeatureEnus(featureEnums);
//			}
//		}
//		newHouse.setDecoration((String)solrDocument.get(DECORATION));
//		
//		if(solrDocument.get(LAYOUT_TYPE)!=null){
//			newHouse.setLayoutTypes((List<String>)solrDocument.get(LAYOUT_TYPE));		
//		}
//		
//		newHouse.setFinishTime((Date)solrDocument.get(FINISHTIME));
//		newHouse.setNewhouseType((String)solrDocument.get(NEWHOUSETYPE));
//		
//		Area parentArea=new Area();
//		parentArea.setName((String)solrDocument.get(PARENT_AREA_NAME));
//		Area area=new Area();
//		area.setName((String)solrDocument.get(AREA_NAME));
//		area.setParent(parentArea);
//		Garden garden = new Garden();
//		garden.setId((Integer)solrDocument.get(GARDEN_ID));
//		garden.setName((String)solrDocument.get(GARDEN_NAME));
//		garden.setAlias((String)solrDocument.get(GARDEN_NAME));//别名
//		garden.setIndexPictureUrl((String)solrDocument.get(GARDEN_INDEX_PICTURE_URL));
//		garden.setAddress((String)solrDocument.get(GARDEN_ADDRESS));
//		garden.setArea(area);
//		garden.setSubwayLine((String)solrDocument.get(NEARSUBWAYINFO));
//		//经度，纬度
//		Double lat= (Double)solrDocument.get(LATITUDE);
//		if(lat!=null){
//			garden.setLatitude(new BigDecimal(lat));
//		}
//		Double lng= (Double)solrDocument.get(LONGITUDE);
//		if(lng!=null){
//			garden.setLongitude(new BigDecimal(lng));
//		}
//		
//		String subwayDistance = (String) solrDocument.get(SUBWAY_DISTANCE);
//		Map<String, String> subwayMap = new HashMap<String, String>();
//		if (subwayDistance != null && !"".equals(subwayDistance)) {
//			String[] subwayDistances = subwayDistance.split(",");
//			for (String string : subwayDistances) {
//				String[] value = string.split(";");
//				if(value.length>2){
//					try {
//					subwayMap.put(value[1], value[2].replace("米", ""));
//						newHouse.setSubwayDistance(Integer.parseInt(value[2].replace("米", "")));
//					} catch (Exception e) {
//						 
//					}
//				}
//			}
//		}
//		newHouse.setSubwayDistanceMap(subwayMap);
//		newHouse.setGarden(garden);
//		GroupBuyProduct groupBuyProduct=new GroupBuyProduct();
//		groupBuyProduct.setId((Integer)solrDocument.get(GROUPBUY_PRODUCT_ID));
//		groupBuyProduct.setFavorableTitle((String)solrDocument.get(FAVORABLE_TITLE));
//		if(solrDocument.get(CUSTOMERCOUNT)!=null){
//			groupBuyProduct.setCustomerCount((Integer)solrDocument.get(CUSTOMERCOUNT));
//		}
//		if(groupBuyProduct.getId()!=null){
//			newHouse.setGroupBuyProduct(groupBuyProduct);
//		}
		return newHouse;
	}

	@Override
	public SolrInputDocument entityToSolrInputDocument(NewHouse newHouse)
			throws Exception {
		if (newHouse == null) {
			return null;
		}
//		Garden garden=newHouse.getGarden();
//		if(garden==null){
//			return null;
//		}
		SolrInputDocument solrInputDocument = new SolrInputDocument();
//		solrInputDocument.setField(ID, garden.getCity().toString()+"-"+ newHouse.getId());
//		solrInputDocument.setField(NEWHOUSE_ID, newHouse.getId());
//		solrInputDocument.setField(SALE_STATUS, newHouse.getSaleStatus()==null?null:newHouse.getSaleStatus().toString());
//		solrInputDocument.setField(OPEN_DATE, newHouse.getOpenDate());
//		solrInputDocument.setField(CREATE_TIME  , newHouse.getCreateTime());
//		solrInputDocument.setField(UPDATE_TIME  , newHouse.getUpdateTime());
//		solrInputDocument.setField(TEL, newHouse.getTel());
//		solrInputDocument.setField(HOUSE_TYPE, newHouse.getHouseType());
//		solrInputDocument.setField(AVGPRICE, newHouse.getAvgPrice());
//		solrInputDocument.setField(AROUNDFACILITIES, newHouse.getAroundFacilities());
//		solrInputDocument.setField(NEWHOUSE_STATUS,newHouse.getStatus()==null?null:newHouse.getStatus().toString()); 
//		solrInputDocument.setField(CLICK_COUNT, newHouse.getClickCount()==null?0:newHouse.getClickCount());
//		solrInputDocument.setField(SOURCE, newHouse.getSource()==null?null:newHouse.getSource().toString());
//		solrInputDocument.setField(HOMEPICTRUEURL,newHouse.getHomePictureUrl());
//		solrInputDocument.setField(UPDATEINDEXTIME,new Date());
//		solrInputDocument.setField(SUPPORT_HOUSELOAN,newHouse.isSupportHouseLoan());
//		if(newHouse.getStickNo()!=null){
//			solrInputDocument.setField(STICK_NO, newHouse.getStickNo());
//		}else{
//			solrInputDocument.setField(STICK_NO, 10000);
//		}
//		solrInputDocument.setField(STICK_STARTDATE, newHouse.getStickStartDate());
//		solrInputDocument.setField(STICK_ENDDATE, newHouse.getStickEndDate());
//		
//		//楼盘特色
//		String features = newHouse.getUniqueFeature();
//		FeatureEnum enumByDesc=null;
//		if(StringUtils.isNotEmpty(features)){
//			String[] arr = features.split(",");
//			for (String feature : arr) {
//				if(StringUtils.isNotEmpty(feature)){
//					if(hzpattern.matcher(feature).find()){//如果包含中文,则将中文转为英文
//						enumByDesc = FeatureEnum.getEnumByDesc(feature);
//						if(enumByDesc!=null){
//							feature=enumByDesc.toString();
//						}
//					}
//				}
//				solrInputDocument.addField(FEATURE, feature);
//			}
//		}
//		
//		solrInputDocument.setField(DECORATION, newHouse.getDecoration());
//		
//		solrInputDocument.setField(FINISHTIME, newHouse.getFinishTime());
//		solrInputDocument.setField(NEWHOUSETYPE, newHouse.getNewhouseType());
//				
//		//户型
//		String layoutStr = newHouse.getLayoutStr();
//		if(StringUtils.isNotBlank(layoutStr)){
//			String[] split = layoutStr.split(",");
//			LayoutTypeEnum layoutType=null;
//			for (String str : split) {
//				layoutType = LayoutTypeEnum.getLayoutTypeByKey(str);
//				if(layoutType!=null){
//					solrInputDocument.addField(LAYOUT_TYPE, layoutType.getKey());
//				}
//			}
//		}
//		
//		if(newHouse.getGroupBuyProduct()!=null){
//			solrInputDocument.setField(GROUPBUY_PRODUCT_ID, newHouse.getGroupBuyProduct().getId());
//			solrInputDocument.setField(CUSTOMERCOUNT,newHouse.getGroupBuyProduct().getCustomerCount());
//			solrInputDocument.setField(FAVORABLE_TITLE, newHouse.getGroupBuyProduct().getFavorableTitle());
//		}
//		 
//		solrInputDocument.setField(GARDEN_ID,garden.getId() );
//		solrInputDocument.setField(GARDEN_NAME , garden.getName());
//		if(garden.getCity()==CityEnum.SHENZHEN){
//			String alias= garden.getAlias();//别名
//			if(StringUtils.isNotBlank(alias)){
//				solrInputDocument.setField(GARDEN_NAME , alias);
//			}
//		}
//		
//		solrInputDocument.setField(GARDEN_INDEX_PICTURE_URL ,garden.getIndexPictureUrl() );
//		solrInputDocument.setField(GARDEN_ADDRESS,garden.getAddress()); 
//		solrInputDocument.setField(GARDEN_STATUS,garden.getStatus()==null?null:garden.getStatus().toString()); 
//		solrInputDocument.setField(CITY,garden.getCity()==null?null:garden.getCity().toString());
//		solrInputDocument.setField(PROPERTY_TYPE, garden.getPropertyType().toString()); 
//		solrInputDocument.setField(DECORATION, newHouse.getDecoration());
//		Area area = garden.getArea();
//		if(area!=null){
//			solrInputDocument.setField(AREA_Id, area.getId());
//			solrInputDocument.setField(AREA_NAME,area.getName());
//			Area parent=area.getParent();
//			if(parent!=null){
//				solrInputDocument.setField(PARENT_AREA_Id, parent.getId());
//				solrInputDocument.setField(PARENT_AREA_NAME,parent.getName() );
//			}else{
//				logger.debug("parentArea为空!newHouse:"+newHouse.getId()+",area:"+area.getId());
//			}
//		}else{
//			logger.debug("area为空!"+newHouse.getId());
//		}
//		
//		//地铁线
//		String subwaylines= garden.getSubwayLine();
//		if(StringUtils.isNotBlank(subwaylines)){
//			String[] arr = subwaylines.split(",");
//			for (String string : arr) {
//				solrInputDocument.addField(LINE_NAME, string);
//			}
//		}
//		//线路名称:站点名称:距离,多个站点以，号分割，第一个为离小区最近站点	2号线;川沙;1036米 
//		String distanceString = garden.getSubwayStationDistance();
//		solrInputDocument.setField(SUBWAY_DISTANCE, distanceString);
//		//地铁站
//		String subwayStations= garden.getSubwayStation();
//		if(StringUtils.isNotBlank(subwayStations)){
//			String[] arr = subwayStations.split(",");
//			for (String string : arr) {
//				solrInputDocument.addField(SUBWAY_STATION, string);
//			}
//		}
//		if(StringUtils.isNotBlank(garden.getSubwayStationDistance())){//4号线(龙华线);福田口岸;675,4号线(龙华线);福民;927,1号线(罗宝线);
//			String[] arr= garden.getSubwayStationDistance().split(",");
//			Integer min=null;
//			String[] subwayinfo;
//			Integer curdistance;
//			String minInfo=null;
//			for (String infos : arr) {
//				subwayinfo = infos.split(";");
//				if(subwayinfo.length==3){
//					try {
//						curdistance=Integer.parseInt(subwayinfo[2]);
//						if(min==null){
//							min=curdistance;
//							minInfo=infos;
//						}else{
//							if(curdistance<min){
//								min=curdistance;
//								minInfo=infos;
//							}
//						}
//					} catch (Exception e) {
//						logger.error(e);
//					}
//				}
//			}
//			if(minInfo!=null){
//				solrInputDocument.setField(NEARSUBWAYINFO, minInfo);
//			}
//		}		
//		//公交路线id
//		String busStations = garden.getBusStation();
//		if(StringUtils.isNotEmpty(busStations)){
//			String[] arr = busStations.split(",");
//			for (String string : arr) {
//				solrInputDocument.addField(BUSS_STATION_IDS, string);
//			}
//		}
//		
//		//经度，纬度
//		if(garden.getLongitude()!=null){
//			solrInputDocument.setField(LONGITUDE,garden.getLongitude().doubleValue());
//		}
//		if(garden.getLatitude()!=null){
//			solrInputDocument.setField(LATITUDE, garden.getLatitude().doubleValue());
//		}
//		
//		// 判断经纬度
//		if (garden.getLongitude() != null && garden.getLatitude()!=null) {
//			double longitude = garden.getLongitude().doubleValue();
//			double latitude = garden.getLatitude().doubleValue();
//			if (longitude > 0 && longitude < 180 && latitude > 0 && latitude < 90) {
//				String location=latitude+","+longitude;
//				solrInputDocument.setField(LOCATION, location);
//			}
//		}
//		
//		//全拼，简拼
//		solrInputDocument.setField(QUANGPIN, garden.getFullPinyin());
//		solrInputDocument.setField(JIANPIN, garden.getSimplePinyin());
		return solrInputDocument;
	}
	
	
	@Override
	public String getSolrServerUrl() {
		if (solrUrls != null && solrUrls.get(getDataSource().toString()) != null) {
			return solrUrls.get(getDataSource().toString());
		}
		return super.getSolrServerUrl();
	}

	public void setSolrUrls(HashMap<String, String> solrUrls) {
		this.solrUrls = solrUrls;
	}

	public static DataSourceEnum getDataSource() {
//		CityEnum city = null;
//		String dataSource = ContextHolder.getDataSource();
//		city = dataSource != null ? CityEnum.valueOf(dataSource.toUpperCase())
//				: CityEnum.SHENZHEN;
//		if (DataSourceEnum.SHENZHEN.equals(city.getDataSource()) || DataSourceEnum.ZH.equals(city.getDataSource())) {
//			return DataSourceEnum.SHENZHEN;
//		}else if(DataSourceEnum.BEIJING.equals(city.getDataSource()) || DataSourceEnum.ZHENGZHOU.equals(city.getDataSource())){
//			return DataSourceEnum.BEIJING;
//		}else{
//			return DataSourceEnum.SHANGHAI;
//		}
		return null;
	}

//	public static CityEnum getCity() {
//		String dataSource = ContextHolder.getDataSource();
//		if (dataSource == null)
//			return CityEnum.SHENZHEN;
//		return CityEnum.valueOf(dataSource.toUpperCase());
//	}

	public void setZkHostS(HashMap<String, String> zkHostS) {
		this.zkHostS = zkHostS;
	}

	public void setSolrNames(HashMap<String, String> solrNames) {
		this.solrNames = solrNames;
	}

	@Override
	public String getSolrName() {
		if (solrNames != null && solrNames.get(getDataSource().toString()) != null) {
			return solrNames.get(getDataSource().toString());
		}
		return super.getSolrName();
	}

	@Override
	public String getZkHost() {
		if (zkHostS != null && zkHostS.get(getDataSource().toString()) != null) {
			return zkHostS.get(getDataSource().toString());
		}
		return super.getZkHost();
	}

	@Override
	public void save(NewHouse entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveList(List<NewHouse> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(NewHouse entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateList(List<NewHouse> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(List<String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByQuery(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination<NewHouse> query(SearchCriteria criteria,
			Pagination<NewHouse> pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolrPagination<NewHouse> queryFacet(SearchCriteria criteria,
			SolrPagination<NewHouse> pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void optimize(int maxSegments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
