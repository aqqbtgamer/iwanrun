package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserAccount;

public interface  UserAccountDao extends JpaRepository<UserAccount,Integer> {

}
