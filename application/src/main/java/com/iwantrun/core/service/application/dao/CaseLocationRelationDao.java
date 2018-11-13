package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CaseLocationRelation;

public interface CaseLocationRelationDao extends JpaRepository<CaseLocationRelation, Integer> {
	
	List<CaseLocationRelation> getByCaseId(Integer caseId);
	
	List<CaseLocationRelation> getByLocationId(Integer locationId);
	
	List<CaseLocationRelation> getByLocationIdAndCaseId(Integer locationId,Integer caseId);
	
	List<CaseLocationRelation> getByCaseIdAndLocationId(Integer caseId,Integer locationId);
	
}
