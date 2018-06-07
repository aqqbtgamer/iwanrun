package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderMessage;

public interface OrderMessageDao extends JpaRepository<OrderMessage, Integer> {

}
