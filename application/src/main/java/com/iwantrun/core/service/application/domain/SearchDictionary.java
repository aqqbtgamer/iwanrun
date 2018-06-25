package com.iwantrun.core.service.application.domain;

public class SearchDictionary {
	
	private String pageIndex;
	
	private String[] activityProvinceCode;//地区
	
	private String[] activitytype;//活动类型
	
	private String[] companytype;//企业类型
	
	private String[] personNum;//人数
	
	private Integer[] duration;//天数
	
	private String[] locationTypeCode;//// 场地类型
	
	private String[] specialTagsCode;//特色关键词
	
	private Integer[] orderSimulatePriceCode;// 订单人均参考报价范围

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String[] getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(String[] activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public String[] getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(String[] activitytype) {
		this.activitytype = activitytype;
	}

	public String[] getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String[] companytype) {
		this.companytype = companytype;
	}

	public String[] getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String[] personNum) {
		this.personNum = personNum;
	}

	public Integer[] getDuration() {
		return duration;
	}

	public void setDuration(Integer[] duration) {
		this.duration = duration;
	}

	public String[] getLocationTypeCode() {
		return locationTypeCode;
	}

	public void setLocationTypeCode(String[] locationTypeCode) {
		this.locationTypeCode = locationTypeCode;
	}

	public String[] getSpecialTagsCode() {
		return specialTagsCode;
	}

	public void setSpecialTagsCode(String[] specialTagsCode) {
		this.specialTagsCode = specialTagsCode;
	}

	public Integer[] getOrderSimulatePriceCode() {
		return orderSimulatePriceCode;
	}

	public void setOrderSimulatePriceCode(Integer[] orderSimulatePriceCode) {
		this.orderSimulatePriceCode = orderSimulatePriceCode;
	}

	
	
	
	
	

}
