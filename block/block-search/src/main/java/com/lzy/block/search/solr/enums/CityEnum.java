package com.lzy.block.search.solr.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


/**
 * 城市列表
 * 
 * @author Hou Peiqin
 * 
 */
public enum CityEnum {	
	
	SHENZHEN("深圳", "SHENZHEN", "深圳", "SHENZHEN",false, "SZ", "shenzhen.qfang.com", DataSourceEnum.SHENZHEN, "114.066003", "22.549625","0755"), 
	ZH("珠海", "ZH", "珠海", "ZHUHAI",false, "ZH", "zh.qfang.com", DataSourceEnum.ZH, "113.58318", "22.276515","0756"), 
	DONGGUAN("东莞", "DONGGUAN", "东莞", "DG",false, "DG", "dongguan.qfang.com", DataSourceEnum.SHENZHEN, "113.758188", "23.02706","0769"), 
	HUIZHOU("惠州", "HUIZHOU", "惠州", "HUIZHOU",false, "HZ", "huizhou.qfang.com", DataSourceEnum.SHENZHEN, "114.423342", "23.116419","0752"), 
	ZHONGSHAN("中山", "ZHONGSHAN", "中山", "ZHONGSHAN",false, "ZS", "zhongshan.qfang.com", DataSourceEnum.ZH, "113.399171", "22.523213","0760"), 
	FOSHAN("佛山", "FOSHAN","佛山", "FOSHAN",false, "FS", "foshan.qfang.com", DataSourceEnum.ZH, "113.128425", "23.027707","0757"),
	BEIJING("北京","BEIJING", "北京","BEIJING", false, "BJ", "beijing.qfang.com", DataSourceEnum.BEIJING, "116.403874", "39.91518","010"),
	SHANGHAI("上海", "SHANGHAI", "上海", "SHANGHAI",false, "SH", "shanghai.qfang.com", DataSourceEnum.SHANGHAI, "121.480263", "31.236295","021"),
	GUANGZHOU("广州", "GUANGZHOU","广州", "GUANGZHOU",false, "GZ", "guangzhou.qfang.com", DataSourceEnum.GUANGZHOU, "113.270802", "23.135312","020"),
	HANGZHOU("杭州", "HANGZHOU","杭州", "HANGZHOU",false, "HZ", "hangzhou.qfang.com", DataSourceEnum.HANGZHOU, "120.161668", "30.279581","0571"),
	CHENGDU("成都", "CHENGDU","成都", "CHENGDU",false, "CD", "chengdu.qfang.com", DataSourceEnum.CHENGDU, "104.073097", "30.663791","028"),
	CHONGQING("重庆", "CHONGQING","重庆", "CHONGQING",false, "CQ", "chongqing.qfang.com", DataSourceEnum.CHONGQING, "106.558105", "29.569096","023"),
	WUHAN("武汉", "WUHAN","武汉", "WUHAN",false, "WH", "wuhan.qfang.com", DataSourceEnum.WUHAN, "114.311834", "30.598656","027"),
	SUZHOU("苏州", "SUZHOU","苏州", "SUZHOU",false, "SZ", "suzhou.qfang.com", DataSourceEnum.SUZHOU, "120.591557", "31.306664","0512"),
	NANJING("南京", "NANJING","南京", "NANJING",false, "NJ", "nanjing.qfang.com", DataSourceEnum.NANJING, "118.802988", "32.064978","025"),
	WUXI("无锡","WUXI", "无锡","WUXI", false,"WX", "wuxi.qfang.com", DataSourceEnum.SHANGHAI, "120.318917", "31.497013","0510"),
	ZIBO("淄博", "ZIBO", "淄博", "ZIBO", false,  "ZB", "zibo.qfang.com", DataSourceEnum.ZHENGZHOU, "118.061253", "36.819179", ""),
	GANZHOU("赣州","GANZHOU", "赣州","GANZHOU", false, "GZ","ganzhou.qfang.com", DataSourceEnum.GUANGZHOU, "102.546657", "24.347241", ""),
	CHUXIONG("楚雄", "CHUXIONG", "楚雄", "CHUXIONG", false,  "CX", "chuxiong.qfang.com", DataSourceEnum.CHENGDU, "101.534326", "25.052103", ""),
	BAOSHAN("保山","BAOSHAN", "保山","BAOSHAN", false, "BS","baoshan.qfang.com",DataSourceEnum.CHENGDU, "102.546657", "24.347241", ""),
	PINGDINGSHAN("平顶山","PINGDINGSHAN", "平顶山","PINGDINGSHAN", false, "PDS","pingdingshan.qfang.com", DataSourceEnum.ZHENGZHOU, "102.546657", "24.347241", ""),
	HEFEI("合肥","HEFEI", "合肥","HEFEI", false,"HF", "hefei.qfang.com", DataSourceEnum.ZHENGZHOU, "117.294772", "31.861054", ""),
	KUNMING("昆明","KUNMING", "昆明","KUNMING", false,"KM", "kunming.qfang.com", DataSourceEnum.CHENGDU, "102.712301", "25.04601", ""),
	TAICANG("太仓","TAICANG", "太仓","TAICANG", false,"TC", "taicang.qfang.com", DataSourceEnum.SHANGHAI, "121.136594", "31.465097", ""),
	QINGDAO("青岛","QINGDAO", "青岛","QINGDAO", false, "QD", "qingdao.qfang.com", DataSourceEnum.ZHENGZHOU, "120.388686", "36.074226", ""),
	SANYA("三亚","SANYA", "三亚","SANYA", false,"SY", "sanya.qfang.com", DataSourceEnum.GUANGZHOU, "109.519896", "18.260523", ""),
	TAIYUAN("太原","TAIYUAN", "太原","TAIYUAN", false,"TY", "taiyuan.qfang.com", DataSourceEnum.ZHENGZHOU, "112.557763", "37.879797", ""),
	CHANGSHA("长沙","CHANGSHA", "长沙","CHANGSHA", false,"CS", "changsha.qfang.com", DataSourceEnum.WUHAN, "113.018447", "28.229774", ""),
	NANNING("南宁", "NANNING", "南宁", "NANNING",false,  "NN","nanning.qfang.com",  DataSourceEnum.GUANGZHOU, "108.337191", "22.827813", ""),
	JIAXING("嘉兴","JIAXING", "嘉兴","JIAXING", false, "JX","jiaxing.qfang.com", DataSourceEnum.HANGZHOU, "120.762045", "30.752464", ""),
	HONGHE("红河","HONGHE", "红河","HONGHE", false, "HH","honghe.qfang.com",DataSourceEnum.CHENGDU, "102.546657", "24.347241", ""),
	LANGFANG("廊坊", "LANGFANG", "廊坊", "LANGFANG", false,  "LF", "langfang.qfang.com", DataSourceEnum.BEIJING, "116.690152", "39.543952", ""),
	HUZHOU("湖州","HUZHOU", "湖州","HUZHOU", false, "HZ","huzhou.qfang.com", DataSourceEnum.HANGZHOU, "120.092266", "30.902981", ""),
	
	
	
	
	
	
	
	
	ZHENGZHOU("郑州", "ZHENGZHOU","郑州", "ZHENGZHOU",true, "ZZ", "zhengzhou.qfang.com", DataSourceEnum.ZHENGZHOU, "113.63175", "34.753451","0371"),
	DALIAN("大连","DALIAN", "大连","DALIAN", true,"DL", "dalian.qfang.com", DataSourceEnum.BEIJING, "121.621074", "38.920869", ""),
	FUZHOU("福州","FUZHOU", "福州","FUZHOU", true,"FZ", "fuzhou.qfang.com", DataSourceEnum.HANGZHOU, "119.310099", "26.07984", ""),
	HAERBIN("哈尔滨","HAERBIN", "哈尔滨","HAERBIN", true,"HEB", "haerbin.qfang.com", DataSourceEnum.BEIJING, "126.641619", "45.77081", ""),
	HAIKOU("海口","HAIKOU", "海口","HAIKOU", true,"HK", "haikou.qfang.com", DataSourceEnum.GUANGZHOU, "110.339138", "20.026962", ""),
	JINAN("济南","JINAN", "济南","JINAN", true,"JN", "jinan.qfang.com", DataSourceEnum.ZHENGZHOU, "117.00427", "36.678617", ""),
	KUNSHAN("昆山","KUNSHAN", "昆山","KUNSHAN", true,"KS", "kunshan.qfang.com", DataSourceEnum.SHANGHAI, "120.98593", "31.391101", ""),
	NANCHANG("南昌","NANCHANG", "南昌","NANCHANG", true,"NC", "nanchang.qfang.com", DataSourceEnum.WUHAN, "115.860183", "28.689071", ""),
	NANTONG("南通","NANTONG", "南通","NANTONG", true,"NT", "nantong.qfang.com", DataSourceEnum.HANGZHOU, "120.894498", "32.010746", ""),
	NINGBO("宁波","NINGBO", "宁波","NINGBO", true,"NB", "ningbo.qfang.com", DataSourceEnum.HANGZHOU, "121.565208", "29.886261", ""),
	XIAMEN("厦门","XIAMEN", "厦门","XIAMEN", true,"XM", "xiamen.qfang.com", DataSourceEnum.HANGZHOU, "118.100436", "24.492388", ""),
	SHENYANG("沈阳","SHENYANG", "沈阳","SHENYANG", true,"SY", "shenyang.qfang.com", DataSourceEnum.BEIJING, "123.440265", "41.815957", ""),
	SHIJIAZHUANG("石家庄","SHIJIAZHUANG", "石家庄","SHIJIAZHUANG", true,"SJZ", "shijiazhuang.qfang.com", DataSourceEnum.BEIJING, "114.518058", "38.057141", ""),
	TIANJIN("天津","TIANJIN", "天津","TIANJIN", true, "TJ", "tianjin.qfang.com", DataSourceEnum.BEIJING, "117.207076", "39.089624", ""),
	XIAN("西安","XIAN", "西安","XIAN", true,"XA", "xian.qfang.com", DataSourceEnum.ZHENGZHOU, "108.952523", "34.272073", ""),
	CHANGCHUN("长春","CHANGCHUN", "长春","CHANGCHUN", true,"CC", "changchun.qfang.com", DataSourceEnum.BEIJING, "125.337788", "43.909978", ""),
	HK("香港", "HK", "香港", "XIANGGANG",true, "HK", "hk.qfang.com", DataSourceEnum.HK, "121.480263", "31.236295","852"),
	CHANGZHOU("常州", "CHANGZHOU", "常州", "CHANGZHOU",true, "CZ", "changzhou.qfang.com", DataSourceEnum.HANGZHOU, "119.981574", "31.818047", ""),
	LANZHOU("兰州", "LANZHOU", "兰州", "LANZHOU",true, "LZ", "lanzhou.qfang.com", DataSourceEnum.ZHENGZHOU, "103.838967", "36.069879", ""),
	YANTAI("烟台", "YANTAI", "烟台", "YANTAI",true, "LT", "yantai.qfang.com", DataSourceEnum.ZHENGZHOU, "121.454425", "37.471472", ""),
	GUIYANG("贵阳", "GUIYANG", "贵阳", "GUIYANG",true, "GY", "guiyang.qfang.com", DataSourceEnum.GUANGZHOU, "106.637966", "26.653522", ""),
	WENZHOU("温州", "WENZHOU", "温州", "WENZHOU",true, "WZ", "wenzhou.qfang.com", DataSourceEnum.HANGZHOU, "120.163993", "30.282305", ""),
	HUAINAN("淮南","HUAINAN", "淮南","HUAINAN", true, "HN","huainan.qfang.com", DataSourceEnum.ZHENGZHOU, "120.388686", "36.074226", ""),
	HUAIHUA("怀化","HUAIHUA", "怀化","HUAIHUA", true, "HH","huaihua.qfang.com", DataSourceEnum.GUANGZHOU, "110.008547", "27.575595", ""),
	BINZHOU("滨州","BINZHOU", "滨州","BINZHOU", true, "BZ","binzhou.qfang.com", DataSourceEnum.ZHENGZHOU, "117.977188", "37.388502", ""),
	RIZHAO("日照","RIZHAO", "日照","RIZHAO", true, "RZ","rizhao.qfang.com", DataSourceEnum.ZHENGZHOU, "119.533175", "35.422974", ""),
	
