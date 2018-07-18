package com.iwantrun.core.service.application.domain;

import java.util.List;

public class SearchDictionaryList {
	
	private String pageIndex;
	
	private Integer pageSize;
	
	private List<String> activityProvinceCode;//地区
	
	private List<String> activitytype;//活动类型
	
	private List<String> companytype;//企业类型
	
	private List<String> personNum;//人数
	
	private List<Integer> duration;//天数
	
	private List<String> locationTypeCode;// 场地类型
	
	private List<Integer> specialTagsCode;//特色关键词
	
	private List<Integer> orderSimulatePriceCode;// 订单人均参考报价范围
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<String> getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(List<String> activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public List<String> getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(List<String> activitytype) {
		this.activitytype = activitytype;
	}

	public List<String> getCompanytype() {
		return companytype;
	}

	public void setCompanytype(List<String> companytype) {
		this.companytype = companytype;
	}

	public List<String> getPersonNum() {
		return personNum;
	}

	public void setPersonNum(List<String> personNum) {
		this.personNum = personNum;
	}

	public List<Integer> getDuration() {
		return duration;
	}

	public void setDuration(List<Integer> duration) {
		this.duration = duration;
	}

	public List<String> getLocationTypeCode() {
		return locationTypeCode;
	}

	public void setLocationTypeCode(List<String> locationTypeCode) {
		this.locationTypeCode = locationTypeCode;
	}

	public List<Integer> getSpecialTagsCode() {
		return specialTagsCode;
	}

	public void setSpecialTagsCode(List<Integer> specialTagsCode) {
		this.specialTagsCode = specialTagsCode;
	}

	public List<Integer> getOrderSimulatePriceCode() {
		return orderSimulatePriceCode;
	}

	public void setOrderSimulatePriceCode(List<Integer> orderSimulatePriceCode) {
		this.orderSimulatePriceCode = orderSimulatePriceCode;
	}
	
	
	

}
