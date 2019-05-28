package com.iwantrun.core.service.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.CasesDao;
import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.dao.ProductionInfoDao;
import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.dao.WishCartDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.WishCart;
import com.iwantrun.core.service.application.enums.EntityType;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONObject;

@Service
public class WishCartService {
	
	@Autowired
	private WishCartDao dao ;
	
	@Autowired
	private PurchaserAccountDao userDao;
	
	@Autowired
	private CasesDao caseDao;
	
	@Autowired
	private LocationsDao locationDao ;
	
	@Autowired
	private ProductionInfoDao productionDao ;

	public JSONObject add(String loginId, String type, int typeId) {
		JSONObject result = new JSONObject();
		result.put("success", false);	
		//verify if loginId valid
		PurchaserAccount user  = userDao.findByLoginId(loginId);
		if(user == null) {
			result.put("message", "用户不存在");
		}
		//verify type 
		EntityType entityType = EntityType.match(type);
		if(entityType == null) {
			result.put("message", "type不存在 只能是location, case和 production之一");
		}
		//verify id
		switch (entityType) {
		case Case:
			if(!caseDao.findById(typeId).isPresent()) {
				result.put("message", "id type不合法");
			}
			break;
		case Locaction:
			if(!locationDao.findById(typeId).isPresent()) {
				result.put("message", "id type不合法");
			}
			break;
		case Production:
			if(!productionDao.findById(typeId).isPresent()) {
				result.put("message", "id type不合法");
			}
			break;
		default:
			break;			
		}
		//all verified 
		WishCart cart = new WishCart();
		cart.setLoginId(loginId);
		cart.setType(entityType);
		cart.setTypeId(typeId);
		dao.saveAndFlush(cart);
		result.put("success", true);
		result.put("message", cart.getId());
		return result;
	}
	
	
	public JSONObject delete(int id) {
		JSONObject result = new JSONObject();
		dao.deleteById(id);
		result.put("success", true);
		return result;		
	}
	
	public String  findById(int id) {
		Optional<WishCart> wishCartOp = dao.findById(id);
		if(wishCartOp.isPresent()) {
			return JSONUtils.objToJSON(wishCartOp.get());
		}else {
			return null;
		}
	}
	
	
	public String query(String loginId,String type,int pageIndex,int pageCount) {
		if(loginId == null) {
			return null;
		}else {
			EntityType entityType = EntityType.match(type);
			Pageable page =PageRequest.of(pageIndex, pageCount, Sort.Direction.ASC, "id") ;
			if(entityType == null) {				
				Page<WishCart> result = dao.findByLoginId(loginId, page);
				return PageDataWrapUtils.page2Json(result);
			}else {
				Page<WishCart> result = dao.findByLoginIdAndType(loginId, entityType, page);
				return PageDataWrapUtils.page2Json(result);
			}
		}
	}
	

}
