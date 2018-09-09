package com.iwantrun.core.service.application.dao;

import com.iwantrun.core.service.application.domain.SiteMessage;
import com.iwantrun.core.service.application.domain.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeStatusDao extends JpaRepository<TradeStatus, Integer> {
}
