package com.iwantrun.front.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JSONUtils {

	public static <T> String objToJSON(T t) {
		if (t == null) {
			return "";
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			
			return mapper.writeValueAsString(t);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
		try {
			// MapperFeature.INFER_PROPERTY_MUTATORS 推断出属性赋值
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			return mapper.readValue(jsonStr, clazz);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 转化为List
	public static <T> List<T> toList(String text, Class<T> clazz) {
		try {
			TypeFactory factory = TypeFactory.defaultInstance();
			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(text, factory.constructCollectionType(List.class, clazz));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map jsonToMap(String json) {
		
		ObjectMapper mapper = new ObjectMapper(); //转换器  
		Map map;
		try {
			map = mapper.readValue(json, Map.class);
			return map;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
