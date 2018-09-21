package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_website_coop_logo")
public class WebSiteCooperativeLogo {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url")
	private String url ;
	
	@Column(name="logo_path")
	private String logoPath ;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

}