	YUXI("玉溪","YUXI", "玉溪","YUXI", true, "YX","yuxi.qfang.com", DataSourceEnum.CHENGDU, "102.546657", "24.347241", ""),
	
	JILIN("吉林","JILIN", "吉林","JILIN", true, "JL","jilin.qfang.com", DataSourceEnum.BEIJING, "102.546657", "24.347241", ""),
	LESHAN("乐山","LESHAN", "乐山","LESHAN", true, "LS","leshan.qfang.com", DataSourceEnum.CHENGDU, "102.546657", "24.347241", ""),
	ZUNYI("遵义","ZUNYI", "遵义","ZUNYI", true, "ZY","zunyi.qfang.com",DataSourceEnum.GUANGZHOU, "102.546657", "24.347241", ""),
	WEIFANG("潍坊","WEIFANG", "潍坊","WEIFANG", true, "WF","weifang.qfang.com", DataSourceEnum.ZHENGZHOU, "102.546657", "24.347241", ""),
	

	
	QINGYUAN("清远","QINGYUAN", "清远","QINGYUAN", true, "QY","qingyuan.qfang.com", DataSourceEnum.GUANGZHOU, "102.546657", "24.347241", ""),
	
	DEZHOU("德州", "DEZHOU", "德州", "DEZHOU", true, "DZ", "dezhou.qfang.com",DataSourceEnum.ZHENGZHOU, "116.354902", "37.45071", ""),
	LINYI("临沂", "LINYI", "临沂", "LINYI", true, "LY", "linyi.qfang.com", DataSourceEnum.ZHENGZHOU, "118.362415", "35.106043", ""),
	BAODING("保定", "BAODING", "保定", "BAODING", true,  "BD", "baoding.qfang.com", DataSourceEnum.BEIJING, "115.47105", "38.880045", ""),

