package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_order_loaction_relation")
public class OrderLocationRelation {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="order_id",nullable=false)
	private Integer orderId;
	
	@Column(name="location_id",nullable=false)
	private Integer locatioId;
	
	@Column(name="location_case",nullable=false)
	private String locatioCase;

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

	public Integer getLocatioId() {
		return locatioId;
	}

	public void setLocatioId(Integer locatioId) {
		this.locatioId = locatioId;
	}

	public String getLocatioCase() {
		return locatioCase;
	}

	public void setLocatioCase(String locatioCase) {
		this.locatioCase = locatioCase;
	}
	
	
}
