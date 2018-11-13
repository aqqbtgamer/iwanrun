package com.iwantrun.core.service.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品场地关联
 */
@Entity
@Table(name = "biz_production_location_relation")
public class ProductionLocationRelation {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;// 系统主键

	@Column(name = "production_id")
	private int productionId;// 虚拟外键 产品号 biz_productions

	@Column(name = "location_id")
	private int locationId;// 虚拟外键 场地号 biz_locations

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductionId() {
		return productionId;
	}

	public void setProductionId(int productionId) {
		this.productionId = productionId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public boolean equals(Object compare) {
		if(compare instanceof ProductionLocationRelation) {
			ProductionLocationRelation compareObj = (ProductionLocationRelation)compare ;
			if(this.locationId == compareObj.getLocationId() && this.productionId == compareObj.getProductionId()) {
				return true ;
			}else {
				return false;
			}
		}else {
			return false ;
		}
	}

}
