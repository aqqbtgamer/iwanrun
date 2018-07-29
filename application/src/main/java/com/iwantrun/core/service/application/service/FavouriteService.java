package com.iwantrun.core.service.application.service;

import com.iwantrun.core.service.application.dao.*;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Favourite;
import com.iwantrun.core.service.application.transfer.FavouriteCase;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteService {
    Logger logger = LoggerFactory.getLogger(CasesService.class);

    @Autowired
    private FavouriteDao favouriteDao;
    @Autowired
    private CasesDao casesDao;

    /**
     * add favourite
     * @param favourite
     * @return
     */
    public SimpleMessageBody addFavourite(Favourite favourite) {
        SimpleMessageBody body = new SimpleMessageBody();
        body.setSuccessful(false);

        Favourite favourite_old = favouriteDao.findCase(favourite.getUserId(), favourite.getCaseType(), favourite.getCaseId());
        if (null != favourite_old) {
            body.setDescription("Error: exists " + favourite_old.toString());
            logger.error("exists {}", favourite_old);
            return null;
        }

        favouriteDao.save(favourite);
        body.setSuccessful(true);
        return body;
    }

    /**
     * delete favourite
     * @param favourite
     * @return
     */
    public SimpleMessageBody delFavourite(Favourite favourite) {
        SimpleMessageBody body = new SimpleMessageBody();
        body.setSuccessful(false);
        favourite = favouriteDao.findCase(favourite.getUserId(), favourite.getCaseType(), favourite.getCaseId());
        if (null == favourite) {
            return null;
        }
        favouriteDao.delete(favourite);
        body.setSuccessful(true);
        return body;
    }

    /**
     * delete favourite
     * @param caseType
     * @return
     */
    public List<FavouriteCase> queryFavouriteCase(String userId, String caseType) {
        List<FavouriteCase> favouriteCaseList = new ArrayList<>();
        List<Favourite> favouriteList = favouriteDao.findAllByUserId(userId);
        favouriteList.forEach(favourite -> {
            if ("case".equals(caseType)) {
                FavouriteCase favouriteCase = new FavouriteCase();
                Cases cases = casesDao.getOne(favourite.getCaseId());

                if (caseType.equals(favourite.getCaseType())) {
                    // FIXME: 页面如果对应数据库？
                    favouriteCase.setType(caseType);
                    favouriteCase.setFavouriteId(favourite.getCaseId());
                    favouriteCase.setPrice(cases.getSimulatePriceCode());
                    favouriteCase.setLocation(cases.getLocation());
                    favouriteCase.setImage(cases.getMainImageIcon());
                    favouriteCase.setTips(cases.getTips());

                    favouriteCaseList.add(favouriteCase);
                }
            }
        });
        return favouriteCaseList;
    }
}
