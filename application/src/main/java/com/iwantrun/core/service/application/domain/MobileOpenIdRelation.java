package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_mopenid_relation")
public class MobileOpenIdRelation {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	@Column(name="mobileOpenId",nullable=false,length=128)
	private String mobileOpenId ;
	
	@Column(name="mobileNumber",nullable=false,length=64)
	private String mobileNumber ;
	
	@Column(name="remark",nullable=true,length=255)
	private String remark;
	
	@Column(name="loginInfoId",nullable=true,length=11)
	private Integer loginInfoId ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobileOpenId() {
		return mobileOpenId;
	}

	public void setMobileOpenId(String mobileOpenId) {
		this.mobileOpenId = mobileOpenId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Integer loginInfoId) {
		this.loginInfoId = loginInfoId;
	}
	
	

}
