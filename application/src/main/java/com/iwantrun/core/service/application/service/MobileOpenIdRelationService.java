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
	
	
	public boolean bindMobileNumber(String openId,String mobileNumber) {
		List<MobileOpenIdRelation> resultList = mOpenIdDao.findByMobileOpenId(openId);
		if(resultList == null || resultList.size() == 0 ) {
			MobileOpenIdRelation  relation = new MobileOpenIdRelation();
			relation.setMobileOpenId(openId);
			relation.setMobileNumber(mobileNumber);
			mOpenIdDao.save(relation);			
		}else {
			MobileOpenIdRelation bean = resultList.get(0);
			bean.setMobileNumber(mobileNumber);
			mOpenIdDao.save(bean);			
		}
		return true ;
	}

}