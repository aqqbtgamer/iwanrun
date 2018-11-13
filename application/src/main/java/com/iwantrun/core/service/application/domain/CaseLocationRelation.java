package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_case_location_relation")
public class CaseLocationRelation {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="case_id",nullable=false)
	private Integer caseId;
	
	@Column(name="location_id",nullable=false)
	private Integer locationId;

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

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public boolean equals(Object compare) {
		if(compare instanceof CaseLocationRelation) {
			CaseLocationRelation compareObj = (CaseLocationRelation)compare ;
			if(this.locationId == compareObj.getLocationId() && this.caseId == compareObj.getCaseId()) {
				return true ;
			}else {
				return false;
			}
		}else {
			return false ;
		}
	}

}
