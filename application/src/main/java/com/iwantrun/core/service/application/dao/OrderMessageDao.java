package com.iwantrun.core.service.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderMessage;

public interface OrderMessageDao extends JpaRepository<OrderMessage, Integer> {
	
	Page<OrderMessage> findByOrderId(Integer orderId , Pageable page);

}
