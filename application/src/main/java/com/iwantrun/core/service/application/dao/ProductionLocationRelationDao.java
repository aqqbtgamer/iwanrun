package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.ProductionLocationRelation;

@Repository
public interface ProductionLocationRelationDao extends JpaRepository<ProductionLocationRelation, Integer>{

	ProductionLocationRelation findByProductionId(int id);

}
