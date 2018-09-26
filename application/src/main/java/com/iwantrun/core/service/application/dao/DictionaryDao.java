package com.iwantrun.core.service.application.dao;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.Dictionary;
@Repository
public interface DictionaryDao extends JpaRepository<Dictionary, Integer>,JpaSpecificationExecutor<Dictionary> {
	
	default Dictionary findByFiledNameCode(String filedId,String name,int code) {
		Dictionary example = new Dictionary();
		example.setUsed_field(filedId);
		example.setName(name);
		example.setCode(code);
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("used_field", GenericPropertyMatchers.exact())
				.withMatcher("name", GenericPropertyMatchers.exact())
				.withMatcher("code", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","display_code","display_value","value","assignTo");
		 Optional<Dictionary> op = findOne(Example.of(example,matcher));
		 if(op.isPresent()) {
			 return op.get();
		 }else {
			 return null;
		 }
				
	}

	Optional<Dictionary> findByCode(int code);

}
