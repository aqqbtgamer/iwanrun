package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CaseLocationRelation;

public interface CaseLocationRelationDao extends JpaRepository<CaseLocationRelation, Integer> {

}
