package com.iwantrun.core.service.application.dao;

import com.iwantrun.core.service.application.domain.Favourite;
import com.iwantrun.core.service.application.domain.SiteMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteMessageDao extends JpaRepository<SiteMessage, Integer> {
    //public List<SiteMessage> findAllByFromUser(String userId);
    public List<SiteMessage> findAllBySendtoUser(String userId);

}
