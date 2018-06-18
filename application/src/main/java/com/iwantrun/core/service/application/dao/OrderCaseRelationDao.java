package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderCaseRelation;

public interface OrderCaseRelationDao extends JpaRepository<OrderCaseRelation, Integer> {

}
