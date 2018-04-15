package com.iwantrun.core.service.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.LocationAttachments;

public interface LocationAttachmentsDao extends JpaRepository<LocationAttachments, Integer> {

}
