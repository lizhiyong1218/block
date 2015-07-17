package com.lzy.block.core.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzy.block.api.common.StrUtil;
import com.lzy.block.api.constant.activiti.PropertyTypeEnum;

public class Variable {
	protected static final Logger logger = LoggerFactory.getLogger(Variable.class);

    public static Map<String, Object> getVariableMap(String variables) {
        Map<String, Object> vars = new HashMap<String, Object>();
        ConvertUtils.register(new DateConverter(), java.util.Date.class);
        
        // 参数为空自己接返回
        if (StrUtil.isEmpty(variables)) {
            return vars;
        }
        
        // 将Json字符串转化成Map列表
        List<LinkedHashMap<String, Object>> mappings = buildTree(variables);
        
        // 将列表按照类型转化成相应的类型并加入Map
        for (LinkedHashMap<String, Object>  keyValuePairs: mappings) {
        	String key = (String) keyValuePairs.get("key");
            Object orignalValue = keyValuePairs.get("value");
            String value = null;
            if(orignalValue!=null){
            	value = String.valueOf(orignalValue);
            }
            String type = (String) keyValuePairs.get("type");
            
            if(StrUtil.isEmpty(value)||StrUtil.isEmpty(type)){
            	vars.put(key, value);
            	continue;
            }
            
            Class<?> targetType = Enum.valueOf(PropertyTypeEnum.class, type).getValue();
            Object objectValue = null;
            if(value instanceof String&&type.equals("D")){
            	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            	try {
					objectValue = format.parseObject(value);
				} catch (ParseException e) {
					logger.error("类型转换出错 ！"+e.getMessage());
				}
            }else{
            	objectValue = ConvertUtils.convert(value, targetType);
            }
            vars.put(key, objectValue);
		}
        return vars;
    }
    
    @SuppressWarnings("unchecked")
	private static List<LinkedHashMap<String, Object>> buildTree(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(jsonString, List.class);
		} catch (Exception e) {
			logger.error("Json解析错误 "+e.getMessage()+" Json:"+jsonString);
		}
		return new ArrayList<LinkedHashMap<String, Object>>();
	}

}
