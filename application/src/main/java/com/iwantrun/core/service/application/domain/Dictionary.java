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
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
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

}
