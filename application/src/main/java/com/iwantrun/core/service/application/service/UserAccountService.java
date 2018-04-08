package com.iwantrun.core.service.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
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
	
	public List<UserAccount> findUserByName(String username,int role){
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(username);
		userAccount.setRole(role);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", GenericPropertyMatchers.exact())
				.withMatcher("role", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","password","mobileNumber","weixinAccount");
		Example<UserAccount> example = Example.of(userAccount,matcher);
		return this.userAccountDao.findAll(example);
	}
	
}
