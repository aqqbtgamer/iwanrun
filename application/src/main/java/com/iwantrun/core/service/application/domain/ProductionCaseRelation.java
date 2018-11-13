package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_production_case_relation")
public class ProductionCaseRelation {
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="case_id",nullable=false)
	private Integer caseId;
	
	@Column(name="production_id",nullable=false)
	private Integer productionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	
	public boolean equals(Object compare) {
		if(compare instanceof ProductionCaseRelation) {
			ProductionCaseRelation compareObj = (ProductionCaseRelation)compare ;
			if(this.caseId == compareObj.getCaseId() && this.productionId == compareObj.getProductionId()) {
				return true ;
			}else {
				return false;
			}
		}else {
			return false ;
		}
	}

}
