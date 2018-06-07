package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderHistory;

public interface OrderHistoryDao extends JpaRepository<OrderHistory, Integer> {

}
