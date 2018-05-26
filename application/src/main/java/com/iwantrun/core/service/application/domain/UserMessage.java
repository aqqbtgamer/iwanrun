package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_user_message")
public class UserMessage {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // '系统主键'
	
	@Column(name="login_info_id" ,nullable=false)
	private Integer loginInfoId ;
	
	@Column(name="message_text" ,nullable=false)
	private String messageText;
	
	@Column(name="message_type",nullable=false)
	private Integer messageType;
	
	@Column(name="createTime")
	private Date createTime;

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

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
