/**  
* @Title: DataSourceAspect.java
* @Package com.lzy.block.core.db
* @author 李志勇  
* @date 2014年12月29日 下午3:56:30
* @version V1.0  
*/ 
package com.lzy.block.core.db;

import java.lang.reflect.Method;  

import org.aspectj.lang.JoinPoint;  
//import org.aspectj.lang.annotation.Aspect;  
//import org.aspectj.lang.annotation.Before;  
//import org.aspectj.lang.annotation.Pointcut;  
import org.aspectj.lang.reflect.MethodSignature;  
//import org.springframework.stereotype.Component;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzy.block.api.constant.common.DatasourceEnum;


/**
 * 在xml文件中配置了，不使用注解方式
 * @ClassName: DataSourceAspect
 * @Description: 数据源切面 
 * @author 李志勇
 * @date 2014年12月29日 下午3:56:30
 *
 */
//@Aspect  
//@Component 
public class DataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	 //@Pointcut("execution(* com.apc.cms.service.*.*(..))")    
    public void pointCut(){};    
      
  //  @Before(value = "pointCut()")  
     public void before(JoinPoint point)  
        {  
            Object target = point.getTarget();  
            logger.debug(target.toString());  
            String method = point.getSignature().getName();  
            logger.debug(method);  
//            Class<?>[] classz = target.getClass().getInterfaces(); //获取该类实现的interface
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())  
                    .getMethod().getParameterTypes();  
            try {  
                Method m = target.getClass().getMethod(method, parameterTypes);  
                logger.debug(m.getName());  
                /*Annotation[] annotations = m.getAnnotations(); 获取方法的所有注解
                for (Annotation annotation : annotations) {
					logger.info(annotation.toString());
				}*/
                if (m != null && m.isAnnotationPresent(DataSource.class)) {//判断是否有加datasorce注解  
                    DataSource data = m.getAnnotation(DataSource.class);  
                    String datasource=data.value();
                    if(DatasourceEnum.existValue(datasource)){
                    	HandleDataSource.putDataSource(data.value());
                    }else{
                    	HandleDataSource.putDataSource(DatasourceEnum.MASTER.value());
                    }
                }else{//如果没有加注解，则使用默认的datasource
                	HandleDataSource.putDataSource(DatasourceEnum.MASTER.value());
                }
            } catch (Exception e) {  
            	logger.error("设置数据源错误!",e);  
            }  
        }  
}
