package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.MobileOpenIdRelation;

public interface MobileOpenIdRelationDao extends JpaRepository<MobileOpenIdRelation, Integer> {
	
	List<MobileOpenIdRelation> findByMobileOpenId(String openId);

}
