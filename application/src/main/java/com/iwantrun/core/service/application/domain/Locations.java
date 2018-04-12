package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="biz_locations")
public class Locations {
	
	private int id ;
	
	private String name ;
	
	private String activeTypeCode ;
	
	private String groupNumberLimit ;
	
	private String groupNumberLimitCode ;
	
	private String activityProvinceCode ;
	
	private String activityCityCode ;
	
	private String activityDistCode ;
	
	private String location ;
	
	private int priority;
	
	private int status;
	
	private Date shiftTime;
	
	private String simulatePriceCode ;
	
	private String groupPriceCode ;
	
	private String qrCode ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveTypeCode() {
		return activeTypeCode;
	}

	public void setActiveTypeCode(String activeTypeCode) {
		this.activeTypeCode = activeTypeCode;
	}

	public String getGroupNumberLimit() {
		return groupNumberLimit;
	}

	public void setGroupNumberLimit(String groupNumberLimit) {
		this.groupNumberLimit = groupNumberLimit;
	}

	public String getGroupNumberLimitCode() {
		return groupNumberLimitCode;
	}

	public void setGroupNumberLimitCode(String groupNumberLimitCode) {
		this.groupNumberLimitCode = groupNumberLimitCode;
	}

	public String getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(String activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public String getActivityCityCode() {
		return activityCityCode;
	}

	public void setActivityCityCode(String activityCityCode) {
		this.activityCityCode = activityCityCode;
	}

	public String getActivityDistCode() {
		return activityDistCode;
	}

	public void setActivityDistCode(String activityDistCode) {
		this.activityDistCode = activityDistCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}

	public String getSimulatePriceCode() {
		return simulatePriceCode;
	}

	public void setSimulatePriceCode(String simulatePriceCode) {
		this.simulatePriceCode = simulatePriceCode;
	}

	public String getGroupPriceCode() {
		return groupPriceCode;
	}

	public void setGroupPriceCode(String groupPriceCode) {
		this.groupPriceCode = groupPriceCode;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

}
