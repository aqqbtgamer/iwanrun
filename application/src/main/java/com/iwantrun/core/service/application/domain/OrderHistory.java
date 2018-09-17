package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_order_history")
public class OrderHistory {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="order_id",nullable=false)
	private Integer orderId ;
	
	@Column(name="change_info")
	private String changeInfo;
	
	@Column(name="before_status_code")
	private Integer beforeStatusCode ;
	
	@Column(name="after_status_code")
	private Integer afterStatusCode;
	
	@Column(name="change_by")
	private Integer change_by;
	
	@Column(name="if_change_by_admin")
	private Integer ifChangeByAdmin;
	
	@Column(name="change_time")
	private Date changeTime ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo;
	}

	public Integer getBeforeStatusCode() {
		return beforeStatusCode;
	}

	public void setBeforeStatusCode(Integer beforeStatusCode) {
		this.beforeStatusCode = beforeStatusCode;
	}

	public Integer getAfterStatusCode() {
		return afterStatusCode;
	}

	public void setAfterStatusCode(Integer afterStatusCode) {
		this.afterStatusCode = afterStatusCode;
	}

	public Integer getChange_by() {
		return change_by;
	}

	public void setChange_by(Integer change_by) {
		this.change_by = change_by;
	}

	public Integer getIfChangeByAdmin() {
		return ifChangeByAdmin;
	}

	public void setIfChangeByAdmin(Integer ifChangeByAdmin) {
		this.ifChangeByAdmin = ifChangeByAdmin;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	
	
}
