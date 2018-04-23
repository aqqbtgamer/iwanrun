package com.iwantrun.admin.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class FormDataUtils {
	
	public static String formData2Json(HttpServletRequest request,List<String> paramList) {
		String result = null ;
		JSONObject obj = new JSONObject();
		for(String param : paramList) {
			if(!StringUtils.isEmpty(param)) {
				if(param.endsWith("[]")) {
					String[] paramvalues = request.getParameterValues(param);
					JSONArray array = new JSONArray();
					if(paramvalues != null) {
						array.addAll(Arrays.asList(paramvalues));
						obj.put(param, array);
					}					
				}else{
					obj.put(param, request.getParameter(param));
				}
			}
		}
		result = obj.toJSONString();
		return result ;
	}
	
	public static List<String> stringArray2List(String[] array){
		List<String> result = new ArrayList<String>();
		if(array != null) {
			for(String add : array) {
				result.add(add);
			}
		}
		return result ;
	}

}
