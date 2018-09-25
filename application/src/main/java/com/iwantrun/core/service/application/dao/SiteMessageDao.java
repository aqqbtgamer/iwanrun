package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.domain.SiteMessage;

@Repository
public interface SiteMessageDao extends JpaRepository<SiteMessage, Integer> {

	default List<SiteMessage> findByMutipleParams(SiteMessage vo, JPQLEnableRepository jpqlExecute, Integer pageSize,
			int pageIndex) {
		List<SiteMessage> list = new ArrayList<>();
		String queryJPQL = "select sm from SiteMessage sm where 1=1 ";
		if (!StringUtils.isEmpty(vo.getIsRead())) {
			queryJPQL += " and sm.isRead = " + vo.getIsRead();
		}
		queryJPQL += " and sm.fromUser = " + vo.getFromUser() + " or sm.sendtoUser = " + vo.getSendtoUser();
		queryJPQL += " order by sm.createTime desc ";
		list = jpqlExecute.findByJPQLPage(queryJPQL, SiteMessage.class, pageIndex, pageSize);
		return list;
	}

	Long countByIsReadAndFromUserOrSendtoUser(String isRead, String fromUser, String sendtoUser);

	Long countByFromUserOrSendtoUser(String fromUser, String sendtoUser);

}