	LASA("拉萨", "LASA", "拉萨", "lasa", true, "LS", "lasa.qfang.com", DataSourceEnum.CHENGDU, "91.124762", "29.661513", ""),
	//非城市名，用于切换到存储房产百科数据库
	BAIKE("百科", "BAIKE", "百科", "BAIKE", true, "BK", "baike.qfang.com",  DataSourceEnum.BAIKE, "", "", ""),
	USER("用户", "USER", "用户", "USER", true,"US", "user.qfang.com", DataSourceEnum.USER, "", "", "")
	;
	
	private String name;
	private String value;
	private String cityName;
	private String erpCityEnum;
	private boolean isPortCity;//是否是端口城市
	private String domain;

	//拼音首字母
	private String simplePinyin; 
	
	private DataSourceEnum dataSource; //对应的数据源
	private String lng; //经度
    private String lat; //纬度
    
    private String cityNumber;//城市编号
	
	//端口城市
	private static List<CityEnum> portCityList = new ArrayList<CityEnum>();
	//非端口城市
	private static List<CityEnum> notPortCityList = new ArrayList<CityEnum>();
	
	static {
		CityEnum[] cityEnums = CityEnum.values();
		for (int i = 0; i < cityEnums.length; i++) {
			if (cityEnums[i].isPortCity) {
				portCityList.add(cityEnums[i]);
			} else {
				notPortCityList.add(cityEnums[i]);
			}
		}
	}
	
	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public boolean isPortCity() {
		return isPortCity;
	}

