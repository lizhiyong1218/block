/**
 * 
 */
package org.block.core.solr;

import java.util.ArrayList;
import java.util.List;


import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.constant.activiti.PropertyTypeEnum;
import com.lzy.block.search.solr.NewHouse;
import com.lzy.block.search.solr.common.ContextHolder;
import com.lzy.block.search.solr.condition.NewHouseSearchCondition;
import com.lzy.block.search.solr.enums.CityEnum;
import com.lzy.block.search.solr.page.Pagination;
import com.lzy.block.search.solr.searcher.SolrSearcher;


public class NewHouseTest  extends BaseTest{
	 
	 
//	@Resource
//	NewHouseSearcher newHouseSearcher;
	
	@Autowired
    private SolrSearcher<NewHouse> newHouseSearcher;
	
	
	@Test
	public void testFacet(){
		
	}
	
	@Test
	public void testQueryIndex(){
		CityEnum city=CityEnum.SHENZHEN;
//		String parentAreaName="龙岗";
//		String areaName="坂田";
//		String avgPrice="0";
//		PropertyTypeEnum propertyTypeEnum=PropertyTypeEnum.APARTMENT;	
//		String saleStatus="SOLD_OUT";
//		String lineName="11";
//		String subwayStation="1234";
//		List<String> features=new ArrayList<String>();
//		features.add("DISCOUNT");
//		features.add("LIVABLE");
//		String pinyin="";
		String keyword="";
		String parentAreaName="";
		String areaName="";
		String avgPrice="";
		PropertyTypeEnum propertyTypeEnum=null;
		String saleStatus="";
		String lineName="";
		String subwayStation="";
		String newHouseStatus="";
		String gardenStatus="";
		List<String> features=new ArrayList<String>();
		
		//排序
		String priceSort="";
		String openDateSort="asc";
		
		ContextHolder.setDataSource(city.toString());
		
		NewHouseSearchCondition criteria=new NewHouseSearchCondition();
		criteria.setCity(city);
		criteria.setParentAreaName(parentAreaName);
		criteria.setAreaName(areaName);
		criteria.setAvgPrice(avgPrice);
		criteria.setPropertyTypeEnum(propertyTypeEnum);
		criteria.setSaleStatus(saleStatus);
		criteria.setLineName(lineName);
		criteria.setSubwayStation(subwayStation);
		criteria.setFeatures(features);
		criteria.setKeyword(keyword);
		criteria.setPriceSort(priceSort);
		criteria.setOpenDateSort(openDateSort);
		criteria.setNewHouseStatus(newHouseStatus);
		criteria.setGardenStatus(gardenStatus);
		
//		criteria.setPinyin(pinyin);
//		criteria.setDefType("edismax");
//		criteria.setPf("quanpin1323 jianpin");
//		criteria.setBf("quanpin^1 jianpin^0.8");
		
		Pagination<NewHouse> pagination=new Pagination<NewHouse>();
		pagination.setCurrentPage(1);
		pagination.setPageSize(10);
		Pagination<NewHouse> query = newHouseSearcher.query(criteria, pagination);
		List<NewHouse> items = query.getItems();
		System.err.println(items.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		for (NewHouse newHouse : items) {
//			System.err.println(newHouse.getOpenDate());
//		}
	}
	 
	
}
