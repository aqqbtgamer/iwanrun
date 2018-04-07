package com.iwantrun.core.service.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.UserAccountDao;
import com.iwantrun.core.service.application.domain.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	private UserAccountDao userAccountDao;

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}
	
	public List<UserAccount> findAll(){
		return this.userAccountDao.findAll();
	}
	
}
