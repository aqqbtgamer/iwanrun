package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.OrderAttachments;

public interface OrderAttachmentsDao extends JpaRepository<OrderAttachments, Integer> {
	
	List<OrderAttachments> findByOrderIdAndPagePath(Integer orderId,String pagePath);

}
