package com.lzy.block.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author 李志勇
 * @date 2015年8月26日 下午1:41:00
 * 
 */
public class TxtUtil {
	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static void readTxtFile(String filePath) {
		try {
			String encoding = "UTF-8";// 根据文件类型，选择UTF-8或者GBK
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String[] s=null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
//					System.out.println(lineTxt);
					s=lineTxt.split("	");//特殊空格字符
					System.out.println(s.length);
					for(int i=0;i<s.length;i++){
						System.out.println(i+"---"+s[i]);
					}
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath = "f:/C1.txt";
		// String filePath = "f:/过滤仓库11.txt";
		readTxtFile(filePath);
	}

}