	public void setPortCity(boolean isPortCity) {
		this.isPortCity = isPortCity;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getErpCityEnum() {
		return erpCityEnum;
	}

	public void setErpCityEnum(String erpCityEnum) {
		this.erpCityEnum = erpCityEnum;
	}
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	private CityEnum(String name, String value,
			String cityName, String erpCityEnum, boolean isPortCity,
			String simplePinyin, String domain, DataSourceEnum dataSource,
			String lng, String lat, String cityNumber) {
		this.cityName = cityName;
		this.name = name;
		this.value = value;
		this.erpCityEnum = erpCityEnum;
		this.isPortCity = isPortCity;
		this.simplePinyin = simplePinyin;
		this.domain = domain;
		this.dataSource = dataSource;
		this.lng = lng;
        this.lat = lat;
        this.cityNumber = cityNumber;
	}
	
	public static List<CityEnum> toList() {
		CityEnum[] ary = CityEnum.values();
		List<CityEnum> line = new ArrayList<CityEnum>();
		for (int i = 0; i < ary.length; i++) {
			line.add(ary[i]);
		}
		return line;
	}
	
	public static CityEnum query(String city) {
		CityEnum[] ary = CityEnum.values();
		for (int i = 0; i < ary.length; i++) {
			if(ary[i].getName().indexOf(city) >= 0){
				return ary[i];
			}
		}
		return CityEnum.SHANGHAI;
	}
	
	/**
	 * 拼音首字母比较器，根据拼音首字母对城市进行排序
	 */
	public static class CityEnumSimplePinyinCompartor implements Comparator<CityEnum> {

		@Override
		public int compare(CityEnum city1, CityEnum city2) {
			return city1.simplePinyin.compareTo(city2.simplePinyin);
		}

	}
	
	/**
	 * 获取根据拼音首字母排序后的城市列表
	 * @return
	 */
	public static List<CityEnum> getSimplePinyinOrderedCities() {
		CityEnum[] cities = CityEnum.values();
		Arrays.sort(cities, new CityEnumSimplePinyinCompartor());
		return Arrays.asList(cities);
	}
	
	/**
	 * 获取非端口城市
	 * 
	 * @author WuKaiFang
	 * @date 2014-7-16 下午08:12:34
	 * @return
	 * List<CityEnum>
	 */
	public static List<CityEnum> getNotPortCitys() {
		return notPortCityList;
	}
	
	/**
	 * 获取端口城市
	 * 
	 * @author WuKaiFang
	 * @date 2014-7-16 下午08:12:34
	 * @return
	 * List<CityEnum>
	 */
	public static List<CityEnum> getPortCitys() {
		return portCityList;
	}
	
	public static CityEnum queryCityByCityName(String city) {
		CityEnum[] ary = CityEnum.values();
		for (int i = 0; i < ary.length; i++) {
			if (ary[i].getCityName().indexOf(city) >= 0) {
				return ary[i];
			}
		}
		return null;
	}
	
	public static CityEnum queryCityByValue(String city) {
		CityEnum[] ary = CityEnum.values();
		for (int i = 0; i < ary.length; i++) {
			if (ary[i].getValue().equals(city)) {
				return ary[i];
			}
		}
		return null;
	}

	public DataSourceEnum getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSourceEnum dataSource) {
		this.dataSource = dataSource;
	}
	
