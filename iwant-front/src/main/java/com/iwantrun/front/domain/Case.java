package com.iwantrun.front.domain;

import java.util.List;

public class Case {
	
	private List<Dictionary> location;//地区
	
	private List<Dictionary> activitytype;//活动类型
	
	private List<Dictionary> companytype;//企业类型
	
	private List<Dictionary> personNum;//人数
	
	private List<Dictionary> duration;//天数

	public List<Dictionary> getLocation() {
		return location;
	}

	public void setLocation(List<Dictionary> location) {
		this.location = location;
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
