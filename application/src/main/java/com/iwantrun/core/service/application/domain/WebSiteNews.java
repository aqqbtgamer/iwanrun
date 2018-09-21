package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_website_news")
public class WebSiteNews {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="news_content")
	private String newsContent;
	
	@Column(name="news_date")
	private Date newsDate ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newDate) {
		this.newsDate = newDate;
	}
}