	public String getLng() {
        return lng;
    }
    
    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    
    
    
	
	public String getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}

	public static CityEnum getValueOf(String cityName){
		CityEnum[] cityEnums = CityEnum.values();
		for(CityEnum ce:cityEnums){
			if(ce.toString().equals(cityName)){
				return ce;
			}
		}
		return null;
	}
	
	private static final Set<CityEnum> REAL_ROOM_ENABLED_CITIES = EnumSet.of(SHENZHEN,ZH,ZHONGSHAN,FOSHAN,HANGZHOU,QINGDAO,SUZHOU,BEIJING,SHANGHAI,GUANGZHOU,NANJING,DONGGUAN);
	/**
	 * 是否是房源开通城市
	 * @param city
	 * @return
	 */
	public static boolean isRealRoomEnabledCity(CityEnum city) {
		return REAL_ROOM_ENABLED_CITIES.contains(city);
	}
	/**
	 * 是否显示真房源广告城市
	 * @return
	 */
	public boolean isRealRoomCity() {
		return REAL_ROOM_ENABLED_CITIES.contains(this);
	}
	
	private static final Set<CityEnum> REAL_ROOM_CITIES = EnumSet.of(SHENZHEN, DONGGUAN, SUZHOU,BEIJING,SHANGHAI,GUANGZHOU,NANJING);
	/**
	 * 是否是真房源城市（新O2O发房规则）
	 * @param city
	 */
	public static boolean isRealRoomCity(CityEnum city) {
		return REAL_ROOM_CITIES.contains(city);
	}
}
