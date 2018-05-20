package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_case_tags")
public class CaseTags {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="case_id",nullable=false)
	private int caseId ;
	
	@Column(name="tags_type")
	private int tagsType ;
	
	@Column(name="tags_code")
	private int tagsCode ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public int getTagsType() {
		return tagsType;
	}

	public void setTagsType(int tagsType) {
		this.tagsType = tagsType;
	}

	public int getTagsCode() {
		return tagsCode;
	}

	public void setTagsCode(int tagsCode) {
		this.tagsCode = tagsCode;
	}
	
	

}
