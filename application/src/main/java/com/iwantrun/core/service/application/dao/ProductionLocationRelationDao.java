package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.ProductionLocationRelation;

@Repository
public interface ProductionLocationRelationDao extends JpaRepository<ProductionLocationRelation, Integer>{

	ProductionLocationRelation findByProductionId(int id);
	
	List<ProductionLocationRelation> getByProductionId(int productionId);
	
	List<ProductionLocationRelation> getByLocationId(int locationId);
	
	List<ProductionLocationRelation> getByProductionIdAndLocationId(Integer productionId,Integer locationId);
	
	List<ProductionLocationRelation> getByLocationIdAndProductionId(Integer locationId,Integer productionId);
	
}
