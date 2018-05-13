package com.iwantrun.core.service.application.config;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.iwantrun.core.service.utils.DictionaryConfigParams;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class DictionaryPageConfig {
	
	private static final List<DictionnaryPageItemConfig> pageItems = new ArrayList<DictionnaryPageItemConfig>();
	
	static {
		//场地数据菜单配置
		DictionnaryPageItemConfig locationConfig = new DictionnaryPageItemConfig();
		locationConfig.setName(DictionaryConfigParams.LOCATION_DICTIONARY_NAME);
		locationConfig.setDesc(DictionaryConfigParams.LOCATION_DICTIONARY_DESC);
		Supplier<String> locationSupplier = () ->{
			return DictionaryConfigParams.getLocationDictinaryJson();	
		};
		Function<String,Integer> locationFunction = (desc)->{
			return DictionaryConfigParams.getLocationIdByDesc(desc);
		};
		locationConfig.setDictionaryJsonSupplier(locationSupplier);
		locationConfig.setIdbyDESFuction(locationFunction);
		pageItems.add(locationConfig);
		//产品数据菜单配置
		DictionnaryPageItemConfig productionConfig = new DictionnaryPageItemConfig();
		productionConfig.setName(DictionaryConfigParams.PRODUCTION_DICTIONARY_NAME);
		productionConfig.setDesc(DictionaryConfigParams.PRODUCTION_DICTIONARY_DESC);
		Supplier<String> produnctSupplier = () ->{
			return DictionaryConfigParams.getProductionDictionaryJson();	
		};
		Function<String,Integer> productFunction = (desc)->{
			return DictionaryConfigParams.getProductionIdByDESC(desc);
		};
		productionConfig.setDictionaryJsonSupplier(produnctSupplier);
		productionConfig.setIdbyDESFuction(productFunction);
		pageItems.add(productionConfig);
		//案例数据菜单配置
		DictionnaryPageItemConfig caseConfig = new DictionnaryPageItemConfig();
		caseConfig.setName(DictionaryConfigParams.CASE_DICTIONARY_NAME);
		caseConfig.setDesc(DictionaryConfigParams.CASE_DICTIONARY_DESC);
		Supplier<String> caseSupplier = () ->{
			return DictionaryConfigParams.getCaseDictionaryJson();
		};
		Function<String,Integer> caseFunction = (desc)->{
			return DictionaryConfigParams.getCaseidByDesc(desc);
		};
		caseConfig.setDictionaryJsonSupplier(caseSupplier);
		caseConfig.setIdbyDESFuction(caseFunction);
		pageItems.add(caseConfig);
		//通用数据菜单配置
		DictionnaryPageItemConfig commonConfig = new DictionnaryPageItemConfig();
		commonConfig.setName(DictionaryConfigParams.COMMON_DICTIONARY_NAME);
		commonConfig.setDesc(DictionaryConfigParams.COMMON_DICTIONARY_DESC);
		Supplier<String> commonSupplier = ()->{
			return DictionaryConfigParams.getCommonDictionaryJson();
		};
		Function<String,Integer> commonFunction = (desc)->{
			return DictionaryConfigParams.getCommonIdByDesc(desc);
		};
		commonConfig.setDictionaryJsonSupplier(commonSupplier);
		commonConfig.setIdbyDESFuction(commonFunction);
		pageItems.add(commonConfig);
	}

	public static List<DictionnaryPageItemConfig> getPageItems() {
		return pageItems;
	}
	
	public static String getPageConfigJson() {
		JSONArray array = new JSONArray();
		int index = 0 ;
		for(DictionnaryPageItemConfig pageItem : pageItems){
			JSONObject jObj = new JSONObject();
			jObj.put("name", pageItem.getName());
			jObj.put("index", index++);
			jObj.put("desc", pageItem.getDesc());
			jObj.put("tags", JSONValue.parse(pageItem.getDictionaryJson()));
			array.add(jObj);
		}
		return array.toJSONString();
	}
	
	public static String getPageTages(String pageName) {
		if(pageName == null) {
			return null;
		}else {
			for(DictionnaryPageItemConfig pageItem : pageItems){
				if(pageName.equals(pageItem.getName())) {
					return pageItem.getDictionaryJson();
				}
			}
			return null;
		}
	}
	
	
	
}
