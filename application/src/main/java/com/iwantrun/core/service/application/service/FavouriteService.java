package com.iwantrun.core.service.application.service;

import com.iwantrun.core.service.application.dao.*;
import com.iwantrun.core.service.application.domain.Cases;
import com.iwantrun.core.service.application.domain.Dictionary;
import com.iwantrun.core.service.application.domain.Favourite;
import com.iwantrun.core.service.application.domain.LocationTags;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.transfer.FavouriteCase;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.EntityDictionaryConfigUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavouriteService {
    Logger logger = LoggerFactory.getLogger(CasesService.class);

    @Autowired
    private FavouriteDao favouriteDao;

    @Autowired
    private CasesDao casesDao;

    @Autowired
    private LocationsDao locationsDao;
    @Autowired  
    private Environment env;
    @Autowired
	private JPQLEnableRepository jpqlExecute;
	@Autowired
	private LocationTagsDao tagsDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private DictionaryService dictioanaryService;

    /**
     * add favourite
     *
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
            return body;
        }

        favouriteDao.save(favourite);
        body.setSuccessful(true);
        return body;
    }

    /**
     * delete favourite
     *
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
     *
     * @param caseType
     * @return
     */
    public List<Favourite> queryFavourite(String userId, String caseType, int caseId) {
        if (caseId >= 0) {
            List<Favourite> favouriteList = new ArrayList<>();
            Favourite favourite = favouriteDao.findCase(userId, caseType, caseId);
            favouriteList.add(favourite);
            return favouriteList;
        }

        List<Favourite> result = favouriteDao.findAllByUserId(userId);
        List<Favourite> favouriteList = new ArrayList<>();
        for (Favourite favourite: result) {
            if (caseType.equals(favourite.getCaseType())) {
                favouriteList.add(favourite);
            }
        }
        return favouriteList;
            /*
        List<FavouriteCase> favouriteCaseList = new ArrayList<>();
        List<Favourite> favouriteList = favouriteDao.findAllByUserId(userId);
        favouriteList.forEach(favourite -> {
            if (!caseType.equals(favourite.getCaseType())) {
                return;
            }

            FavouriteCase favouriteCase = new FavouriteCase();
            favouriteCase.setType(caseType);
            favouriteCase.setFavouriteId(favourite.getCaseId());
            if (caseType.equals("case")) {
                Cases cases = casesDao.getOne(favourite.getCaseId());

                // FIXME: 页面如果对应数据库？
                favouriteCase.setPrice(cases.getSimulatePriceCode());
                favouriteCase.setLocation(cases.getLocation());
                favouriteCase.setImage(cases.getMainImageIcon());
                favouriteCase.setTips(cases.getTips());

                favouriteCaseList.add(favouriteCase);
            } else if (caseType.equals("loction")) {
                Locations locations = locationsDao.getOne(favourite.getCaseId());

                // FIXME: 页面如果对应数据库？
                favouriteCase.setPrice(2500);
                favouriteCase.setLocation(locations.getLocationTypeCode());
                favouriteCase.setImage(locations.getName());
                favouriteCase.setTips(locations.getTips());

                favouriteCaseList.add(favouriteCase);
            }
        });
            */
    }

    public boolean doseFavouritExists(String userId, String caseType, int caseId) {
        Favourite favourite = favouriteDao.findCase(userId, caseType, caseId);
        return favourite != null;
    }
    public String findByExample(JSONObject requestObj,String login_id){
    	String pageIndexStr = requestObj.getAsString("pageIndex");
		int pageSize = Integer.parseInt(env.getProperty("common.pageSize"));
		String type = requestObj.getAsString("type");
		Integer pageIndex = pageIndexStr == null ? 1:Integer.parseInt(pageIndexStr)+1 ;
		Pageable page =  PageRequest.of(pageIndex-1, pageSize, Sort.Direction.ASC, "id");
		Favourite vo = new Favourite();
		vo.setUserId(login_id);
		vo.setCaseType(type);
		Integer totalNum = favouriteDao.countAllWithFavourite(vo ,jpqlExecute);
		List<Map<String,Object>> resultList = favouriteDao.findAllWithFavouritePaged(vo,jpqlExecute,pageSize,pageIndex-1);
		PageImpl<Map<String,Object>> result = new PageImpl<Map<String,Object>>(resultList, page, totalNum);
//		resultMap.put("favouritList", resultJson);
		for( Map<String,Object> map : resultList) {
			String otherId = (String) map.get("id");
			List<LocationTags> listTag = tagsDao.findByLocationId(Integer.parseInt(otherId));
			if(listTag!= null && listTag.size() >0 ) {
				String[] tips =new String[listTag.size()];
				for( int i=0;i< listTag.size();i++ ) {
					Dictionary dic = dictionaryDao.findByFiledNameCode(String.valueOf(listTag.get(i).getTagsType()),"location",listTag.get(i).getTagsCode());
					tips[i]=dic.getValue();
				}
				map.put("tips", tips);
			}
		}
//		Map<String,Dictionary> dictionnaryMap = EntityDictionaryConfigUtils.getDictionaryMaping(new Locations());
//		dictioanaryService.dictionaryFilter(result.getContent(), dictionnaryMap);
		String resultJson = PageDataWrapUtils.page2JsonNoCopy(result);
		return resultJson;
    }
}