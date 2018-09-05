package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CastPosition;

public interface CastPositionDao extends JpaRepository<CastPosition, Integer> {

}
