package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserCollections;

public interface UserCollectionsDao extends JpaRepository<UserCollections, Integer> {

}
