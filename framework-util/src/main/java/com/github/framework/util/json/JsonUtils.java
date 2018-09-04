package com.github.framework.util.json;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:48:08
 * @Description: java对象与json转换
 */
public class JsonUtils {
	
private static final ObjectMapper mapper = new ObjectMapper();
	
	static{
		mapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	}
	
	public static <T> T toObject(String jsonStr, Class<T> t){
		if(jsonStr == null || "".equals(jsonStr)){
			return null;
		}
		try {
			return mapper.readValue(jsonStr, t);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toJson(Object o){
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* jsonToList<T> 带泛型
	* jsonStr
	* clazz  集合元素类型
	*/
	
	public static <T> List<T> toList(String jsonStr, Class<T> clazz){
		JavaType javaType = getCollectionType(List.class,clazz);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* jsonToMap<T> 带泛型
	* jsonStr  json字符串
	* keyClazz     key class
	* valueClazz	value class
	*/
	public static <K,V> Map<K, V> toMap(String jsonStr, Class<K> keyClazz, Class<V> valueClazz){
		JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, keyClazz,valueClazz);
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
	
	/**
	* 泛型Json字符串解析对象
	* @param jsonStr 字符串
	* @param
	* @param
	* @return
	* @author 龙海仁
	* @date 2015年6月3日
	* @update
	*/
	public static <T> T toObject(String jsonStr, Class<T> t, Class<?> c){
		return toObject(jsonStr,t,null,c);
	}
	
	/**
	* 泛型集合Json解析
	* @param jsonStr
	* @param t
	* @param collection
	* @param c
	* @return
	* @author 龙海仁
	* @date 2015年6月3日
	* @update
	*/
	@Deprecated
	public static <T> T toObject(String jsonStr, Class<T> t, Class<?> collection, Class<?> c){
		if(jsonStr == null || "".equals(jsonStr)){
			return null;
		}
		JavaType javaType = null;
		if(collection == null){
			javaType = getJavaType(t,c);
		}else{
			javaType = getJavaType(t,getJavaType(collection, c));
			
		}
		try {
			return mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* json泛型解析
	* @param jsonStr
	* @param typeReference
	* @return
	* @author 龙海仁
	* @date 2015年7月23日
	* @update
	*/
	public static <T> T toObject(String jsonStr, TypeReference<T> typeReference){
		if(jsonStr == null || "".equals(jsonStr)){
			return null;
		}
		try {
			return mapper.readValue(jsonStr, typeReference);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JavaType getJavaType(Class<?> collectionClass,
			Class<?> elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
	
	public static JavaType getJavaType(Class<?> clazz, JavaType javaType){
		return mapper.getTypeFactory().constructParametricType(clazz,
				javaType);
	}
}
