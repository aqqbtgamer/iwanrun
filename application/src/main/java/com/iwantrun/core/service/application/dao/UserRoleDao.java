package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

}
