package com.lzy.block.api.model.newhouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;




import com.lzy.block.api.constant.newhouse.CityEnum;

/**
 * 新盘
 */
public class NewHouse implements Serializable{
	private static final long serialVersionUID = 5910811382419338313L;
	
	private Integer id;
	private Integer gardenId;
	private String saleStatus;
	private String source;
	private String houseType; // 户型
	private String decoration; // 装修情况
	private String uniqueFeature; // 楼盘特色
	private String lightspot; // 楼盘亮点
	private String aroundFacilities; // 周边配套
	private String trafficFacilities; // 交通配套
	private BigDecimal chargeRanking; // 收费权重
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	private Integer clickCount;//点击数
	private Integer layoutPicClickCount;//点击数
	private Integer gardenPicClickCount;//点击数
	private Integer modelPicClickCount;
	private BigDecimal ranking;//排序权重
    private String posterSort;  //广告类型
    private String dynamicCount; //楼盘动态
    private int layoutPictureCount;//户型图片数
    private int gardenPictureCount;//项目图片数
    private int trendsCount;       //新房动态数
//	private Garden garden; // 小区
//  private GroupBuyProduct groupBuyProduct;
//  private List<NewHouseNews> newHouseNews;
//	private NewHouseSourceEnum source; // 来源为联动盘或者抓取盘
//	private SaleStatusEnum saleStatus; // 销售状态（枚举）
    
    /*运营后台设置的新房封面图,如果不存在则为空--lizhiyong*/
    private String homePictureUrl;
    private boolean supportHouseLoan; // 是否支持Q时贷
    private String layoutStr;//户型
    private List<String> layoutTypes;//户型集合
    //以前用小区的数据，现在全部用新房的数据  开始
    private double avgPrice; 		//平均房价
    private String propertyCompany; //物业管理公司
    private String developer; 		//开发商
    private Date openDate; 			//开盘时间
    private String tel;				//服务电话
    private int subwayDistance;			//距离，单位m，用于展示楼盘与地铁之间的距离
    private Date finishTime;
    private String newhouseType;//新房类型:NORMAL,HONOURED
    private BigDecimal minTotalPrice;
    private BigDecimal maxTotalPrice;
    
    private List<String> features;
    //以前用小区的数据，现在全部用新房的数据  结束
    
    private BigDecimal minPropertyCharge; // 物业管理费（最小）
    private BigDecimal maxPropertyCharge; // 最大物业管理费
    
    /*索引默认排序时需要字段--lizhiyong*/
    private Integer stickNo;//置顶排序,生成索引时有效
    private Date stickStartDate;//置顶开始时间
    private Date stickEndDate;//置顶结束时间
    
    private Map<String, String> subwayDistanceMap;// 地铁站到达小区的距离
    private List<FeatureEnum> featureEnus;//新房列表页显示楼盘特色枚举中文
    
    
    /**
	 * 
	 * 楼盘特色枚举值
	 */
	public static enum FeatureEnum {
		
		DISCOUNT(null,"打折优惠","F1","1"),
		SMALL_LAYOUT(null,"小户型","F2","2"),
		LOW_PRICE(null,"低总价","F3","3"),
		FINISHED(null,"现房","F4","4"),
		SCHOOL_DISTRICT(null,"学区房","F5","5"),
		ALONG_LINE(null,"地铁沿线","F6","6"),
		FOREIGN_STYLE(null,"花园洋房","F7","7"),
		UNLIMITED_PURCHASE(null,"不限购","F8","8"),
		INVESTMENT(null,"投资地产","F9","9"),
		PARK(null,"公园地产","F10","10"),
		LIVABLE(null,"生态宜居","F11","11"),
		WATERSCAPE(null,"水景地产","F12","12"),
		MOUNTAIN_VIEW_DEF(null,"山景地产","F13","13"),
		LANDSCAPE(null,"景观居所","F14","14"),
		SCIENCE(null,"科技住宅","F15","15"),
		TRIP(null,"旅游地产","F16","16"),
		APARTMENT(null,"酒店式公寓","F17","17"),
		FIRST_SELL(null,"新盘首开","F18","18"),
		ANNUAL_HOT(null,"年度热盘","F19","19"),
		COTTAGES(null, "别墅	",	"F20",	"20");
		
