package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_user_collctions")
public class UserCollections {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // '系统主键'
	
	@Column(name="collction_type",nullable=false)
	private Integer collectionType ;
	
	@Column(name="related_id",nullable=false)
	private Integer relatedId;
	
	@Column(name="login_info_id")
	private Integer loginInfoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}

	public Integer getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Integer relatedId) {
		this.relatedId = relatedId;
	}

	public Integer getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Integer loginInfoId) {
		this.loginInfoId = loginInfoId;
	}
	
}
