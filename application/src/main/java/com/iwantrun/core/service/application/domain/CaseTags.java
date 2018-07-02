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
	private Integer caseId ;
	
	@Column(name="tags_type")
	private Integer tagsType ;
	
	@Column(name="tags_code")
	private Integer tagsCode ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getTagsType() {
		return tagsType;
	}

	public void setTagsType(Integer tagsType) {
		this.tagsType = tagsType;
	}

	public Integer getTagsCode() {
		return tagsCode;
	}

	public void setTagsCode(Integer tagsCode) {
		this.tagsCode = tagsCode;
	}
	

}
