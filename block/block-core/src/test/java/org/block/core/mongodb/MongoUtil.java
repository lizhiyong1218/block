package org.block.core.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
	public static MongoUtil mongoUtil = null;
	public static MongoClient mongoClient = null;
	public static MongoDatabase mongoDataBase = null;

	private MongoUtil() {
	}

	public static MongoUtil getInstance() {
		if (mongoUtil == null) {
			mongoUtil = new MongoUtil();
		}
		return mongoUtil;
	}

	public void init() {
		mongoUtil.closeMongoClient();
		mongoUtil.getMongoClient();
		mongoUtil.getMongoDataBase();
	}

	private void getMongoClient() {
		System.out.println("*********** getMongoClient***********");
		try {
			// 用户名 数据库 密码
			MongoCredential credential = MongoCredential.createCredential("test1", "test", "test1".toCharArray());
			// IP port
			ServerAddress addr = new ServerAddress("192.168.0.115", 27017);
			mongoClient = new MongoClient(addr, Arrays.asList(credential));
			// 得到数据库
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMongoDataBase() {
		System.out.println("*********** getMongoDataBase***********");
		try {
			if (mongoClient != null) {
				// 得到数据库
				mongoDataBase = mongoClient.getDatabase("test");
			} else {
				throw new RuntimeException("MongoClient不能够为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeMongoClient() {
		System.out.println("*********** closeMongoClient***********");
		if (mongoDataBase != null) {
			mongoDataBase = null;
		}
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	/**
	 * 将实体类的obj的字段信息和内容动态放到mapParams里面
	 * 
	 * @param mapParams
	 * @param obj
	 * @param method
	 */
	public void dymParms(Object mapParams, Object obj, String method) {
		try {
			if (obj != null) {
				Field[] fields = obj.getClass().getDeclaredFields();
				Class<?>[] arrClazz = new Class[2];
				arrClazz[0] = String.class;
				arrClazz[1] = Object.class;
				Method m = mapParams.getClass().getDeclaredMethod(method,
						arrClazz);
				m.setAccessible(true);
				if (fields != null) {
					for (Field f : fields) {
						f.setAccessible(true);
						Object value = f.get(obj);
						if (null!=value) {
							Class<?> clazz = value.getClass();
							Object[] strs = new Object[2];
							if (clazz == String.class) {
								if ( !"".equals(String.valueOf(value))) {
									strs[0] = f.getName();
									strs[1] = value;
									m.invoke(mapParams, strs);
								}
							} else {
								strs[0] = f.getName();
								strs[1] = value;
								m.invoke(mapParams, strs);
							}
						}
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

