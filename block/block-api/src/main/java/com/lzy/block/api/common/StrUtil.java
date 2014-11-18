package com.lzy.block.api.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

	private StrUtil() {
		throw new Error("不要实例化我");
	}

	/**
	 * 将字符串以seperator形式分割放入string[] ,然后组合输出
	 */

	public static String[] splitstring(String s) {
		// 全角转换为半角
		s = StrUtil.ToDBC(s);
		String[] splitstring = null;
		// 如果字符串不是以逗号分割，则返回字符串本身
		if (s.indexOf(",") == -1) {
			splitstring = new String[1];
			splitstring[0] = s;
		} else {

			splitstring = s.split(",");
		}

		return splitstring;

	}

	/**
	 * 将list用seperator分割返回字符串
	 * 
	 * @param source
	 * @param seperator
	 * @return
	 */
	public static String join(List<String> source, String seperator) {
		if (source.size() > 0) {
			StringBuffer ret = new StringBuffer("");
			int size = source.size();
			ret.append(source.get(0));

			for (int i = 1; i < size; ++i) {
				ret.append(seperator);
				ret.append(source.get(i));
			}
			return ret.toString();
		}
		return "";
	}

	/**
	 * 彻底去除字符串的空格 传入参数:需要去除空白,换行, 制表符的字符串 输出:整理过后的字符串
	 */

	public static String replaceBlank(String replacestring) {

		String afterreplacestring = replacestring.replaceAll("\\s*", "");
		return afterreplacestring;
	}

	public static String trimNull(String str, String defValue) {
		String temp = str;
		try {
			if (str == null || "null".equalsIgnoreCase(str) || "".equals(str)) {
				temp = defValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 将 :数字 成 “:数字”
	 * 
	 * @param content
	 * @return
	 */
	public static String replace4ExcelGrid(String content) {

		Matcher mData = Pattern.compile(":((\\d+)(\\.\\d+)?)(,|})").matcher(content);
		StringBuffer sb = new StringBuffer();
		while (mData.find()) {
			mData.appendReplacement(sb, ":\"" + mData.group(1) + "\"" + mData.group(4));
		}
		mData.appendTail(sb);
		return sb.toString();

	}

	/**
	 * 转全角的函数(SBC case)
	 * 
	 * // /全角空格为12288，半角空格为32 // /其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * /
	 **/
	public static String ToSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 转半角的函数(DBC case) 任意字符串 半角字符串 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 **/
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 
	 * 方法用途: 判空<br>
	 * 实现步骤: <br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return (str.trim().equals(""));
	}

	/**
	 * 
	 * 方法用途: 判非空<br>
	 * 实现步骤: <br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (!(isEmpty(str)));
	}

	// 将字符串转换为列表
	public static List<String> strToList(String str, String regex) {
		String[] strs = null;
		;
		if (str != null) {
			strs = str.split(regex);
		} else {
			strs = "".split(regex);
		}
		List<String> list = new ArrayList<String>();
		for (String s : strs) {
			list.add(s);
		}
		return list;
	}

	// 将json转换为list
	public static List<Map<String, String>> jsonToMapList(String jsonString) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String keyAndValue = "";
		// 去掉最前和最后的{,}
		jsonString = jsonString.substring(1, jsonString.length() - 1);
		// 避免了与json中value值中逗号相冲突
		String[] strArray = jsonString.split("\",");
		for (int i = 0; i < strArray.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			if (i != strArray.length - 1) {
				keyAndValue = strArray[i] + "\"";
			} else {
				// 最后一个分割出的字符串不少'
				keyAndValue = strArray[i];
			}
			String[] arr = keyAndValue.split(":");

			map.put("extendtypevalue", arr[0]);
			if (arr[0].equals("\"7_ip\"")) {
				arr[1] = arr[1].replaceAll(" ", "");
				arr[1] = arr[1].replace("\\t", "");
			}
			map.put("extendvalue", arr[1]);
			result.add(map);
		}
		return result;
	}

	/**
	 * 在某个字符串中删除特殊字符
	 * 
	 * @param s
	 *            原字符串
	 * @param regEx
	 *            ,要删除的特殊字符
	 */

	public static String deleteSpecialChar(String s, String deletechar) {

		String regEx = "[`~!" + deletechar + "]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		s = m.replaceAll("").trim();
		return s;
	}

	// 将字符串转换整型List
	public static List<Integer> strToIntList(String str, String regex) {
		String[] strs = null;
		;
		if (str != null) {
			strs = str.split(regex);
		} else {
			strs = "".split(regex);
		}
		List<Integer> list = new ArrayList<Integer>();
		for (String s : strs) {
			list.add(new Integer(s));
		}
		return list;
	}
	 

	/**
	 * 判断字符串是否为数字 方法用途: <br>
	 * 实现步骤: <br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}