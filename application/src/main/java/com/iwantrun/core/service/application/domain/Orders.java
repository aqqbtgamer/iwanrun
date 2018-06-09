package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String companyTypeId;
	
	@Column(name="contract")
	private String contract;
	
	@Column(name="contract_mobile")
	private String contractMobile;
	
	@Column(name="group_number_code")
	private Integer groupNumberCode;
	
	@Column(name="activity_code")
	private Integer activity_code;
	
	@Column(name="activity_during_code")
	private Integer activityDuringCode;
	
	@Column(name="activity_start")
	private Date activityStart;
	
	@Column(name="activity_end")
	private Date activityEnd;
	
	@Column(name="activity_province_code")
	private Integer activityProvinceCode;
	
	@Column(name="activity_city_code")
	private Integer activityCityCode ;
	
	@Column(name="activity_dist_code")
	private Integer activityDistCode ;
	
	@Column(name="order_simulate_price_code")
	private Integer orderSimulatePriceCode ;
	
	@Column(name="order_group_price_code")
	private Integer orderGroupPriceCode;

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

	public String getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(String companyTypeId) {
		this.companyTypeId = companyTypeId;
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
	
	
	
	
	
	
	
	
	
	
}
