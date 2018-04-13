package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.Dictionary;

public interface DictionaryDao extends JpaRepository<Dictionary, Integer> {

}
