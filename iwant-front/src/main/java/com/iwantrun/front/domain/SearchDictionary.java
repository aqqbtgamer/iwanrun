package com.iwantrun.front.domain;

import java.util.List;

public class SearchDictionary {
	
	private String pageIndex;
	
	private List<Dictionary> activityProvinceCode;//地区
	
	private List<Dictionary> activitytype;//活动类型
	
	private List<Dictionary> companytype;//企业类型
	
	private List<Dictionary> personNum;//人数
	
	private List<Dictionary> duration;//天数
	
	
	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<Dictionary> getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(List<Dictionary> activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public List<Dictionary> getActivitytype() {
		return activitytype;
	}

	public void setActivitytype(List<Dictionary> activitytype) {
		this.activitytype = activitytype;
	}

	public List<Dictionary> getCompanytype() {
		return companytype;
	}

	public void setCompanytype(List<Dictionary> companytype) {
		this.companytype = companytype;
	}

	public List<Dictionary> getPersonNum() {
		return personNum;
	}

	public void setPersonNum(List<Dictionary> personNum) {
		this.personNum = personNum;
	}

	public List<Dictionary> getDuration() {
		return duration;
	}

	public void setDuration(List<Dictionary> duration) {
		this.duration = duration;
	}
	
	
	
	
	
	

}
