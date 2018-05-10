package com.iwantrun.core.service.utils;

public interface DictionaryConfigParams {
	
	//场地数据字典类型
	int LOCATION_TAGS_TYPE = 1 ;
	
	String LOCATION_TAGS_DESC = "特色关键字";
	
	int LOCATION_TYPE = 2 ;
	
	String LOCATION_DESC = "场地类型";
	
	int LOCATION_GROUP_NUMBER_LIMIT_TYPE = 3;
	
	String LOCATION_GROUP_NUMBER_LIMIT_DESC = "可容纳人数";
	
	int LOCATION_PRICE_TYPE = 4 ;
	
	String LOCATION_PRICE_DESC = "参考报价区间";
	
	int LOCATION_USAGE_TYPE = 5 ;
	
	String LOCATION_USAGE_DESC = "场地用途";
	
	String LOCATION_DICTIONARY_NAME = "location";
	
	int[] LOCATION_DICTIONARY_TYPE_ARRAY = new int[] {
			LOCATION_TAGS_TYPE,
			LOCATION_TYPE,
			LOCATION_GROUP_NUMBER_LIMIT_TYPE,
			LOCATION_PRICE_TYPE,
			LOCATION_USAGE_TYPE			
	};
	
	String[] LOCATION_DICTIONARY_DESC_ARRAY = new String[] {
			LOCATION_TAGS_DESC,
			LOCATION_DESC,
			LOCATION_GROUP_NUMBER_LIMIT_DESC,
			LOCATION_PRICE_DESC,
			LOCATION_USAGE_DESC	
	};
	
	//通用数据字典类型
	int COMMON_PROVINCE_TYPE = 6 ;
	
	int COMMON_CITY_TYPE = 7 ;
	
	int COMMON_DIST_TYPE = 8 ;
	
	int COMMON_ACTIVITY_TYPE = 9;
	
	int COMMON_ACTIVITY_PURPOSE_TYPE = 10 ;
	
	String COMMON_DICTIONARY_NAME = "common";
	
	int[] COMMON_DICTIONARY_TYPE_ARRAY = new int[] {
			COMMON_PROVINCE_TYPE,
			COMMON_CITY_TYPE,
			COMMON_DIST_TYPE,
			COMMON_ACTIVITY_TYPE,
			COMMON_ACTIVITY_PURPOSE_TYPE		
	};
	
	
	default int getLocationIdByDesc(String desc){
		if(desc == null) {
			return -1;
		}else {
			int index = 0;
			for(String tmp : LOCATION_DICTIONARY_DESC_ARRAY) {
				if(tmp.equals(desc)) {
					return LOCATION_DICTIONARY_TYPE_ARRAY[index];
				}
				index++;
			}
			return -1 ;
		}
	}

}
