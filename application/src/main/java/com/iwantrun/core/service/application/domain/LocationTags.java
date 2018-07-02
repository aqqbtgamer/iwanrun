package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iwantrun.core.service.application.annotation.DictionaryField;
import com.iwantrun.core.service.utils.DictionaryConfigParams;

@Entity
@Table(name="biz_location_tags")
public class LocationTags {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="location_id",nullable=false)
	private Integer locationId ;
	
	@Column(name="tags_type")	
	private Integer tagsType ;
	
	@Column(name="tags_code")
	@DictionaryField(name=DictionaryConfigParams.LOCATION_DICTIONARY_NAME ,usedField=DictionaryConfigParams.LOCATION_TAGS_TYPE)
	private Integer tagsCode ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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
