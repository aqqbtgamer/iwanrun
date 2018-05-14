package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_dictionary")
public class Dictionary {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="used_field")
	private String used_field;
	
	@Column(name="display_code")
	private int display_code;
	
	@Column(name="display_value")
	private String display_value;
	
	@Column(name="code")
	private int code;
	
	@Column(name="value")
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsed_field() {
		return used_field;
	}

	public void setUsed_field(String used_field) {
		this.used_field = used_field;
	}

	public int getDisplay_code() {
		return display_code;
	}

	public void setDisplay_code(int display_code) {
		this.display_code = display_code;
	}

	public String getDisplay_value() {
		return display_value;
	}

	public void setDisplay_value(String display_value) {
		this.display_value = display_value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
