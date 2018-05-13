package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author user 采购方登录账户
 */
@Entity
@Table(name = "sys_login_info")
public class PurchaserAccount {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // '系统主键'
	@Column(name = "mobile_number")
	private String mobileNumber; // '登录用手机号 不能重复'
	@Column(name = "login_id")
	private String loginId; // '登录用用户名 不能重复'
	@Column(name = "password")
	private String password; // '密码'
	@Column(name = "wec")
	private String wec; // '微信号'
	@Column(name = "ali_pay_id")
	private String aliPayId; // '支付宝账号'
	@Column(name = "third_party_id_1")
	private String thirdPartyId1; // '待扩展字段 第三方绑定号'
	@Column(name = "third_party_id_2")
	private String thirdPartyId2; // '待扩展字段 第三方绑定号'
	@Column(name = "third_party_id_3")
	private String thirdPartyId3; // '待扩展字段 第三方绑定号'
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWec() {
		return wec;
	}
	public void setWec(String wec) {
		this.wec = wec;
	}
	public String getAliPayId() {
		return aliPayId;
	}
	public void setAliPayId(String aliPayId) {
		this.aliPayId = aliPayId;
	}
	public String getThirdPartyId1() {
		return thirdPartyId1;
	}
	public void setThirdPartyId1(String thirdPartyId1) {
		this.thirdPartyId1 = thirdPartyId1;
	}
	public String getThirdPartyId2() {
		return thirdPartyId2;
	}
	public void setThirdPartyId2(String thirdPartyId2) {
		this.thirdPartyId2 = thirdPartyId2;
	}
	public String getThirdPartyId3() {
		return thirdPartyId3;
	}
	public void setThirdPartyId3(String thirdPartyId3) {
		this.thirdPartyId3 = thirdPartyId3;
	}
	
}
