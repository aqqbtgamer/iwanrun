package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserAuthority;

public interface UserAuthorityDao extends JpaRepository<UserAuthority, Integer> {

}
