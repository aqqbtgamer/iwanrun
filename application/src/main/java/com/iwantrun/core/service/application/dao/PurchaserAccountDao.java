package com.iwantrun.core.service.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.transfer.MixedUserResponse;
import com.mysql.jdbc.StringUtils;

@Repository
public interface PurchaserAccountDao extends JpaRepository<PurchaserAccount, Integer>{

	PurchaserAccount findByLoginIdAndPassword(String loginId, String password);
	
	List<PurchaserAccount> findByMobileNumber(String mobileNumber);
	
	String QUERY_PURCHASE_USER_JPQL = "select new com.iwantrun.core.service.application.transfer.MixedUserResponse(login,info,role) from"
			+ " PurchaserAccount login left join UserInfo info on login.id = info.loginInfoId left join UserRole role on login.sysRoleId = role.id where 1=1 " ;
	
	
	String COUNT_PURCHASE_USER_JPQL = "select count(login.id) from"
			+ " PurchaserAccount login left join UserInfo info on login.id = info.loginInfoId left join UserRole role on login.sysRoleId = role.id where 1=1 " ;
	
	String SORE_BY = "order by login.id asc ";
	
	default List<MixedUserResponse> findByMutipleParams(String loginId ,String name,Integer role,String mobileNumber,JPQLEnableRepository repository,int pageSize,int pageIndex ) {
		List<MixedUserResponse> resultList = new ArrayList<MixedUserResponse>();
		String queryJPQL = QUERY_PURCHASE_USER_JPQL ;
		if(!StringUtils.isNullOrEmpty(loginId)) {
			queryJPQL = queryJPQL.concat(" and login.loginId = '"+loginId.toString()+"'");
		}
		if(role != null) {
			queryJPQL = queryJPQL.concat(" and login.sysRoleId ="+role.toString()+"");
		}
		if(!StringUtils.isNullOrEmpty(mobileNumber)) {
			queryJPQL = queryJPQL.concat(" and login.mobileNumber like '%"+mobileNumber+"%'");
		}
		if(!StringUtils.isNullOrEmpty(name)) {
			queryJPQL = queryJPQL.concat(" and info.name like '%"+name+"%'");
		}
		queryJPQL = queryJPQL.concat(SORE_BY);
		resultList = repository.findByJPQLPage(queryJPQL, MixedUserResponse.class, pageIndex, pageSize);
		return resultList;
	}
	
	default Long countByMutipleParams(String loginId ,String name,Integer role,String mobileNumber,JPQLEnableRepository repository) {
		String queryJPQL = COUNT_PURCHASE_USER_JPQL ;
		if(!StringUtils.isNullOrEmpty(loginId)) {
			queryJPQL = queryJPQL.concat(" and login.loginId = '"+loginId.toString()+"'");
		}
		if(role != null) {
			queryJPQL = queryJPQL.concat(" and login.sysRoleId ="+role.toString()+"");
		}
		if(!StringUtils.isNullOrEmpty(mobileNumber)) {
			queryJPQL = queryJPQL.concat(" and login.mobileNumber like '%"+mobileNumber+"%'");
		}
		if(!StringUtils.isNullOrEmpty(name)) {
			queryJPQL = queryJPQL.concat(" and info.name like '%"+name+"%'");
		}
		return repository.findOneJPQL(queryJPQL, Long.class);
	}
}
