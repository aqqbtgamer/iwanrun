package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iwantrun.core.service.application.enums.VerifyStatus;

@Entity
@Table(name="sys_user_info")
public class UserInfo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // '系统主键'
	
	@Column(name="login_info_id",nullable=false)
	private Integer loginInfoId ;
	
	@Column(name="name",nullable=false)
	private String name ;
	
	@Column(name="gender")
	private Integer gender;
	
	@Column(name="contract_mobile")
	private String contractMobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="company_type_id")
	private Integer companyTypeId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_size_id")
	private Integer companySizeId;
	
	@Column(name="company_credential_phote")
	private String companyCredentialPhote;
	
	@Column(name="verified")
	private Integer verified ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Integer loginInfoId) {
		this.loginInfoId = loginInfoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getContractMobile() {
		return contractMobile;
	}

	public void setContractMobile(String contractMobile) {
		this.contractMobile = contractMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanySizeId() {
		return companySizeId;
	}

	public void setCompanySizeId(Integer companySizeId) {
		this.companySizeId = companySizeId;
	}

	public String getCompanyCredentialPhote() {
		return companyCredentialPhote;
	}

	public void setCompanyCredentialPhote(String companyCredentialPhote) {
		this.companyCredentialPhote = companyCredentialPhote;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}
	
	public String getVerifiedString() {
		VerifyStatus verify = VerifyStatus.matchById(this.verified);
		if(verify != null) {
			return verify.getName();
		}else {
			return null;
		}
	}
	
}
