package com.iwantrun.core.service.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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

	// 转化为Collection
	public static <T> Collection<T> toCollection(String text, Class<T> clazz, Class<Collection<T>> clazzC) {
		try {
			TypeFactory factory = TypeFactory.defaultInstance(); 
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.readValue(text,factory.constructCollectionType(clazzC,clazz));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
