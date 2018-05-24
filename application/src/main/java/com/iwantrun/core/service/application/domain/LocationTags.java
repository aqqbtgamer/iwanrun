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
	private int locationId ;
	
	@Column(name="tags_type")	
	private int tagsType ;
	
	@Column(name="tags_code")
	@DictionaryField(name=DictionaryConfigParams.LOCATION_DICTIONARY_NAME ,usedField=DictionaryConfigParams.LOCATION_TAGS_TYPE)
	private int tagsCode ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
