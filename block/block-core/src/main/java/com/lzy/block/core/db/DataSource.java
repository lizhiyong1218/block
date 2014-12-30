/**  
* @Title: DataSource.java
* @Package com.lzy.block.core.db
* @author 李志勇  
* @date 2014年12月29日 下午3:51:41
* @version V1.0  
*/ 
package com.lzy.block.core.db;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Target;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy; 

/**
 * @ClassName: DataSource
 * @Description: 数据源注解 
 * @author 李志勇
 * @date 2014年12月29日 下午3:51:41
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface DataSource {
    String value();
}

