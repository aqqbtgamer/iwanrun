package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iwantrun.core.service.application.domain.CaseTags;

public interface CaseTagsDao extends JpaRepository<CaseTags, Integer> {
	
	default List<CaseTags> findByCaseId(int caseId) {
		CaseTags tagsExample = new CaseTags();
		tagsExample.setCaseId(caseId);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("caseId", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","tagsType","tagsCode");
		return this.findAll(Example.of(tagsExample,matcher));
	}

}
