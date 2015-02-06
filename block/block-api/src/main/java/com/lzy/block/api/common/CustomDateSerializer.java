
 

package com.lzy.block.api.common;
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.codehaus.jackson.JsonGenerator;  
import org.codehaus.jackson.JsonProcessingException;  
import org.codehaus.jackson.map.JsonSerializer;  
import org.codehaus.jackson.map.SerializerProvider;  
  
/**
 * 自定义返回JSON 数据格中日期格式化处理   
 * @author: ZhiYong.Li  
 * @date:2014年7月11日  
 * @Copyright:Copyright (c) 中国电子器材深圳有限公司 2014 ~ 2015 版权所有
 */
public class CustomDateSerializer extends JsonSerializer<Date> {  
  
    @Override  
    public void serialize(Date value,   
            JsonGenerator jsonGenerator,   
            SerializerProvider provider)  
            throws IOException, JsonProcessingException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        jsonGenerator.writeString(sdf.format(value));  
    }  
}
