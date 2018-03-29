package com.hnzy.util;

import java.util.Properties;

/**
 * 系统配置文件参数获取工具
 * @author XuXueYang
 *
 * @time 2017-3-16
 */
public final class PropertyUtil {
	private static Properties props;

	private PropertyUtil() {
	}
	public static String Informationerror="账户或密码错误"; 
	public static String Informationempty="账户或密码为空"; 
	public static String Accountdisabled="账户已停用，请联系管理员"; 
//	static {
//		props = new Properties();
//		try {
//			props.load(PropertyUtil.class
//					.getResourceAsStream("/config.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static Properties getSysProperties() {
		return props;
	}

	/**
	 * 获取指定的系统属�?
	 * 
	 * @param key
	 *            指定的属性名�?
	 * @return 指定的系统属性�?
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取指定的系统属�?
	 * 
	 * @param key
	 *            指定的属性名�?
	 * @param defaultVal
	 *            默认�?
	 * @return 指定的系统属性�?
	 */
	public static String getProperty(String key, String defaultVal) {
		return props.getProperty(key, defaultVal);
	}

}
