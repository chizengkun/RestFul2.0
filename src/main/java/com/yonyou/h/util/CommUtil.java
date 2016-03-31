package com.yonyou.h.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.ufida.hap.util.DateUtils;

/**
 * <p>Title: CommUtil<��p>
 * <p>Description: ���ù�����<��p>
 * <p>Copyright: Copyright (c) 2014<��p>
 * @author yelk
 * @since 2014-3-27
 * @version 1.0
 */
public class CommUtil {

	/**
	 * �ַ���ת����Integer����
	 * @param value
	 * @return Integer
	 */
	public static Integer checkIntVal(String value,Integer defaultVal) {
		Integer retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Integer.parseInt(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Float����
	 * @param value
	 * @return Float
	 */
	public static Float checkFloatVal(String value, Float defaultVal) {
		Float retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Float.parseFloat(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Double����
	 * @param value
	 * @return Double
	 */
	public static Double checkDoubleVal(String value, Double defaultVal) {
		Double retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Double.parseDouble(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Short����
	 * @param value
	 * @return Short
	 */
	public static Short checkShortVal(String value, Short defaultVal) {
		Short retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Short.parseShort(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Long����
	 * @param value
	 * @return Long
	 */
	public static Long checkLongVal(String value, Long defaultVal) {
		Long retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Long.parseLong(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Boolean����
	 * @param value
	 * @return Boolean
	 */
	public static Boolean checkBooleanVal(String value, Boolean defaultVal) {
		Boolean retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Boolean.parseBoolean(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * ����תΪBoolean
	 * @param value
	 * @return Boolean
	 */
	public static Boolean checkBooleanNullFalse(Object value){
		String val = String.valueOf(value);
		Boolean retVal = false;
		if(checkNull(val)){
			return false;
		}
		try {
			retVal = Boolean.parseBoolean(val);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Byte����
	 * @param value
	 * @return Byte
	 */
	public static Byte checkByteVal(String value, Byte defaultVal) {
		Byte retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Byte.parseByte(value);
		} catch (Exception e) {
		}
		return retVal;
	}
	
	/**
	 * �ַ���ת����Character����
	 * @param value
	 * @return Character
	 */
	public static Character checkCharVal(String value, Character defaultVal) {
		Character retVal = defaultVal;
		if(value == null)
			return null;
		try {
			retVal = Character.valueOf(value.charAt(0));
		} catch (Exception e) {
		}
		return retVal;
	}

	/**
	 * ����ַ����Ƿ�Ϊnull��""
	 * @param str
	 * @return boolean
	 */
	public static boolean checkNull(String str) {
		return (str == null || "".equals(str) || "null".equals(str));
	}
	
	/**
	 * ����ַ����Ƿ�Ϊnull��""����Ϊnull��""ʱ����defaultVal��ֵ
	 * @param str
	 * @param defaultVal
	 * @return String
	 */
	public static String checkNull(String str, String defaultVal){
		if (checkNull(str))
			return defaultVal;
		else
			return str;
	}
	
	/**
	 * ������ַ����Ƿ���ֵ
	 * @param str
	 * @return boolean
	 */
	public static boolean checkIsNotNull(String str){
		return (str != null && !"".equals(str) && !"null".equals(str));
	}
	
	/**
	 * ��map�л�ȡ����ֵ
	 * @param map
	 * @param key
	 * @return Integer
	 */
	public static Integer getIntVal(Map<String, Object> map, String key) {
		Integer retVal = null;
		Object object = map.get(key);
		if(object != null) {
			String objStr = String.valueOf(object);
			try {
				retVal = Integer.parseInt(objStr);
			} catch (NumberFormatException e) {
				retVal = null;
			}
		}
		return retVal;
	}
	
	/**
	 * ��map�л�ȡ����ֵ
	 * @param map
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static Integer getIntVal(Map<String, Object> map, String key, Integer defaultVal) {
		Integer retVal = defaultVal;
		Object object = map.get(key);
		if(object != null) {
			String objStr = String.valueOf(object);
			try {
				retVal = Integer.parseInt(objStr);
			} catch (NumberFormatException e) {
				
			}
		}
		return retVal;
	}
	
	/**
	 * ��map�л�ȡDouble
	 * @param map
	 * @param key
	 * @return
	 */
	public static Double getDoubleVal(Map<String, Object> map, String key) {
		return checkDoubleVal(getStringVal(map, key), null);
	}
	
	/**
	 * ��map�л�ȡDouble
	 * @param map
	 * @param key
	 * @return
	 */
	public static Double getDoubleVal(Map<String, Object> map, String key, Double defaultVal) {
		return checkDoubleVal(getStringVal(map, key), defaultVal);
	}
	
	/**
	 * ��map�л�ȡFloat
	 * @param map
	 * @param key
	 * @return
	 */
	public static Float getFloatVal(Map<String, Object> map, String key) {
		return checkFloatVal(getStringVal(map, key), null);
	}
	
	/**
	 * ��map�л�ȡFloat
	 * @param map
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static Float getFloatVal(Map<String, Object> map,String key, Float defaultVal) {
		return checkFloatVal(getStringVal(map, key), defaultVal);
	}
	
	/**
	 * ��map�л�ȡ�ַ���
	 * @param map
	 * @param key
	 * @return String
	 */
	public static String getStringVal(Map<String, Object> map, String key){
		Object object = map.get(key);
		return String.valueOf(object);
	}
	
	public static String getString(Map<Object, Object> map, String key) {
		Object obj = map.get(key);
		return String.valueOf(obj);
	}
	
	/**
	 * ��Map�л�ȡString,null����""
	 * @param map
	 * @param key
	 * @return String
	 */
	public static String getStringValNullBlank(Map<String, Object> map, String key){
		Object object = map.get(key);
		return object == null ? "" : String.valueOf(object);
	}
	
	/**
	 * ��ȡ����ʱ���
	 * @param map
	 * @param sd
	 * @param ed
	 * @param format
	 * @return String
	 */
	public static String dateRange(Map<String, Object> map, String sd, String ed, String format){
		Object sdObj = map.get(sd);
		Object edObj = map.get(ed);
		if(sdObj != null && edObj != null){
			Date sDate = (Date)sdObj;
			Date eDate = (Date)edObj;
			return new SimpleDateFormat(format).format(sDate) + "-" + new SimpleDateFormat(format).format(eDate);
		}
		return "";
	}
	
	/**
	 * ��ʽ��Integer����
	 * @param rq
	 * @param pattern
	 * @return
	 */
	public static String formatDateTime(Integer rq, String pattern) {
		return DateUtils.format(DateUtils.integerToDate(rq), pattern);
	}
	
	/**
	 * ��ʽ�����ڸ�ʽ
	 * @param rq
	 * @return
	 */
	public static String formatDateTime(Integer rq) {
		return formatDateTime(rq, "yyyyMM");
	}
	
	public static void transDate(Map<String, Object> map, String fieldName) {
		Integer val = getIntVal(map, fieldName);
		if(val != null) {
			map.put(fieldName, DateUtils.intToDateString(val));
		}
	}

	public static String transDateTime(Integer rq, Integer sj) {
		if(rq == null)
			return "";
		return DateUtils.format(DateUtils.integerToDateTime(rq, sj), "yyyy-MM-dd HH:mm:ss");
	}
}
