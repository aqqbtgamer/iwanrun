package com.iwantrun.core.service.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class EntityBeanUtils {
	
	static Logger logger = LoggerFactory.getLogger(EntityBeanUtils.class);
	
	/**
	 * 只能拷贝简单的get Set属性 不适合复杂属性
	 * @param t
	 * @param relation
	 * @param obj
	 * @return
	 */
	public static <T> T beanCreateFromJson(T t, Map<String,String> relation , JSONObject obj) {
		if(relation == null || obj == null || t == null) {
			return t ;
		}else {
			Set<Entry<String, String>> mapEntrySet = relation.entrySet();
			for(Entry<String,String> mappingItem : mapEntrySet) {
				try {
					Object property = obj.get(mappingItem.getValue());
					if(property instanceof JSONArray) {
						BeanUtils.setProperty(t, mappingItem.getKey(), ((JSONArray)property).toJSONString());
					}else {
						BeanUtils.setProperty(t, mappingItem.getKey(), property);
					}
					
				} catch (IllegalAccessException e) {					
					logger.error("生成entitybean出错",e);
				} catch (InvocationTargetException e) {					
					logger.error("生成entitybean出错",e);
				}
			}
			return t ;
		}
	}
	
	/**
	 * 只能复制单一简单属性 
	 * @param t
	 * @param relation
	 * @param obj
	 * @return
	 */
	public static <T> T beanCreateFromSimpleValue(T t, Map<String,String> relation , Object obj) {
		if(relation == null || obj == null || t == null) {
			return t ;
		}else {
			Set<Entry<String, String>> mapEntrySet = relation.entrySet();
			for(Entry<String,String> mappingItem : mapEntrySet) {
				try {
					BeanUtils.setProperty(t, mappingItem.getKey(), obj);
				} catch (IllegalAccessException e) {					
					logger.error("生成entitybean出错",e);
				} catch (InvocationTargetException e) {					
					logger.error("生成entitybean出错",e);
				}
			}
			return t ;
		}
	}
	
	/**
	 * 只能复制简单属性的list
	 * @param t
	 * @param relation
	 * @param array
	 * @param instanceClass
	 * @return
	 */
	public static <T> List<T> listBeanCreateFromJson(List<T> t, Map<String,String> relation, JSONArray array,Class<T> instanceClass){
		if(t== null || relation == null || array == null) {
			return t ;
		}else {
			int size0 = array.size();
			int size = t.size();
			if(size < size0) {
				// update for 0....size
				for(int i=0; i< size ; i++) {
					T tItem = t.get(i);
					Object obj = array.get(i);
					beanCreateFromSimpleValue(tItem,relation,obj);
				}
				//create for size ... size0
				for(int i=size ; i< size0; i++) {
					try {
						T tItem = instanceClass.newInstance();
						Object obj = array.get(i);
						beanCreateFromSimpleValue(tItem,relation,obj);
						t.add(tItem);
					} catch (InstantiationException e) {
						logger.error("生成entitybean出错",e);
					} catch (IllegalAccessException e) {
						logger.error("生成entitybean出错",e);
					}
				}
			}else {
				for(int i=0; i< size0 ; i++) {
					T tItem = t.get(i);
					Object obj = array.get(i);
					beanCreateFromSimpleValue(tItem,relation,obj);
				}
			}
		}
		return t ;
	}

}
