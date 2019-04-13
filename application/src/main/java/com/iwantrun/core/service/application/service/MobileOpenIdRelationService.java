package com.iwantrun.core.service.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.MobileOpenIdRelationDao;
import com.iwantrun.core.service.application.domain.MobileOpenIdRelation;

@Service
public class MobileOpenIdRelationService {
	
	@Autowired
	private MobileOpenIdRelationDao mOpenIdDao ;
	
	public List<MobileOpenIdRelation> findByMobileOpenId(String openId){
		return mOpenIdDao.findByMobileOpenId(openId);
	}

}
