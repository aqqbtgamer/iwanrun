package com.iwantrun.core.service.application.dao;

import com.iwantrun.core.service.application.domain.Favourite;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteDao extends JpaRepository<Favourite, Integer>,JpaSpecificationExecutor<Favourite> {
    public List<Favourite> findAllByUserId(int userId);
    default Favourite findCase(int userId, String caseType, int caseId) {
        Favourite favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setCaseType(caseType);
        favourite.setCaseId(caseId);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("case_type", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("case_id", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("user_id", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        Optional<Favourite> op = findOne(Example.of(favourite, matcher));
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }

    }

}
