package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iwantrun.core.service.application.domain.UserAccount;

public interface  UserAccountDao extends JpaRepository<UserAccount,Integer>,JpaSpecificationExecutor<UserAccount> {
	
	public Page<UserAccount> findByRole(Integer roleId , Pageable page);
	
	public Page<UserAccount> findByUsernameLikeAndMobileNumberLike(String username,String mobileNumber,Pageable page);
	
	public Page<UserAccount> findByUsernameLike(String username,Pageable page);
	
	public Page<UserAccount> findByMobileNumberLike(String mobileNumber,Pageable page);
	
	public List<UserAccount> findByUsernameAndRole(String username,Integer roleId);

	public List<UserAccount> findByUsername(String username);
}
