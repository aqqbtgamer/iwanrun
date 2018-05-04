package com.iwantrun.core.service.application.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "biz_production_attachements")
public class ProductionInfoAttachments {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;// int primary key AUTO_INCREMENT comment '系统主键',
	@Column(name = "production_id", nullable=false)
	private Integer productionId;// int not null comment '附件所属产品号 虚拟外键 关联biz_cases',
	@Column(name = "file_name")
	private String fileName;// varchar(256) comment '文件名',
	@Column(name = "file_path")
	private String filePath;// varchar(256) comment '文件保存路径',
	@Column(name = "file_snapshot")
	private Blob fileSnapshot;// BLOB comment '文件快照'

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
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

	public Blob getFileSnapshot() {
		return fileSnapshot;
	}

	public void setFileSnapshot(Blob fileSnapshot) {
		this.fileSnapshot = fileSnapshot;
	}
}
