package com.hnzy.util;

import java.util.UUID;

/**
 * 通用字符串判断
 * @author XuXueYang
 *
 * @time 2016-10-15
 */
public class StringUtil {

	/**
	 * 获取UUID
	 * 
	 * @return 32UUID小写字符
	 */
	public static String gainUUID() {
		String strUUID = UUID.randomUUID().toString();
		strUUID = strUUID.replaceAll("-", "").toLowerCase();
		return strUUID;
	}

	/**
	 * 判断是否是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || ("".equals(str.trim())));
	}

	/**
	 * 判断是否不是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !((str == null) || ("".equals(str.trim())));
	}

//	/**
//	 * 得到URL中字符串参数，只适合URL中有一个参数
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public static String getParamFromUrl(String url) {
//		String afterQuestion = url.substring(url.indexOf("?") + 1);
//		return afterQuestion.substring(afterQuestion.indexOf("=") + 1);
//	}
	
//	/**
//     * 格式化模糊查询
//     * @param str
//     * @return
//     */
//    public static String formatLike(String str){
//        if(isNotEmpty(str)){
//            return "%"+str+"%";
//        }else{
//            return null;
//        }
//    }
}
