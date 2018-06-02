package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.AuthorityRoleRelation;

public interface AuthorityRoleRelationDao extends JpaRepository<AuthorityRoleRelation, Integer> {

}
