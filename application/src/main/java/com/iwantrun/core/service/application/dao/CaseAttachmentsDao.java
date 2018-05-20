package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CaseAttachments;

public interface CaseAttachmentsDao extends JpaRepository<CaseAttachments, Integer> {

	default List<CaseAttachments> findByCaseId(int caseId){
		CaseAttachments attachExample = new CaseAttachments();
		attachExample.setCaseId(caseId);
		ExampleMatcher matcher1 = ExampleMatcher.matching()
				.withMatcher("location_id", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","fileName","pagePath","fileSnapShot","filePath");			
		List<CaseAttachments> listAttch = this.findAll(Example.of(attachExample,matcher1));
		return listAttch;
	}
}
