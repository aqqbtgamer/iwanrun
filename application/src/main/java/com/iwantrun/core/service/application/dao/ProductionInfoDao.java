package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.ProductionInfo;

@Repository
public interface ProductionInfoDao
		extends JpaRepository<ProductionInfo, Integer>, JpaSpecificationExecutor<ProductionInfo> {
	
	@Modifying
	@Query("update ProductionInfo d set d.status = ?1 where d.id = ?2")
	int updateStatusById(int status, int id);
	
}
