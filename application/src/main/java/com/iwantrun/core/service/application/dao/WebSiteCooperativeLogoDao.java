package com.iwantrun.core.service.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.WebSiteCooperativeLogo;

public interface WebSiteCooperativeLogoDao extends JpaRepository<WebSiteCooperativeLogo, Integer> {
	
	public Page<WebSiteCooperativeLogo> findByNameLike(String name ,Pageable page);

}
