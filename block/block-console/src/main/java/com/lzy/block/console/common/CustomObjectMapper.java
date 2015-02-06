/**  
* @Title: CustomObjectMapper.java
* @Package com.lzy.block.console.common
* @author 李志勇  
* @date 2015年2月6日 上午10:00:13
* @version V1.0  
*/ 
package com.lzy.block.console.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * @ClassName: CustomObjectMapper
 * @Description: springmvc返回json时间类型格式化 
 * @author 李志勇
 * @date 2015年2月6日 上午10:00:13
 *
 */
public class CustomObjectMapper extends ObjectMapper{
	public CustomObjectMapper(){
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){
			@Override
			public void serialize(Date value, 
					JsonGenerator jsonGenerator, 
					SerializerProvider provider)
					throws IOException, JsonProcessingException {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonGenerator.writeString(sdf.format(value));
			}
		});
		this.setSerializerFactory(factory);
	}
}