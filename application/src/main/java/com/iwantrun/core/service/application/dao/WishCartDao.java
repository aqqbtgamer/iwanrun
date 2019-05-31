package com.iwantrun.core.service.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.WishCart;
import com.iwantrun.core.service.application.enums.EntityType;

public interface WishCartDao extends JpaRepository<WishCart, Integer> {
	
	Page<WishCart> findByLoginId(String loginId,Pageable page);
	
	Page<WishCart> findByLoginIdAndType(String loginId,EntityType type,Pageable page);
	
	WishCart findByLoginIdAndTypeAndTypeId(String loginId,EntityType type,int typeId);

}
