package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_production_tags")
public class ProductionTags {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="production_id",nullable=false)
	private Integer productionId ;
	
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

	public int getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}

	public int getTagsType() {
		return tagsType;
	}

	public void setTagsType(Integer tagsType) {
		this.tagsType = tagsType;
	}

	public int getTagsCode() {
		return tagsCode;
	}

	public void setTagsCode(Integer tagsCode) {
		this.tagsCode = tagsCode;
	}
	
	

}
