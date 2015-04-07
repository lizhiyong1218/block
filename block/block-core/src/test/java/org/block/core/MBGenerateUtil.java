package org.block.core;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 
 * @ClassName: MBGenerateUtil
 * @Description: 生成mybatis mapper文件
 * @author 李志勇
 * @date 2015年4月7日 下午4:56:19
 * 
 */
public class MBGenerateUtil {
	private static void generateMbgConfiguration() {
        /*
         * Mybatis自带Generator工具生成相应东西
         */
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
//        File configFile = new File("src/main/resources/mbg_configuration.xml");
        String path= MBGenerateUtil.class.getClassLoader().getResource("mbg_configuration.xml").getPath();
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("生成Mybatis配置成功！");
    }
	
	public static void main(String[] args) {
		generateMbgConfiguration();
//		String pathname="src/main/resources/log4j.properties";
//		File file=new File(pathname);
//		System.out.println(file.exists());
//		String path= MBGenerateUtil.class.getClassLoader().getResource("mbg_configuration.xml").getPath();
//		System.out.println(path);
	}
	

}
