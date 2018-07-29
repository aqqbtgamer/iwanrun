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
    public List<Favourite> findAllByUserId(String userId);
    default Favourite findCase(String userId, String caseType, int caseId) {
        Favourite favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setCaseType(caseType);
        favourite.setCaseId(caseId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("caseType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("caseId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        //List<Favourite> ls = findAll(Example.of(favourite, matcher));
        //if (ls.size() <= 0) {
        //    return null;
        //}

        //return ls.get(0);
        Optional<Favourite> op = findOne(Example.of(favourite, matcher));
        List<Favourite> ls = findAll();
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }

    }

}
