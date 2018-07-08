package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.UserInfoAttachments;

@Repository
public interface UserInfoAttachmentsDao extends JpaRepository<UserInfoAttachments, Integer> {
	
	String DELETE_ATTACHMENTS_JPQL = "delete from UserInfoAttachments ucct where 1=1";

	public List<UserInfoAttachments> findByUserInfoIdAndPagePath(Integer userInfoId, String pagePath);

	@Modifying
	@Query("delete from UserInfoAttachments ucct where 1=1 and ucct.userInfoId = ?1 and ucct.pagePath = ?2")
	public int deleteByUserInfoIdAndPagePath(Integer userInfoId, String pagePath);

}
