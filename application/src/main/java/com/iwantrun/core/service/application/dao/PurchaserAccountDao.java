package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.PurchaserAccount;

@Repository
public interface PurchaserAccountDao extends JpaRepository<PurchaserAccount, Integer>{

	PurchaserAccount findByLoginIdAndPassword(String loginId, String password);
	
}
