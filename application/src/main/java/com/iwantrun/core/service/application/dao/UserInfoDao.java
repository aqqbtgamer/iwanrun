package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
	
	public List<UserInfo> findByLoginInfoId(Integer loginInfoId);

}
