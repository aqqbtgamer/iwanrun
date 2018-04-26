package com.iwantrun.core.service.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class PageDataWrapUtils {
	static Logger logger = LoggerFactory.getLogger(PageDataWrapUtils.class);
	
	public static <T>String page2Json(Page<T> page) {
		JSONObject obj = new JSONObject();
		JSONObject pageInfo = new JSONObject();
		JSONArray content = new JSONArray();
		pageInfo.put("pageSize", page.getSize());
		pageInfo.put("currentPage", page.getNumber());
		pageInfo.put("currentSize", page.getNumberOfElements());
		pageInfo.put("total", page.getTotalElements());
		pageInfo.put("totalpage", page.getTotalPages());
		obj.put("pageInfo", pageInfo);
		List<T> contentList = page.getContent();
		for(T t :contentList) {
			content.add(simpleBeanCopy(t));
		}
		obj.put("content", content);
		return obj.toJSONString();
	}
	
	public static JSONObject simpleBeanCopy(Object bean) {
		if(bean == null) {
			return null;
		}
		JSONObject obj = new JSONObject();
		try {
			Map<String,Object> beanMap =PropertyUtils.describe(bean);			
			Set<String> properties =beanMap.keySet();
			for(String property : properties) {
				Object value = beanMap.get(property);
				if(value != null) {
					if(value instanceof Date) {
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						obj.put(property, format.format(date));
					}else {
						obj.put(property, value.toString());
					}
				}				
			}
		} catch (IllegalAccessException e) {
			logger.error("error for bean copy ...",e);
		} catch (InvocationTargetException e) {
			logger.error("error for bean copy ...",e);
		} catch (NoSuchMethodException e) {
			logger.error("error for bean copy ...",e);
		}
		return obj;
	}

}
