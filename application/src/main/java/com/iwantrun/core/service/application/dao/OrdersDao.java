package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer> {

}
