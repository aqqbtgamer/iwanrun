package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.UserInfoAttachments;

@Repository
public interface UserInfoAttachmentsDao extends JpaRepository<UserInfoAttachments, Integer> {
	
	public List<UserInfoAttachments> findByUserInfoIdAndPagePath(Integer userInfoId, String pagePath);

}
