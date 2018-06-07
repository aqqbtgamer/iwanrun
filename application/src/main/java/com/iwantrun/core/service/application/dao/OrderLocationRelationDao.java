package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderLocationRelation;

public interface OrderLocationRelationDao extends JpaRepository<OrderLocationRelation, Integer> {

}
