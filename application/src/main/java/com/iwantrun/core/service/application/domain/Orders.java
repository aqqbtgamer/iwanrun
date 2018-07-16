package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.iwantrun.core.service.application.annotation.DictionaryField;
import com.iwantrun.core.service.application.enums.TradeStatus;
import com.iwantrun.core.service.utils.DictionaryConfigParams;

@Entity
@Table(name="biz_orders")
public class Orders {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="order_no",nullable=false,unique=true,updatable=false)
	private String orderNo ;
	
	@Column(name="create_time")
	private Date createTime; 
	
	@Column(name="modify_time")
	private Date modifyTime;
	
	@Column(name="order_status_code",length=2,nullable=false)
	private Integer orderStatusCode;
	
	@Column(name="order_adviser_id")
	private Integer orderAdviserId;
	
	@Column(name="order_owner_id")
	private Integer orderOwnerId;
	
	@Column(name="company_type_id")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME, usedField = DictionaryConfigParams.COMMON_COMPANY_TYPE)
	private String companyTypeId;
	
	@Column(name="contract")
	private String contract;
	
	@Column(name="contract_mobile")
	private String contractMobile;
	
	@Column(name="group_number_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_ACTIVITY_PERSON_NUMBER_TYPE,aliasField="groupNumber")
	private Integer groupNumberCode;
	
	@Transient
	private String groupNumber;
	
	@Column(name="activity_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_ACTIVITY_TYPE,aliasField="activitysCode")
	private Integer activity_code;
	
	@Transient
	private String activitysCode;
	
	@Column(name="activity_during_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_ACTIVITY_PERIOD_TYPE,aliasField="activityDuring")
	private Integer activityDuringCode;
	
	@Transient
	private String activityDuring;
	
	@Column(name="activity_start")
	private Date activityStart;
	
	@Column(name="activity_end")
	private Date activityEnd;
	
	@Column(name="activity_province_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_PROVINCE_TYPE,aliasField="activityProvince")
	private Integer activityProvinceCode;
	
	@Transient
	private String activityProvince;
	
	
	@Column(name="activity_city_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_CITY_TYPE,aliasField="activityCity")
	private Integer activityCityCode ;
	
	@Transient
	private String activityCity;
	
	
	@Column(name="activity_dist_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_DIST_TYPE,aliasField="activityDist")
	private Integer activityDistCode ;
	
	@Transient
	private String activityDist;
	
	@Column(name="order_simulate_price_code")
	@DictionaryField(name=DictionaryConfigParams.PRODUCTION_DICTIONARY_NAME,usedField=DictionaryConfigParams.PRODUCTION_SINGEL_PRICE_LIMIT_TYPE,aliasField="orderSimulatePrice")
	private Integer orderSimulatePriceCode ;
	
	@Transient
	private String orderSimulatePrice;
	
	@Column(name="order_group_price_code")
	private Integer orderGroupPriceCode;
	
	@Column(name="people_tag_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME,usedField=DictionaryConfigParams.COMMON_PEOPLE_TAG_TYPE,aliasField="peopleTag")
	private Integer peopleTagCode ;
	
	@Transient
	private String peopleTag;
	
	@Column(name="other_request" ,length=1024)
	private String otherRequest ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(Integer orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	
	public String getOrderStatus() {
		TradeStatus status = TradeStatus.matchById(this.orderStatusCode);
		if(status != null) {
			return status.getName();
		}else {
			return null;
		}
		
	}

	public Integer getOrderAdviserId() {
		return orderAdviserId;
	}

	public void setOrderAdviserId(Integer orderAdviserId) {
		this.orderAdviserId = orderAdviserId;
	}

	public Integer getOrderOwnerId() {
		return orderOwnerId;
	}

	public void setOrderOwnerId(Integer orderOwnerId) {
		this.orderOwnerId = orderOwnerId;
	}

	public void setCompanyTypeId(String companyTypeId) {
		this.companyTypeId = companyTypeId;
	}	

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCompanyTypeId() {
		return companyTypeId;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getContractMobile() {
		return contractMobile;
	}

	public void setContractMobile(String contractMobile) {
		this.contractMobile = contractMobile;
	}

	public Integer getGroupNumberCode() {
		return groupNumberCode;
	}

	public void setGroupNumberCode(Integer groupNumberCode) {
		this.groupNumberCode = groupNumberCode;
	}

	public Integer getActivity_code() {
		return activity_code;
	}

	public void setActivity_code(Integer activity_code) {
		this.activity_code = activity_code;
	}

	public Integer getActivityDuringCode() {
		return activityDuringCode;
	}

	public void setActivityDuringCode(Integer activityDuringCode) {
		this.activityDuringCode = activityDuringCode;
	}

	public Date getActivityStart() {
		return activityStart;
	}

	public void setActivityStart(Date activityStart) {
		this.activityStart = activityStart;
	}

	public Date getActivityEnd() {
		return activityEnd;
	}

	public void setActivityEnd(Date activityEnd) {
		this.activityEnd = activityEnd;
	}

	public Integer getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(Integer activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public Integer getActivityCityCode() {
		return activityCityCode;
	}

	public void setActivityCityCode(Integer activityCityCode) {
		this.activityCityCode = activityCityCode;
	}

	public Integer getActivityDistCode() {
		return activityDistCode;
	}

	public void setActivityDistCode(Integer activityDistCode) {
		this.activityDistCode = activityDistCode;
	}

	public Integer getOrderSimulatePriceCode() {
		return orderSimulatePriceCode;
	}

	public void setOrderSimulatePriceCode(Integer orderSimulatePriceCode) {
		this.orderSimulatePriceCode = orderSimulatePriceCode;
	}

	public Integer getOrderGroupPriceCode() {
		return orderGroupPriceCode;
	}

	public void setOrderGroupPriceCode(Integer orderGroupPriceCode) {
		this.orderGroupPriceCode = orderGroupPriceCode;
	}

	public String getActivitysCode() {
		return activitysCode;
	}

	public void setActivitysCode(String activitysCode) {
		this.activitysCode = activitysCode;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getActivityDuring() {
		return activityDuring;
	}

	public void setActivityDuring(String activityDuring) {
		this.activityDuring = activityDuring;
	}

	public String getActivityProvince() {
		return activityProvince;
	}

	public void setActivityProvince(String activityProvince) {
		this.activityProvince = activityProvince;
	}

	public String getActivityCity() {
		return activityCity;
	}

	public void setActivityCity(String activityCity) {
		this.activityCity = activityCity;
	}

	public String getActivityDist() {
		return activityDist;
	}

	public void setActivityDist(String activityDist) {
		this.activityDist = activityDist;
	}

	public String getOrderSimulatePrice() {
		return orderSimulatePrice;
	}

	public void setOrderSimulatePrice(String orderSimulatePrice) {
		this.orderSimulatePrice = orderSimulatePrice;
	}

	public Integer getPeopleTagCode() {
		return peopleTagCode;
	}

	public void setPeopleTagCode(Integer peopleTagCode) {
		this.peopleTagCode = peopleTagCode;
	}

	public String getPeopleTag() {
		return peopleTag;
	}

	public void setPeopleTag(String peopleTag) {
		this.peopleTag = peopleTag;
	}

	public String getOtherRequest() {
		return otherRequest;
	}

	public void setOtherRequest(String otherRequest) {
		this.otherRequest = otherRequest;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
