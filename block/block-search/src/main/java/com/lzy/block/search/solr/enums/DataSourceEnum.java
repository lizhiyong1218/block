package com.lzy.block.search.solr.enums;


public enum DataSourceEnum {
    SHENZHEN("SHENZHEN", "深圳", DataCenterEnum.SOUTH), 
    ZH("ZH", "珠海", DataCenterEnum.SOUTH), 
    DONGGUAN("DONGGUAN", "东莞", DataCenterEnum.SOUTH), 
    HUIZHOU("HUIZHOU", "惠州", DataCenterEnum.SOUTH), 
    ZHONGSHAN("ZHONGSHAN", "中山", DataCenterEnum.SOUTH),
    SHANGHAI("SHANGHAI", "上海", DataCenterEnum.EAST),
    CHENGDU("CHENGDU", "成都", DataCenterEnum.EAST),
    CHONGQING("CHONGQING", "重庆", DataCenterEnum.EAST),
    WUHAN("WUHAN", "武汉", DataCenterEnum.EAST),
    SUZHOU("SUZHOU", "苏州", DataCenterEnum.EAST),
    NANJING("NANJING", "南京", DataCenterEnum.EAST),
    ZHENGZHOU("ZHENGZHOU", "郑州", DataCenterEnum.NORTH),
    HANGZHOU("HANGZHOU", "杭州", DataCenterEnum.EAST),
    GUANGZHOU("GUANGZHOU", "广州", DataCenterEnum.EAST),
    FOSHAN("FOSHAN", "佛山", DataCenterEnum.SOUTH),
    BEIJING("BEIJING", "北京", DataCenterEnum.NORTH),
    HK("HK", "香港", DataCenterEnum.EAST), 
    BAIKE("BK", "百科", DataCenterEnum.NORTH),
    USER("US", "用户", DataCenterEnum.NORTH);
    
    DataSourceEnum(String value, String desc, DataCenterEnum dataCenter) {
        this.value = value;
        this.desc = desc;
        this.dataCenter = dataCenter;
    }

    private String value;
    private String desc;
    private DataCenterEnum dataCenter;
    
    public DataCenterEnum getDataCenter() {
		return dataCenter;
	}

	public void setDataCenter(DataCenterEnum dataCenter) {
		this.dataCenter = dataCenter;
	}

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static DataSourceEnum getDataSource(String dataSource) {
        try {
            return DataSourceEnum.valueOf(dataSource.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
