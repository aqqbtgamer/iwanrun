package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderProductionRelation;

public interface OrderProductionRelationDao extends JpaRepository<OrderProductionRelation, Integer> {

}
