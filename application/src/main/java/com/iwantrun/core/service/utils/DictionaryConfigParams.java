package com.iwantrun.core.service.utils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface DictionaryConfigParams {
	
	//数据字典大类型
	String LOCATION_DICTIONARY_NAME = "location";
	
	String COMMON_DICTIONARY_NAME = "common";
	
	String PRODUCTION_DICTIONARY_NAME = "production";
	
	String CASE_DICTIONARY_NAME = "case";
	
	//数据字典大类型描述
	String LOCATION_DICTIONARY_DESC = "场地数据字典";
	
	String COMMON_DICTIONARY_DESC ="通用数据字典";
	
	String PRODUCTION_DICTIONARY_DESC = "产品数据字典";
	
	String CASE_DICTIONARY_DESC = "案例数据字典";

	
	//场地数据字典类型
	int LOCATION_TAGS_TYPE = 1 ;	
	
	int LOCATION_TYPE = 2 ;
	
	int LOCATION_GROUP_NUMBER_LIMIT_TYPE = 3;
	
	int LOCATION_PRICE_TYPE = 4 ;
	
	int LOCATION_USAGE_TYPE = 5 ;
	
	//场地数据字典描述
	
	String LOCATION_TAGS_DESC = "特色关键字";
	
	String LOCATION_DESC = "场地类型";
		
	String LOCATION_GROUP_NUMBER_LIMIT_DESC = "可容纳人数";	
	
	String LOCATION_PRICE_DESC = "参考报价区间";	
	
	String LOCATION_USAGE_DESC = "场地用途";	
	
	//场地数据数据集合
	
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
	
	int COMMON_ACTIVITY_PERSON_NUMBER_TYPE = 22;
	
	int COMMON_ACTIVITY_PERIOD_TYPE = 23;
	
	//通用字段字典描述
	
	String COMMON_PROVINCE_DESC = "省" ;
	
	String COMMON_CITY_DESC = "市";
	
	String COMMON_DIST_DESC = "区";
	
	String COMMON_ACTIVITY_DESC = "活动类型" ;
	
	String COMMON_ACTIVITY_PURPOSE_DESC = "团建目的";
	
	String COMMON_ACTIVITY_PERSON_NUMBER_DESC = "活动人数";
	
	String COMMON_ACTIVITY_PERIOD_DESC = "活动天数" ;
	
	int[] COMMON_DICTIONARY_TYPE_ARRAY = new int[] {
			COMMON_PROVINCE_TYPE,
			COMMON_CITY_TYPE,
			COMMON_DIST_TYPE,
			COMMON_ACTIVITY_TYPE,
			COMMON_ACTIVITY_PURPOSE_TYPE,
			COMMON_ACTIVITY_PERSON_NUMBER_TYPE,
			COMMON_ACTIVITY_PERIOD_TYPE
	};
	
	
	String[] COMMON_DICTIONARY_DESC_ARRAY = new String[] {
			COMMON_PROVINCE_DESC,
			COMMON_CITY_DESC,
			COMMON_DIST_DESC,
			COMMON_ACTIVITY_DESC,
			COMMON_ACTIVITY_PURPOSE_DESC,
			COMMON_ACTIVITY_PERSON_NUMBER_DESC,
			COMMON_ACTIVITY_PERIOD_DESC
	};
	
	//产品数据字典类型
	
	int PRODUCTION_TAGS_TYPE = 11 ;
		
	int PRODUCTION_GROUP_NUMBER_LIMIT_TYPE = 12 ;
		
	int PRODUCTION_SINGEL_PRICE_LIMIT_TYPE = 13 ;
		
	int PRODUCTION_GROUP_PRICE_LIMIT_TYPE = 14 ;
		
	//产品数据字典描述
		
	String PRODUCTION_TAGS_DESC = "产品特色关键词";
		
	String PRODUCTION_GROUP_NUMBER_LIMIT_DESC = "产品人数上限";
		
	String PRODUCTION_SINGEL_PRICE_LIMIT_DESC = "单人参考报价区间";
		
	String PRODUCTION_GROUP_PRICE_LIMIT_DESC = "整包参考报价区间";	
	
	int[] PRODUCTION_DICTIONARY_TYPE_ARRAY = new int[] {
			PRODUCTION_TAGS_TYPE,
			PRODUCTION_GROUP_NUMBER_LIMIT_TYPE,
			PRODUCTION_SINGEL_PRICE_LIMIT_TYPE,
			PRODUCTION_GROUP_PRICE_LIMIT_TYPE		
	};
	
	String[] PRODUCTION_DICTIONARY_DESC_ARRAY = new String[] {
			PRODUCTION_TAGS_DESC,
			PRODUCTION_GROUP_NUMBER_LIMIT_DESC,
			PRODUCTION_SINGEL_PRICE_LIMIT_DESC,
			PRODUCTION_GROUP_PRICE_LIMIT_DESC	
	};
	
	//案例数据字典类型
	int CASE_PLOT_PERIOD_TYPE = 15 ;
	
	int CASE_IMPL_PERIOD_TYPE = 16 ;
	
	int CASE_TAGS_TYPE = 17 ;
	
	int CSAE_TRAFFIC_TYPE = 18 ;
	
	int CSAE_FOOD_TYPE = 19 ;
	
	int CSAE_RESTURANT_TYPE = 20 ;
	
	int CASE_COMPANY_TYPE = 21 ;
	
	String CASE_PLOT_PERIOD_DESC = "策划周期";
	
	String CASE_IMPL_PERIOD_DESC = "实施周期";
	
	String CASE_TAGS_DESC = "案例特色关键词";
	
	String CSAE_TRAFFIC_DESC = "交通信息";
	
	String CSAE_FOOD_DESC = "餐饮信息";
	
	String CSAE_RESTURANT_DESC = "住宿信息";
	
	String CASE_COMPANY_DESC = "企业类型";
	
	int[] CASE_DICTIONARY_TYPE_ARRAY = new int[] {
			CASE_PLOT_PERIOD_TYPE,
			CASE_IMPL_PERIOD_TYPE,
			CASE_TAGS_TYPE,
			CSAE_TRAFFIC_TYPE,
			CSAE_FOOD_TYPE,
			CSAE_RESTURANT_TYPE,
			CASE_COMPANY_TYPE
	};
	
	String[] CASE_DICTIONARY_DESC_ARRAY = new String[] {
			CASE_PLOT_PERIOD_DESC,
			CASE_IMPL_PERIOD_DESC,
			CASE_TAGS_DESC,
			CSAE_TRAFFIC_DESC,
			CSAE_FOOD_DESC,
			CSAE_RESTURANT_DESC,
			CASE_COMPANY_DESC
	};
	
    static int getLocationIdByDesc(String desc){
		return getByDESC(desc,LOCATION_DICTIONARY_TYPE_ARRAY,LOCATION_DICTIONARY_DESC_ARRAY);
	}
	
    static String getLocationDictinaryJson() {
		return getDictionaryJson(LOCATION_DICTIONARY_TYPE_ARRAY,LOCATION_DICTIONARY_DESC_ARRAY);
	}
    
    static int getCommonIdByDesc(String desc) {
    	return getByDESC(desc,COMMON_DICTIONARY_TYPE_ARRAY,COMMON_DICTIONARY_DESC_ARRAY);
    }
    
    static String getCommonDictionaryJson() {
    	return getDictionaryJson(COMMON_DICTIONARY_TYPE_ARRAY,COMMON_DICTIONARY_DESC_ARRAY);
    }
    
    static int getProductionIdByDESC(String desc) {
    	return getByDESC(desc,PRODUCTION_DICTIONARY_TYPE_ARRAY,PRODUCTION_DICTIONARY_DESC_ARRAY);
    }
    
    static String getProductionDictionaryJson() {
    	return getDictionaryJson(PRODUCTION_DICTIONARY_TYPE_ARRAY, PRODUCTION_DICTIONARY_DESC_ARRAY);
    }
    
    static int getCaseidByDesc(String desc){
    	return getByDESC(desc, CASE_DICTIONARY_TYPE_ARRAY, CASE_DICTIONARY_DESC_ARRAY);
    }
    
    static String getCaseDictionaryJson() {
    	return getDictionaryJson(CASE_DICTIONARY_TYPE_ARRAY, CASE_DICTIONARY_DESC_ARRAY);
    }
    
    static int getByDESC(String desc,int[] idArray,String[] descArray) {
    	if(desc == null) {
			return -1;
		}else {
			int index = 0;
			for(String tmp : descArray) {
				if(tmp.equals(desc)) {
					return idArray[index];
				}
				index++;
			}
			return -1 ;
		}
    }
    
    static String getDictionaryJson(int[] idArray, String[] descArray) {
    	JSONArray dictionaryDesc = new JSONArray();
		int index = 0;
		for(int tmp : idArray) {
			JSONObject dictionaryObj = new JSONObject();
			dictionaryObj.put("id", tmp);
			dictionaryObj.put("name",descArray[index++]);
			dictionaryDesc.add(dictionaryObj);
		}
		return dictionaryDesc.toJSONString();
    }

}
