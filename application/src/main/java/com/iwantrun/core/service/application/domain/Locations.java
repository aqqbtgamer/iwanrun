package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iwantrun.core.service.application.annotation.DictionaryField;
import com.iwantrun.core.service.utils.DictionaryConfigParams;

@Entity
@Table(name="biz_locations")
public class Locations {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="name")
	private String name ;
	
	@Column(name="activity_type_code",nullable=false)
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_ACTIVITY_TYPE,aliasField="activeTypeCodeDesc")
	private String activeTypeCode ;
	
	@Transient
	private String activeTypeCodeDesc;
	
	@Column(name="group_number_limit")	
	private String groupNumberLimit ;
	
	@Column(name="group_number_limit_code")
	@DictionaryField(name=DictionaryConfigParams.LOCATION_DICTIONARY_NAME ,usedField=DictionaryConfigParams.LOCATION_GROUP_NUMBER_LIMIT_TYPE,aliasField="groupNumberLimitCodeDesc")
	private String groupNumberLimitCode ;
	
	@Transient
	private String groupNumberLimitCodeDesc;
	
	@Column(name="activity_province_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_PROVINCE_TYPE,aliasField="activityProvinceCodeDesc")
	private String activityProvinceCode ;
	
	@Transient
	private String activityProvinceCodeDesc;
	
	@Column(name="activity_city_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_CITY_TYPE,aliasField="activityCityCodeDesc")
	private String activityCityCode ;
	
	@Transient
	private String activityCityCodeDesc;
	
	@Column(name="activity_dist_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_DIST_TYPE,aliasField="activityDistCodeDesc")
	private String activityDistCode ;
	
	@Transient
	private String activityDistCodeDesc;
	
	@Column(name="location")
	private String location ;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="status",nullable=false)
	private int status;
	
	@Column(name="shift_time")
	private Date shiftTime;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="simulate_price_code")
	@DictionaryField(name=DictionaryConfigParams.LOCATION_DICTIONARY_NAME ,usedField=DictionaryConfigParams.LOCATION_PRICE_TYPE ,aliasField="simulatePriceCodeDesc")
	private String simulatePriceCode ;	
	
	@Transient
	private String simulatePriceCodeDesc;
	
	@Column(name="group_price_code")
	private String groupPriceCode ;
	
	@Column(name="qrcode")
	private String qrCode ;
	
	@Column(name="descirbe_text1")
	private String descirbeText1;
	
	@Column(name="descirbe_text2")
	private String descirbeText2;
	
	@Column(name="descirbe_text3")
	private String descirbeText3;
	
	@Column(name="location_type_code")
	private String locationTypeCode ;
	
	@Column(name="special_tags_code")
	private String specialTagsCode ;
	
	private String[] tips;//多个特色关键字
	
	

	public String[] getTips() {
		return tips;
	}

	public void setTips(String[] tips) {
		this.tips = tips;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescirbeText1() {
		return descirbeText1;
	}

	public void setDescirbeText1(String descirbeText1) {
		this.descirbeText1 = descirbeText1;
	}

	public String getDescirbeText2() {
		return descirbeText2;
	}

	public void setDescirbeText2(String descirbeText2) {
		this.descirbeText2 = descirbeText2;
	}

	public String getDescirbeText3() {
		return descirbeText3;
	}

	public void setDescirbeText3(String descirbeText3) {
		this.descirbeText3 = descirbeText3;
	}

	public String getLocationTypeCode() {
		return locationTypeCode;
	}

	public void setLocationTypeCode(String locationTypeCode) {
		this.locationTypeCode = locationTypeCode;
	}

	public String getSpecialTagsCode() {
		return specialTagsCode;
	}

	public void setSpecialTagsCode(String specialTagsCode) {
		this.specialTagsCode = specialTagsCode;
	}

	public String getActiveTypeCodeDesc() {
		return activeTypeCodeDesc;
	}

	public void setActiveTypeCodeDesc(String activeTypeCodeDesc) {
		this.activeTypeCodeDesc = activeTypeCodeDesc;
	}

	public String getGroupNumberLimitCodeDesc() {
		return groupNumberLimitCodeDesc;
	}

	public void setGroupNumberLimitCodeDesc(String groupNumberLimitCodeDesc) {
		this.groupNumberLimitCodeDesc = groupNumberLimitCodeDesc;
	}

	public String getActivityProvinceCodeDesc() {
		return activityProvinceCodeDesc;
	}

	public void setActivityProvinceCodeDesc(String activityProvinceCodeDesc) {
		this.activityProvinceCodeDesc = activityProvinceCodeDesc;
	}

	public String getActivityCityCodeDesc() {
		return activityCityCodeDesc;
	}

	public void setActivityCityCodeDesc(String activityCityCodeDesc) {
		this.activityCityCodeDesc = activityCityCodeDesc;
	}

	public String getActivityDistCodeDesc() {
		return activityDistCodeDesc;
	}

	public void setActivityDistCodeDesc(String activityDistCodeDesc) {
		this.activityDistCodeDesc = activityDistCodeDesc;
	}

	public String getSimulatePriceCodeDesc() {
		return simulatePriceCodeDesc;
	}

	public void setSimulatePriceCodeDesc(String simulatePriceCodeDesc) {
		this.simulatePriceCodeDesc = simulatePriceCodeDesc;
	}

}
