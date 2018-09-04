package com.github.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:49:08
 * @Description: 反射工具类
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils{
	/**
	 * 属性复制
	 * @param source 源对象
	 * @param target 目标对象
	 * @param nullValueCopy 是否复制null值
	 * @author 龙海仁
	 * @date 2015年9月6日
	 * @update 
	 */
	public static void copyProperties(Object source, Object target, boolean nullValueCopy){
		Field[] targetFields =  target.getClass().getDeclaredFields();
		Field[] sourceFields =  source.getClass().getDeclaredFields();
		for (int i = 0; i < targetFields.length; i++) {
			Field field = targetFields[i];
			if(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())){
				continue;
			}
			for (Field sourceField : sourceFields) {
				if(sourceField.getName().equals(field.getName())&&sourceField.getType().equals(field.getType())){
					setPropertyValue(target, field.getName(), getPropertyValue(source, field.getName()));
					break;
				}
			}
		}
	}
	
	/**
	 * 属性复制
	 * @param source 源对象
	 * @param target 目标对象
	 * @author 龙海仁
	 * @date 2015年9月6日
	 * @update 
	 */
	public static void copyProperties(Object source, Object target){
		copyProperties(source, target, true);
	}
	
	/**
	 * 获取对象属性值
	 * @param obj 对象
	 * @param property 属性名称
	 * @return 属性值
	 * @author 龙海仁
	 * @date 2015年9月6日
	 * @update 
	 */
	public static Object getPropertyValue(Object obj, String property){
		try {
			Field field = obj.getClass().getDeclaredField(property);
			makeAccessible(field);
			return getField(field, obj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置属性对象值
	 * @param obj 对象
	 * @param property 属性名称
	 * @param value 属性值
	 * @author 龙海仁
	 * @date 2015年9月6日
	 * @update 
	 */
	public static void setPropertyValue(Object obj, String property, Object value){
		try {
			Field field = obj.getClass().getDeclaredField(property);
			makeAccessible(field);
			setField(field, obj, value);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
}