		private CityEnum city; // null表示default
		
		private String desc;
		
		private String value;
		
		private String id;
		
		private  static Map<CityEnum, List<FeatureEnum>> cityGroups 
			= new HashMap<CityEnum, List<FeatureEnum>>();
		
		static {
			for (FeatureEnum feature : FeatureEnum.values()) {
				CityEnum city = feature.getCity();
				List<FeatureEnum> features = cityGroups.get(city);
				if (features == null) {
					features = new LinkedList<FeatureEnum>();
					cityGroups.put(city, features);
				}
				features.add(feature);
			}
		}
		
		public static List<FeatureEnum> getFeatures(CityEnum city) {
			List<FeatureEnum> features = cityGroups.get(city);
			return features == null ? cityGroups.get(null) : features;
		}
		
		private FeatureEnum(CityEnum city, String desc,String value, String id) {
			this.city = city;
			this.desc = desc;
			this.value=value;
			this.id = id;
		}
		
		public static FeatureEnum getEnumById(String id) {
			FeatureEnum[] enums = FeatureEnum.values();
			for(FeatureEnum e : enums) {
				if(e.getId().equals(id)) {
					return e;
				}
			}
			return null;
		}
		
		public static FeatureEnum getFeatureByNameOrDesc(String text) {
			if (text==null||text.equals("")) {
				return null;
			}
			List<FeatureEnum> features = cityGroups.get(null);
			if (text.matches("\\w+")) {
				text = text.toUpperCase();
				for (FeatureEnum fe : features) {
					if (fe.name().equals(text)) {
						return fe;
					}
				}
			} else {
				for (FeatureEnum fe : features) {
					if (fe.getDesc().equals(text)) {
						return fe;
					}
				}
			}
			return null;
		}
		
		/**
		 * 根据描述获取特色
		 * lizhiyong
		 * @param desc
		 * @return
		 */
		public static FeatureEnum getEnumByDesc(String desc){
			if(desc!=null){
				for(FeatureEnum e : FeatureEnum.values()){
					if(e.getDesc().equals(desc.trim())){
						return e;
					}
				}
			}
			return null;
		}
		
		
		public CityEnum getCity() {
			return city;
		}

		public void setCity(CityEnum city) {
			this.city = city;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
	}
    
    
    public BigDecimal getMinPropertyCharge() {
		return minPropertyCharge;
	}

	public void setMinPropertyCharge(BigDecimal minPropertyCharge) {
		this.minPropertyCharge = minPropertyCharge;
	}

	public BigDecimal getMaxPropertyCharge() {
		return maxPropertyCharge;
	}

	public void setMaxPropertyCharge(BigDecimal maxPropertyCharge) {
		this.maxPropertyCharge = maxPropertyCharge;
	}
	
    public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getUniqueFeature() {
		return uniqueFeature;
	}
	public void setUniqueFeature(String uniqueFeature) {
		this.uniqueFeature = uniqueFeature;
	}
	public String getLightspot() {
		return lightspot;
	}
	public void setLightspot(String lightspot) {
		this.lightspot = lightspot;
	}
	public String getAroundFacilities() {
		return aroundFacilities;
	}
	public void setAroundFacilities(String aroundFacilities) {
		this.aroundFacilities = aroundFacilities;
	}
	public String getTrafficFacilities() {
		return trafficFacilities;
	}
	public void setTrafficFacilities(String trafficFacilities) {
		this.trafficFacilities = trafficFacilities;
	}
	 
	public BigDecimal getChargeRanking() {
		return chargeRanking;
	}

