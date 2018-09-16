package com.iwantrun.core.service.application.dao;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.domain.Favourite;
import com.iwantrun.core.service.application.enums.TradeStatus;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    String QUERY_LOCATION_FAVOU="select favourite.case_id as caseId,favourite.case_type as caseType,favourite.user_id as userId ,"+ 
    		"location.id as id,location.activity_province_code as activityProvinceCode,location.activity_type_code  as activitytype ," + 
    		"location.group_number_limit_code as groupNumberLimitCode,location.duration," + 
    		"location.descirbe_text2 as descirbeText2,location.simulate_price_code as price,location.name as name,location.tips  " + 
    		" from biz_favourite favourite " + 
    		" left outer join biz_locations location on location.id=favourite.case_id  where 1=1 and favourite.case_type='location'";
    String COUNT_LOCATION_FAVOU="select count(*) "+
    		" from biz_favourite favourite " + 
    		" left outer join biz_locations location on location.id=favourite.case_id  where 1=1 and favourite.case_type='location' ";
    
    Function<Object[],Map<String,Object>> MAPPER_MIXED_FAVOU =
			objArray -> {
				Map<String,Object> result = new HashMap<String,Object>();
				int index = 0 ;
				result.put("caseId", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("caseType", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("userId", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("id", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("activityProvinceCode", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("activitytype",objArray[index]== null ? null: objArray[index].toString());
				index ++ ;
				result.put("groupNumberLimitCode", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("duration", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("descirbeText2", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("price", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("name", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				result.put("tips", objArray[index] == null ? null:objArray[index].toString());
				index ++ ;
				return result ;
			};	
	default List<Map<String,Object>> findAllWithFavouritePaged(Favourite vo,JPQLEnableRepository repository,int pageSize,int pageIndex){
		@SuppressWarnings("unchecked")
		String sql = QUERY_LOCATION_FAVOU;
		if( vo!= null && !StringUtils.isEmpty(vo.getUserId()) ) {
			sql =  sql.concat(" and favourite.user_id = '"+vo.getUserId()+"'");
		}
		List<Object[]> rawResult = (List<Object[]>)repository.findByNativeSqlPage(sql, pageIndex, pageSize);
		return rawResult.stream().map(MAPPER_MIXED_FAVOU).collect(Collectors.toList());
	}
	
	default Integer countAllWithFavourite(Favourite vo,JPQLEnableRepository repository) {			
		@SuppressWarnings("unchecked")
		String sql = COUNT_LOCATION_FAVOU;
		if( vo!= null && !StringUtils.isEmpty(vo.getUserId()) ) {
			sql =  sql.concat(" and favourite.user_id = "+vo.getUserId());
		}
		List<Object> rawResult = repository.findByNativeSqlPage(sql);
		Object raw = rawResult.get(0);
		return AdminApplicationConstants.MAPPER_FOR_INTEGER.apply(raw);
	}

}
