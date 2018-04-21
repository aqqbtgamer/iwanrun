package com.iwantrun.core.service.utils;

import java.util.HashMap;
import java.util.Map;

public class MappingGenerateUtils {
	
	/**
	 * mapping "key => value" like string array to a mapping map
	 * @param mappingArray
	 * @return
	 */
	public static Map<String,String> generateMappingRelation(String[] mappingArray){
		Map<String,String> resultMap = new HashMap<String,String>();
		for(String mappingItem : mappingArray) {
			String[] entity = mappingItem.split("=>");
			resultMap.put(entity[0].trim(), entity[1].trim());
		}
		return resultMap ;
	}

}
