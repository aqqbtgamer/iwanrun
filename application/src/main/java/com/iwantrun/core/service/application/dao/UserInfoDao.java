package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

}
