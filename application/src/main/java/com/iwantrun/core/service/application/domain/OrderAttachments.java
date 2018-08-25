package com.iwantrun.core.service.application.domain;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_order_attachments")
public class OrderAttachments {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="order_id",nullable=false)
	private Integer orderId ;
	
	@Column(name="file_name")
	private String fileName ;
	
	@Column(name="file_path")
	private String filePath ;
	
	@Column(name="page_path")
	private String pagePath ;
	

	@Column(name="file_snapshot")
	private Blob fileSnapShot;
	
	@Column(name="uploader")
	private Integer uploader;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="file_type")
	private String fileType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Integer getUploader() {
		return uploader;
	}

	public void setUploader(Integer uploader) {
		this.uploader = uploader;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
