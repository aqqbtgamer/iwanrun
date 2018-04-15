package com.iwantrun.core.service.application.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.ProductionInfo;

@Repository
public interface ProductionInfoDao extends JpaRepository<ProductionInfo, Integer> {

}
