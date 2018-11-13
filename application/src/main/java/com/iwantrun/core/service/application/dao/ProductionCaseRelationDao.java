package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.ProductionCaseRelation;

public interface ProductionCaseRelationDao extends JpaRepository<ProductionCaseRelation, Integer> {
	
	public List<ProductionCaseRelation> getByProductionId(Integer productionId);
	
	public List<ProductionCaseRelation> getByCaseId(Integer caseId);
	
	public List<ProductionCaseRelation> getByCaseIdAndProductionId(Integer caseId, Integer productionId);	
    
	public List<ProductionCaseRelation> getByProductionIdAndCaseId(Integer productionId, Integer caseId);	
}
