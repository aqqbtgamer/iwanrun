package com.iwantrun.core.service.application.transfer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iwantrun.core.service.utils.JSONUtils;

public class PageDomianRequest {
	
	Logger logger = LoggerFactory.getLogger(PageDomianRequest.class);
	
	private int pageIndex ;
	
	private Map<String,Object> obj ;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Map<String,Object> getObj() {
		return obj;
	}

	public void setObj(Map<String,Object> obj) {
		this.obj = obj;
	}
	
	public <T> T getObjAsType(Class<T> clazz){
		String json = JSONUtils.objToJSON(obj);
		return JSONUtils.jsonToObj(json, clazz);
	}

}
