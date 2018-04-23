package com.iwantrun.core.service.utils;

import java.util.List;


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
			content.add(t);
		}
		obj.put("content", content);
		return obj.toJSONString();
	}

}
