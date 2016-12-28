/**
 * 
 */
package org.block.core.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.block.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.constant.activiti.PropertyTypeEnum;
import com.lzy.block.api.constant.newhouse.CityEnum;
import com.lzy.block.api.model.newhouse.NewHouse;
import com.lzy.block.core.dao.newhouse.NewHouseMapper;
import com.lzy.block.search.solr.common.ContextHolder;
import com.lzy.block.search.solr.condition.NewHouseSearchCondition;
import com.lzy.block.search.solr.page.SolrPagination;
import com.lzy.block.search.solr.searcher.SolrSearcher;
import com.lzy.block.search.solr.vo.SolrGroupVo;


public class NewHouseTest  extends BaseTest{
	 
	 
//	@Resource
//	NewHouseSearcher newHouseSearcher;
	
	@Resource
    private SolrSearcher<NewHouse> newHouseSearcher;
	@Resource
	private NewHouseMapper newHouseMapper;
	
	@Test
	public void testGetByParams(){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("id", 1000276);
		List<NewHouse> list = newHouseMapper.getByParams(params);
		for (NewHouse newHouse : list) {
			System.err.println(newHouse.getId());
		}
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
//		criteria.setPropertyTypeEnum(propertyTypeEnum);
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
		
		SolrPagination<NewHouse> pagination=new SolrPagination<NewHouse>();
		pagination.setCurrentPage(1);
		pagination.setPageSize(10);
		newHouseSearcher.query(criteria, pagination);
		List<NewHouse> items = pagination.getItems();
		System.err.println(items.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Test
	public void testFacet(){
		CityEnum city=CityEnum.SHENZHEN;
		ContextHolder.setDataSource(city.toString());
		NewHouseSearchCondition criteria=new NewHouseSearchCondition();
		List<String> facetFields=new ArrayList<String>();
		facetFields.add("saleStatus");
		criteria.setFacetFields((String[])facetFields.toArray(new String[facetFields.size()]));
		criteria.setCity(city);
		SolrPagination<NewHouse> pagination=new SolrPagination<NewHouse>();
		pagination.setPageSize(5);
		pagination.setCurrentPage(1);
		newHouseSearcher.queryFacet(criteria, pagination);
		Map<String, Map<String, Long>> facetRes = pagination.getFacetRes();
		for(String fieldName:facetRes.keySet()){
			Map<String, Long> map = facetRes.get(fieldName);
			for(String fieldValue:map.keySet()){
				System.err.println(fieldName+">>"+fieldValue+">>"+map.get(fieldValue));
			}
		}
		List<NewHouse> items = pagination.getItems();
		for (NewHouse newHouse : items) {
			System.err.println(newHouse.getId());
		}
	}
	
	@Test
	public void testGroupby(){
		CityEnum city=CityEnum.SHENZHEN;
		ContextHolder.setDataSource(city.toString());
		NewHouseSearchCondition criteria=new NewHouseSearchCondition();
		criteria.setCity(city);
		criteria.setGroupField("saleStatus");
		criteria.setGroupLimit(5);
		criteria.setGroupSortByField("avgPrice");
		criteria.setGroupSortBy("desc");
		SolrPagination<NewHouse> pagination=new SolrPagination<NewHouse>(5, 1);
		newHouseSearcher.queryGroup(criteria, pagination);
		Map<String, Map<String, SolrGroupVo<NewHouse>>> groupRes = pagination.getGroupRes();
		SolrGroupVo<NewHouse> solrGroupVo=null;
		for(String filedName : groupRes.keySet()){
			for(String fieldValue: groupRes.get(filedName).keySet()){
				solrGroupVo = groupRes.get(filedName).get(fieldValue);
				List<NewHouse> list = solrGroupVo.getList();
				for (NewHouse newHouse : list) {
					System.out.println(solrGroupVo.getFieldName()+">>"+solrGroupVo.getFieldValue()+">>"+solrGroupVo.getCount()+">>"+newHouse.getAvgPrice());
				}
			}
		}
	}
	
}
