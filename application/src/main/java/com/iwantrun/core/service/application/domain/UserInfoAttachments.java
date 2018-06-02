package com.iwantrun.core.service.application.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_user_info_attch")
public class UserInfoAttachments {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="user_info_id",nullable=false)
	private int userInfoId ;
	
	@Column(name="fileName")
	private String fileName ;
	
	@Column(name="filePath")
	private String filePath ;
	
	@Column(name="page_path")
	private String pagePath ;
	

	@Column(name="file_snapshot")
	private Blob fileSnapShot;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserInfoId() {
		return userInfoId;
	}


	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getPagePath() {
		return pagePath;
	}


	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}


	public Blob getFileSnapShot() {
		return fileSnapShot;
	}


	public void setFileSnapShot(Blob fileSnapShot) {
		this.fileSnapShot = fileSnapShot;
	}
	
	

}
