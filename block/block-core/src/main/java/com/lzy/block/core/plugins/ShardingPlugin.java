/**
 * 
 */
package com.lzy.block.core.plugins;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.lzy.block.api.model.dictionary.DictionaryItemModel;

/**
 * @ClassName: ShardingPlugin
 * @Description: 分表分库插件 
 * @author 李志勇
 * @date 2016年3月10日 下午5:57:41
 *
 */
@Intercepts(@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}))
public class ShardingPlugin implements Interceptor{
	
	private String table;
	
	/**
	 * 1.获取SQL语句和参数
	 * 2.判断sql语句中是否包含表名
	 * 3.获取参数
	 * 4.拿到参数值根据策略替换sql中的表
	 * 5.重新给sql对象设置sql语句
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try {	
				StatementHandler statement = (StatementHandler) invocation.getTarget();
				BoundSql boundSql = statement.getBoundSql();
				String sql = boundSql.getSql();//sql语句
				Object obj= boundSql.getParameterObject();//参数
				if((sql.trim().contains("t_sys_dictionary_item") || sql.trim().contains("T_SYS_DICTIONARY_ITEM"))){
					String dictionaryValue=null;
					if(obj instanceof Integer){ 
						dictionaryValue=(String)obj;
					}else if(obj instanceof String){
						dictionaryValue=obj.toString();
					}else if(obj instanceof DictionaryItemModel){
						DictionaryItemModel d=(DictionaryItemModel) obj;
						dictionaryValue=d.getDictionaryValue();
					}
					else if(obj instanceof Map){
						Map<String,Object> map=(Map<String,Object>)obj;
						if(dictionaryValue==null && map.containsKey("dictionaryValue")){
							dictionaryValue=map.get("dictionaryValue").toString();
						}
						if(dictionaryValue==null && map.containsKey("dictionaryValue")){
							dictionaryValue=map.get("dictionaryValue").toString();
						}
					} 
					
					if(dictionaryValue!=null){
						if(dictionaryValue.equalsIgnoreCase("RMATYPE")){//根据sql参数修改表
//							String tableName=table+" ";
//							sql=sql.replaceAll("t_sys_dictionary_item\\(",tableName+"(").replaceAll("t_sys_dictionary_item[^_]", tableName);
//							FieldUtils.writeDeclaredField(boundSql, "sql", sql, true);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置属性
	 */
	@Override
	public void setProperties(Properties properties) {
		this.table= properties.getProperty("table");
		System.err.println(table);
	}

}
