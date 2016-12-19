package org.block.search.solr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.qfang.common.ContextHolder;
//import com.qfang.common.page.Pagination;
//import com.qfang.model.Garden;
//import com.qfang.model.Person;
//import com.qfang.model.Room;
//import com.qfang.model.enums.CityEnum;
//import com.qfang.model.enums.DecorationEnum;
//import com.qfang.model.enums.RentTypeEnum;
//import com.qfang.model.enums.RoomBizTypeEnum;
//import com.qfang.model.enums.RoomTypeEnum;
//import com.qfang.search.GardenSearchCriteria;
//import com.qfang.search.SearchCriteria;
//import com.qfang.search.Searcher;
//import com.qfang.search.page.SolrPagination;
//import com.qfang.search.solr.spring.SolrTemplate;
import org.block.search.solr.spring.SolrTemplate;

import com.lzy.block.api.model.newhouse.NewHouse;

public abstract class AbstractSearcher extends SolrTemplate<NewHouse> implements Searcher<NewHouse> {
//    @Autowired
//    private Searcher<Garden> gardenSolrSearcher;//小区索引对象
    public static final String BEDROOM = "bedRoom"; // 房型（卧室）
    public static final String BEDROOM_NAMES = "bedRoomNames"; // 房型（卧室）名称列表
    public static final String LIVINGROOM = "livingRoom"; // 房型（客厅）
    public static final String LIVINGROOM_NAMES = "livingRoomNames"; // 房型（客厅）名称
    public static final String BATHROOM = "bathRoom"; // 卫生间
    public static final String PROPERTY_TYPE = "propertyType"; // 物业类型
    public static final String ROOM_TYPE = "roomType";// 房间类型
    public static final String GARDEN_PRIMARY_SCHOOL = "primarySchool";// 小学
    public static final String GARDEN_MIDDLE_SCHOOL = "middleSchool";// 中学
    public static final String GARDEN_PRIMARY_SCHOOL_IDS = "primarySchoolIds";// 小学
    public static final String GARDEN_MIDDLE_SCHOOL_IDS = "middleSchoolIds";// 中学
    public static final String RENT_TYPE = "rentType";// 出租方式
    public static final String DECORATION = "decoration";// 装修类型
    public static final String GARDENPICTURES = "gardenPictures";//小区图片
    public static final String LAYOUTPICTURE  = "layoutPicture";
    public static final String NAME = "name";//名字
    public static final String FACILITY_NAMES = "facilityNames"; // 配套设施
    public static final String BUSINESS_IDS = "businessIds";// 商圈ID列表
    public static final String BUSINESS_NAMES = "businessNames";// 商圈名称列表
    public static final String OFFICE_BUSINESS_IDS = "officeBusinessIds";// 商圈ID列表
    public static final String OFFICE_BUSINESS_NAMES = "officeBusinessNames";//写字楼商圈名称列表
    public static final String STATIONGARDENDESTANCE = "stationGardenDestance";//地铁小区距离;
//    public CityEnum getCity() {
//        String dataSource = ContextHolder.getDataSource();
//        if(dataSource == null) return CityEnum.SHENZHEN;
//        return CityEnum.valueOf(dataSource.toUpperCase());
//    }
    
//    public Pagination<Room> query(SearchCriteria criteria, Pagination<Room> pagination) {
//        if (criteria.isGroup()){
//    		Pagination<Room> page = queryGroup(criteria, pagination);
//    		executeQueryGarden(page);
//            return page;
//        }
//        if (pagination instanceof SolrPagination) {
//            SolrPagination<Room> page = (SolrPagination<Room>)pagination;
//            page.setCriteria(criteria);
//            return queryFacet(criteria, page);
//        }
//        Pagination<Room> page = query(criteria.getSolrQuery(), pagination);
//        //executeQueryGarden(page);
//		return page;
//    }
//    
//    private void executeQueryGarden(Pagination<Room> page){
//    	try {
//        	List<Integer> gardenIds = new ArrayList<Integer>();
//        	if(page.getItems()!=null && page.getItems().size()>0){
//        		Set<Integer> set = new HashSet<Integer>();
//        		for(Room room:page.getItems()){
//        			if(room.getGarden()==null){
//        				continue;
//        			}
//        			set.add(room.getGarden().getId());
//        		}
//        		Iterator<Integer>  it=set.iterator();
//        		while(it.hasNext()){
//        			gardenIds.add(it.next());
//        		}
//        	}
//        	
//        	if(gardenIds!=null && gardenIds.size()!=0){
//        		GardenSearchCriteria criteria = new GardenSearchCriteria();
//        		criteria.setGardenIds(gardenIds);
//        		Pagination<Garden> gardenPage = new Pagination<Garden>(gardenIds.size(),1);
//        		gardenSolrSearcher.query(criteria, gardenPage);
//        		if(gardenPage.getItems()!=null && gardenPage.getItems().size()>0){
//        			Map<Integer,Garden> gardenMap = new HashMap<Integer,Garden>();
//        			for(Garden garden:gardenPage.getItems()){
//        				gardenMap.put(garden.getId(), garden);
//        			}
//        			for(Room room:page.getItems()){
//            			room.setGarden(gardenMap.get(room.getGarden().getId()));
//            		}
//        		}
//        	}	
//    		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
//    
//    @SuppressWarnings("unchecked")
//	@Override
//    public Room solrDocumentToEntity(SolrDocument solrDocument) throws Exception {
//        if (solrDocument == null)
//            return null;
//        Room room = new Room();
//        room.setId((Integer) solrDocument.get(ID));
//        room.setTitle((String) solrDocument.get(TITLE));
//
//        
//        String roomType = (String) solrDocument.get(ROOM_TYPE);
//        room.setRoomType(roomType != null ? RoomTypeEnum.valueOf(roomType) : null);
//        
//        String rentType = (String) solrDocument.get(RENT_TYPE);
//        room.setRentType(rentType != null ? RentTypeEnum.valueOf(rentType) : null);
//        
//
//        Double price = (Double) solrDocument.get(PRICE);
//        room.setPrice(price != null?BigDecimal.valueOf(price):BigDecimal.valueOf(0));
//        
//        Double area = (Double) solrDocument.get(AREA);
//        room.setArea(area != null?BigDecimal.valueOf(area):BigDecimal.valueOf(0));
//        
//        room.setBedRoom((Integer) solrDocument.get(BEDROOM));
//        room.setLivingRoom((Integer) solrDocument.get(LIVINGROOM));
//        room.setFloor((Integer) solrDocument.get(FLOOR));
//        room.setCreateTime((Date) solrDocument.get(CREATE_TIME));
//        room.setUpdateTime((Date) solrDocument.get(UPDATE_TIME));
//        room.setTotalFloor((Integer) solrDocument.get(TOTAL_FLOOR));
//        room.setIndexPictureUrl((String) solrDocument.get(INDEXPICTUREURL));
//        
//        Integer pictureCount=(Integer)solrDocument.get("pictureCount");
//        room.setPictureCount(pictureCount==null?0:pictureCount);
//        
//        Integer bathRoom=(Integer) solrDocument.get(BATHROOM);
//        room.setBathRoom(bathRoom==null?0:bathRoom);
//        
//        String decoration=(String)solrDocument.get(DECORATION);
//        room.setDecoration(decoration!=null?DecorationEnum.valueOf(decoration):null);
//
//	    String city=(String) solrDocument.get(CITY);
//        room.setCity(city != null ? CityEnum.valueOf(city) : null);
//        
//        
//        Integer personId=(Integer) solrDocument.get(PERSON_ID);
//        if(personId!=null){
//        	Person person = new Person();
//        	person.setName((String) solrDocument.get(PERSON_NAME));
//        	person.setId(personId);
//        	person.setCompany((String) solrDocument.get(PERSON_COMPANY));
//        	room.setPerson(person);
//        }
//        
//        Long refreshValue=(Long)solrDocument.get(REFRESHVALUE);
//        room.setRefreshValue(refreshValue!=null?refreshValue:0);
//        
//        List<String> facilityNames = (List<String>) solrDocument.get(FACILITY_NAMES);
//        if (null != facilityNames) {
//        	StringBuilder sb = new StringBuilder();
//        	boolean isFirst = true;
//        	for (String str : facilityNames) {
//        		if (isFirst) {
//        			isFirst = false;
//        		} else {
//        			sb.append(",");
//        		}
//    			sb.append(str);
//        	}
//        	room.setFacility(sb.toString());
//        }
//        return room;
//    }
//
//    @Override
//    public SolrInputDocument entityToSolrInputDocument(Room room) throws Exception {
//        if (room == null)
//            return null;
//        SolrInputDocument solrInputDocument = new SolrInputDocument();
//        solrInputDocument.setField(ID, room.getId());
//        solrInputDocument.setField(TITLE, room.getTitle(), 2);
//        solrInputDocument.setField(PRICE, room.getPrice());
//        solrInputDocument.setField(AREA, room.getArea());
//        solrInputDocument.setField(UNIT_PRICE, room.getArea()!=null && room.getArea().intValue() != 0 ? room.getPrice().divide(room.getArea(),0, BigDecimal.ROUND_HALF_EVEN) : 0);
//        int bedRoom = room.getBedRoom()!=null?room.getBedRoom():0;
//        solrInputDocument.setField(BEDROOM, bedRoom);
//        String[] bedRooms = {"房", "室", "居"};
//		for (int i = 0; i < bedRooms.length; i++) {
//			solrInputDocument.addField(BEDROOM_NAMES, bedRoom + bedRooms[i]);
//		}
//        List<String> values = NumberMapper.getValue(bedRoom);
//        if (null != values) {
//        	for (String value : values) {
//        		for (int i = 0; i < bedRooms.length; i++) {
//        			solrInputDocument.addField(BEDROOM_NAMES, value + bedRooms[i]);
//        		}
//        	}
//        }
//        int livingRoom = room.getLivingRoom()!=null?room.getLivingRoom():0;
//        solrInputDocument.setField(LIVINGROOM, livingRoom);
//        List<String> livingRoomValues = NumberMapper.getValue(livingRoom);
//        if (null != livingRoomValues) {
//        	for (String value : livingRoomValues) {
//        		solrInputDocument.addField(LIVINGROOM_NAMES, value + "厅");
//        	}
//        }
//        solrInputDocument.addField(LIVINGROOM_NAMES, livingRoom + "厅");
//        
//        solrInputDocument.setField(FLOOR, room.getFloor());
//        solrInputDocument.setField(CREATE_TIME, room.getCreateTime());
//        solrInputDocument.setField(UPDATE_TIME, room.getUpdateTime());
//        solrInputDocument.setField(TOTAL_FLOOR, room.getTotalFloor());
//
//        solrInputDocument.setField(INDEXPICTUREURL, room.getIndexPictureUrl());
//        //solrInputDocument.setField(LAST_FOLLOW_TIME, room.getUpdateTime());
//        solrInputDocument.setField(ISCHECK, room.getIsCheck());//设置核查值
//        solrInputDocument.setField(ROOM_TYPE, room.getRoomType());
//        solrInputDocument.setField(BATHROOM, room.getBathRoom());
//        //solrInputDocument.setField(RECOMMEND, room.getRecommend());
//        //solrInputDocument.setField(IS_EXCELLENT, room.getIsExcellent());
//        
//        solrInputDocument.setField(RANDING, room.getRanking());
//        
//        solrInputDocument.setField(REFRESH_TIME, room.getRefreshTime());
//        solrInputDocument.setField(CITY, room.getCity() != null ? room.getCity().toString() : null);
//        int count = room.getIndoorPictures() != null ? room.getIndoorPictures().split("\\|\\|\\|").length : 0;
//        if (room.getBizType() == RoomBizTypeEnum.RENT) {
//            solrInputDocument.setField(RENT_TYPE, room.getRentType());
//            solrInputDocument.setField(CAREATE_TIME, room.getCreateTime());
//        }
//        solrInputDocument.setField(DECORATION, room.getDecoration());
//        if (count >= 4) {
//            solrInputDocument.setField(MANY_IMAGE, 1);
//        } else {
//            solrInputDocument.setField(MANY_IMAGE, 0);
//        }
//
//        if (room.getPerson() != null) {
//            solrInputDocument.setField(PERSON_ID, room.getPerson().getId());
//            solrInputDocument.setField(PERSON_NAME, room.getPerson().getName());
//            //solrInputDocument.setField(PERSON_COMPANY, room.getPerson().getCompany());
//        }
//        if (room.getBuilding() != null) {
//            solrInputDocument.setField(TOTAL_FLOOR, room.getBuilding().getFloorNumber());
//        }
//        solrInputDocument.setField(REFRESHVALUE, room.getRefreshValue());
//        //solrInputDocument.setField(GARDENPICTURES, room.getGardenPictures());
//        return solrInputDocument;
//    }
//    
//    static class NumberMapper {
//    	private static Map<Integer, List<String>> datas = new ConcurrentHashMap<Integer, List<String>>();
//    	static {
//    		datas.put(1, Arrays.asList("一"));
//    		datas.put(2, Arrays.asList("二", "两"));
//    		datas.put(3, Arrays.asList("三"));
//    		datas.put(4, Arrays.asList("四"));
//    		datas.put(5, Arrays.asList("五"));
//    		datas.put(6, Arrays.asList("六"));
//    		datas.put(7, Arrays.asList("七"));
//    		datas.put(8, Arrays.asList("八"));
//    		datas.put(9, Arrays.asList("九"));
//    		datas.put(10, Arrays.asList("十"));
//    	}
//    	
//    	public static List<String> getValue(Integer key) {
//    		if (datas.containsKey(key)) {
//    			return datas.get(key);
//    		}
//    		return null;
//    	}
//    }
}
