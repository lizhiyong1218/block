/**  
* @Title: JsonUtils.java
* @Package com.lzy.block.core.common
* @author 李志勇  
* @date 2014年11月21日 上午10:37:42
* @version V1.0  
*/ 
package com.lzy.block.core.common;

import java.io.IOException;
import java.util.List;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: JsonUtils
 * @Description: json工具类 
 * @author 李志勇
 * @date 2014年11月21日 上午10:37:42
 *
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils(){}
    public static ObjectMapper getInstance() {  
            return objectMapper;  
    }  

    /**
     * 使用Jackson 数据绑定 将对象转换为 json字符串
     * 
     * 还可以 直接使用 JsonUtils.getInstance().writeValueAsString(Object obj)方式 
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("转换为json字符串失败" + e.toString());
        } catch (JsonMappingException e) {
            logger.error("转换为json字符串失败" + e.toString());
        } catch (IOException e) {
            logger.error("转换为json字符串失败" + e.toString());
        }
        return null;
    }

    /**
     * json字符串转化为 JavaBean
     * 
     * 还可以直接JsonUtils.getInstance().readValue(String content,Class valueType)用这种方式
     * @param <T>
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T toJavaBean(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (JsonParseException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        } catch (JsonMappingException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        } catch (IOException e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        }
        return null;
    }

    /**
     * json字符串转化为list
     * 
     * 还可以 直接使用  JsonUtils.getInstance().readValue(String content, new TypeReference<List<T>>(){})方式
     * @param <T>
     * @param content
     * @param valueType
     * @return
     * @throws IOException 
     */
    public static <T> List<T> toJavaBeanList(String content, TypeReference<List<T>>  typeReference) throws IOException {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (JsonParseException e) {
            logger.error("json字符串转化为 list失败,原因:" + e.toString());
            throw new RuntimeException("json字符串转化为 list失败");
        } catch (JsonMappingException e) {
            logger.error("json字符串转化为 list失败,原因" + e.toString());
            throw new JsonMappingException("json字符串转化为 list失败");
        } catch (IOException e) {
            logger.error("json字符串转化为 list失败,原因" + e.toString());
            throw new IOException("json字符串转化为 list失败");
        }
    }
    
    /**
     * 嵌套数组测试
     */
    public static void  testIncludeJsonArr(){
//		String str="{'list':[{'oaPriceClassInstallPrice':'1','oaPriceClassName':'1','taxMoney':'1','oaPriceClassAccessories':'1','priceStr':[{'oaCommodityList_id':'983','oaCommodityListName':'中性笔','oaCommodityListBrand':'晨光','oaCommodityListSpecification':'油性金色','oaCommodityType':'','oaCommodityListUnits':'支','oaEngPriceNumber':'2','oaEngPricePrice':'3.0','oaEngPriceRemark':''},{'oaCommodityList_id':'982','oaCommodityListName':'移动硬盘','oaCommodityListBrand':'西部数据','oaCommodityListSpecification':'1TB','oaCommodityType':'','oaCommodityListUnits':'个','oaEngPriceNumber':'1','oaEngPricePrice':'450.0','oaEngPriceRemark':''}]},{'oaPriceClassInstallPrice':'22','oaPriceClassName':'22','taxMoney':'22','oaPriceClassAccessories':'22','priceStr':[{'oaCommodityList_id':'983','oaCommodityListName':'中性笔','oaCommodityListBrand':'晨光','oaCommodityListSpecification':'油性金色','oaCommodityType':'','oaCommodityListUnits':'支','oaEngPriceNumber':'2','oaEngPricePrice':'3.0','oaEngPriceRemark':''},{'oaCommodityList_id':'982','oaCommodityListName':'移动硬盘','oaCommodityListBrand':'西部数据','oaCommodityListSpecification':'1TB','oaCommodityType':'','oaCommodityListUnits':'个','oaEngPriceNumber':'3','oaEngPricePrice':'450.0','oaEngPriceRemark':''}]}]}";
//		JSONObject object = new JSONObject(str);
//		JSONArray list = object.getJSONArray("list");
		
    	JSONArray list=new JSONArray("[{'oaPriceClassInstallPrice':'1','oaPriceClassName':'1','taxMoney':'1','oaPriceClassAccessories':'1','priceStr':[{'oaCommodityList_id':'983','oaCommodityListName':'中性笔','oaCommodityListBrand':'晨光','oaCommodityListSpecification':'油性金色','oaCommodityType':'','oaCommodityListUnits':'支','oaEngPriceNumber':'2','oaEngPricePrice':'3.0','oaEngPriceRemark':''},{'oaCommodityList_id':'982','oaCommodityListName':'移动硬盘','oaCommodityListBrand':'西部数据','oaCommodityListSpecification':'1TB','oaCommodityType':'','oaCommodityListUnits':'个','oaEngPriceNumber':'1','oaEngPricePrice':'450.0','oaEngPriceRemark':''}]},{'oaPriceClassInstallPrice':'22','oaPriceClassName':'22','taxMoney':'22','oaPriceClassAccessories':'22','priceStr':[{'oaCommodityList_id':'983','oaCommodityListName':'中性笔','oaCommodityListBrand':'晨光','oaCommodityListSpecification':'油性金色','oaCommodityType':'','oaCommodityListUnits':'支','oaEngPriceNumber':'2','oaEngPricePrice':'3.0','oaEngPriceRemark':''},{'oaCommodityList_id':'982','oaCommodityListName':'移动硬盘','oaCommodityListBrand':'西部数据','oaCommodityListSpecification':'1TB','oaCommodityType':'','oaCommodityListUnits':'个','oaEngPriceNumber':'3','oaEngPricePrice':'450.0','oaEngPriceRemark':''}]}]");
		for(int i=0; i<list.length(); i++){
		    JSONObject entityObj = list.getJSONObject(i);           
		    JSONArray entitys = entityObj.getJSONArray("priceStr");
		        for(int j=0; j<entitys.length(); j++){
		            JSONObject entity = entitys.getJSONObject(j);
		        String date = entity.getString("oaCommodityList_id");
		        System.err.println(date);
		    }      
		}
    }
    
    public static void main(String[] args) {
    	testIncludeJsonArr();
	}

}