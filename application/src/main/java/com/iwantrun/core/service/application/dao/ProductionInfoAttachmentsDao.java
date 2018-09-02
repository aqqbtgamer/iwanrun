package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.ProductionInfoAttachments;

@Repository
public interface ProductionInfoAttachmentsDao extends JpaRepository<ProductionInfoAttachments, Integer>{
	default List<ProductionInfoAttachments> findByProductionId(Integer productionId) {
		ProductionInfoAttachments attachExample = new ProductionInfoAttachments();
		attachExample.setProductionId(productionId);
		ExampleMatcher matcher1 = ExampleMatcher.matching()
				.withMatcher("production_id", GenericPropertyMatchers.exact())
				.withIgnorePaths("id", "fileName", "pagePath", "fileSnapShot", "filePath");
		List<ProductionInfoAttachments> listAttch = this.findAll(Example.of(attachExample, matcher1));
		return listAttch;
	}
}
