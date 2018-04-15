package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iwant_account")
public class UserAccount {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="username",nullable=false)
	private String username ;
	
	@Column(name="password",nullable=false)
	private String password ;
	
	@Column(name="mobile_number")
	private String mobileNumber ;
	
	@Column(name="weixin_account")
	private String weixinAccount;
	
	@Column(name="role")
	private int role ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getWeixinAccount() {
		return weixinAccount;
	}

	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
