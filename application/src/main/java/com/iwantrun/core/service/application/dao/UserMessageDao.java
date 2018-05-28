package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.UserMessage;

public interface UserMessageDao extends JpaRepository<UserMessage, Integer> {

}
