package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iwantrun.core.service.application.domain.Locations;

public interface LocationsDao  extends JpaRepository<Locations,Integer>,JpaSpecificationExecutor<Locations> {
}