	public void setChargeRanking(BigDecimal chargeRanking) {
		this.chargeRanking = chargeRanking;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getClickCount() {
		return clickCount;
	}
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	
	
	public Integer getLayoutPicClickCount() {
		return layoutPicClickCount;
	}
	public void setLayoutPicClickCount(Integer layoutPicClickCount) {
		this.layoutPicClickCount = layoutPicClickCount;
	}
	public Integer getGardenPicClickCount() {
		return gardenPicClickCount;
	}
	public void setGardenPicClickCount(Integer gardenPicClickCount) {
		this.gardenPicClickCount = gardenPicClickCount;
	}
	public BigDecimal getRanking() {
		return ranking;
	}
	public void setRanking(BigDecimal ranking) {
		this.ranking = ranking;
	}

    public String getPosterSort() {
		return posterSort;
	}

	public void setPosterSort(String posterSort) {
		this.posterSort = posterSort;
	}
	public String getDynamicCount() {
		return dynamicCount;
	}
	public void setDynamicCount(String dynamicCount) {
		this.dynamicCount = dynamicCount;
	}
	public int getLayoutPictureCount() {
		return layoutPictureCount;
	}
	public void setLayoutPictureCount(int layoutPictureCount) {
		this.layoutPictureCount = layoutPictureCount;
	}
	public int getGardenPictureCount() {
		return gardenPictureCount;
	}
	public void setGardenPictureCount(int gardenPictureCount) {
		this.gardenPictureCount = gardenPictureCount;
	}
	public int getTrendsCount() {
		return trendsCount;
	}
	public void setTrendsCount(int trendsCount) {
		this.trendsCount = trendsCount;
	}
	public Integer getModelPicClickCount() {
		return modelPicClickCount;
	}
	public void setModelPicClickCount(Integer modelPicClickCount) {
		this.modelPicClickCount = modelPicClickCount;
	}

	public int getSubwayDistance() {
		return subwayDistance;
	}

	public void setSubwayDistance(int subwayDistance) {
		this.subwayDistance = subwayDistance;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public Integer getStickNo() {
		return stickNo;
	}

	public void setStickNo(Integer stickNo) {
		this.stickNo = stickNo;
	}

	public Date getStickStartDate() {
		return stickStartDate;
	}

	public void setStickStartDate(Date stickStartDate) {
		this.stickStartDate = stickStartDate;
	}

	public Date getStickEndDate() {
		return stickEndDate;
	}

	public void setStickEndDate(Date stickEndDate) {
		this.stickEndDate = stickEndDate;
	}

	public String getHomePictureUrl() {
		return homePictureUrl;
	}

	public void setHomePictureUrl(String homePictureUrl) {
		this.homePictureUrl = homePictureUrl;
	}

	public Map<String, String> getSubwayDistanceMap() {
		return subwayDistanceMap;
	}

	public void setSubwayDistanceMap(Map<String, String> subwayDistanceMap) {
		this.subwayDistanceMap = subwayDistanceMap;
	}

	public List<FeatureEnum> getFeatureEnus() {
		return featureEnus;
	}

	public void setFeatureEnus(List<FeatureEnum> featureEnus) {
		this.featureEnus = featureEnus;
	}

	public boolean isSupportHouseLoan() {
		return supportHouseLoan;
	}

	public void setSupportHouseLoan(boolean supportHouseLoan) {
		this.supportHouseLoan = supportHouseLoan;
	}

	public String getLayoutStr() {
		return layoutStr;
	}

	public void setLayoutStr(String layoutStr) {
		this.layoutStr = layoutStr;
	}

	public List<String> getLayoutTypes() {
		return layoutTypes;
	}

	public void setLayoutTypes(List<String> layoutTypes) {
		this.layoutTypes = layoutTypes;
	}

	public String getNewhouseType() {
		return newhouseType;
	}

	public void setNewhouseType(String newhouseType) {
		this.newhouseType = newhouseType;
	}

	public BigDecimal getMinTotalPrice() {
		return minTotalPrice;
	}

	public void setMinTotalPrice(BigDecimal minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}

	public BigDecimal getMaxTotalPrice() {
		return maxTotalPrice;
	}

	public void setMaxTotalPrice(BigDecimal maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGardenId() {
		return gardenId;
	}

	public void setGardenId(Integer gardenId) {
		this.gardenId = gardenId;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
