package com.iwantrun.core.service.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.iwantrun.core.service.application.annotation.DictionaryField;
import com.iwantrun.core.service.application.domain.Dictionary;

public class EntityDictionaryConfigUtils {
	
	public static <T> Map<String,Dictionary> getDictionaryMaping(T entity){
		Map<String,Dictionary> resultMap = new HashMap<String,Dictionary>();
		Field[] entityFields = entity.getClass().getDeclaredFields();
		for(Field field : entityFields) {
			DictionaryField dicField = field.getAnnotation(DictionaryField.class);
			if(dicField != null) {
				String name = dicField.name();
				int usedField = dicField.usedField();
				Dictionary dictionary = new Dictionary();
				dictionary.setName(name);
				dictionary.setUsed_field(String.valueOf(usedField));
				resultMap.put(field.getName(), dictionary);
			}
		}
		return resultMap;		
	}

}
