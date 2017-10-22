package org.slsale.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 

 */
public final class StringUtils {

	/** HTML标签正则表达式 */
	private static final Pattern HTML_PATTERN = Pattern.compile("<[^<^>]+>");

	/** 工具类不允许构造 */
	private StringUtils() {
	}

	/**
	 * 判断字符串是否为空：字符串的值是null和""，返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

	/**
	 * 获取字符串长度，null和""返回0
	 * 
	 * @param str
	 * @return
	 */
	public static int length(String str) {
		return str != null && !"".equals(str) ? str.length() : 0;
	}

	/**
	 * 字符串转换成小写字母，忽略null
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (str != null) {
			return str.toLowerCase();
		}
		return null;
	}

	/**
	 * 字符串转成大写字母，忽略null
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (str != null) {
			return str.toUpperCase();
		}
		return null;
	}

	/**
	 * 截取字符串前后空格，忽略null
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str != null) {
			return str.trim();
		}
		return null;
	}

	/**
	 * 过滤HTML标签，将HTML转换为文本。null返货空字符串
	 * 
	 * @param html
	 * @return
	 */
	public static String html2Text(final String html) {
		if (html == null) {
			return "";
		}
		String htmlstr = html.replaceAll("&nbsp;", "");
		Matcher matcher = HTML_PATTERN.matcher(htmlstr);
		return matcher.replaceAll("|");
	}
}
