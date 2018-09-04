package com.github.framework.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 
 * @author
 * @create：2016年4月14日 下午3:48:06
 * @description：
 */
public class FastJsonUtil {

	/**
	 *
	 */
	public static Object toObject(String jsonString) {
		try {
			return JSON.parse(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /** 
     * 序列化 
     */  
	public static String toJsonString(Object o) {
		try {
			return JSON.toJSONString(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
  
    /** 
     * 反序列化为json对象 
     */  
	public static JSONObject toJson(String jsonStr) {
		try {
			return JSON.parseObject(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}  
  
    /** 
     * 反序列化为javaBean对象 
     */  
	public static <T> T json2Bean(String jsonStr, Class<T> t) {
		try {
			return JSON.parseObject(jsonStr, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
  
    /** 
     * 将javaBean转化为json对象 
     */  
	public static JSONObject bean2Json(Object o) {
		try {
			return (JSONObject) JSON.toJSON(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @author
	 * @date 2016年4月14日下午4:02:06
	 * @update 
	 */
	public static <T> List<T> toList(String jsonStr, Class<T> clazz){
		try {
			return JSON.parseArray(jsonStr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
  

}
