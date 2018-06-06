package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.dao.UserAccountDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.UserAccount;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.application.domain.UserInfoAttachments;
import com.iwantrun.core.service.application.transfer.PageDomianRequest;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.JPADBUtils;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.Md5Utils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;
import com.mysql.jdbc.StringUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class UserAccountService {
	
	@Autowired
	private Environment env ;
	
	@Autowired
	private UserAccountDao userAccountDao;

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}
	
	public List<UserAccount> findAll(){
		return this.userAccountDao.findAll();
	}
	
	public List<UserAccount> findUserByName(String username,int role){
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(username);
		userAccount.setRole(role);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", GenericPropertyMatchers.exact())
				.withMatcher("role", GenericPropertyMatchers.exact())
				.withIgnorePaths("id","password","mobileNumber","weixinAccount");
		Example<UserAccount> example = Example.of(userAccount,matcher);
		return this.userAccountDao.findAll(example);
	}

	public String findAllPaged(PageDomianRequest example) {
		int pageIndex = example.getPageIndex();
		String size = env.getProperty("common.pageSize");
		Pageable page = PageRequest.of(pageIndex, Integer.parseInt(size), Sort.Direction.ASC,"id");
		Page<UserAccount> pageResult = this.userAccountDao.findByRole(AdminApplicationConstants.ADMIN_ROLE_TYPE, page);
		return PageDataWrapUtils.page2JsonNoCopy(pageResult);
	}

	public String findByExamplePaged(PageDomianRequest example) {
		int pageIndex = example.getPageIndex();
		String size = env.getProperty("common.pageSize");
		Pageable page = PageRequest.of(pageIndex, Integer.parseInt(size), Sort.Direction.ASC,"id");
		String username = (String) example.getObj().get("username");
		String mobileNumber = (String) example.getObj().get("mobileNumber");
		UserAccount query = new UserAccount();
		if(!StringUtils.isNullOrEmpty(username)) {
			query.setUsername(username);
		}
		if(!StringUtils.isNullOrEmpty(mobileNumber)) {
			query.setMobileNumber(mobileNumber);
		}
		query.setRole(AdminApplicationConstants.ADMIN_ROLE_TYPE);
		Page<UserAccount> pageResult = this.userAccountDao.findAll(JPADBUtils.generateSpecificationFromExample(query, new String[] {
				"like,username,and",
				"like,mobileNumber,and",
				"=,role,and"
		}),page);
		return PageDataWrapUtils.page2JsonNoCopy(pageResult);
	}

	@Transactional(rollbackFor=Exception.class)
	public String addUserAccount(JSONObject obj) {
		UserAccount account = new UserAccount();
		if(obj != null) {
			account.setMobileNumber(obj.getAsString("mobileNumber"));
			account.setPassword(Md5Utils.generate(obj.getAsString("password")));
			account.setRole(AdminApplicationConstants.ADMIN_ROLE_TYPE);
			account.setUsername(obj.getAsString("username"));
			account.setWeixinAccount(obj.getAsString("weixinAccount"));
			//exam username not taken 
			List<UserAccount> userAccountList = this.userAccountDao.findByUsernameAndRole(account.getUsername(), account.getRole());
			if(userAccountList != null && userAccountList.size() > 0) {
				SimpleMessageBody result = new SimpleMessageBody();
				result.setSuccessful(false);
				result.setDescription("已存在相同用户名管理员");
				return JSONUtils.objToJSON(result);
			}
			this.userAccountDao.save(account);
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("添加成功");
		return JSONUtils.objToJSON(result);
	}

	public String get(JSONObject obj) {
		String idStr = obj.getAsString("id");
		Integer id = Integer.parseInt(idStr);
		Optional<UserAccount> accountOp = this.userAccountDao.findById(id);
		if(accountOp.get() != null) {
			return JSONValue.toJSONString(accountOp.get());
		}else {
			return null;
		}
	}

	@Transactional(rollbackFor=Exception.class)
	public String modify(String idStr, Map<String, Object> paramsMap) {
		Integer id = Integer.parseInt(idStr);
		Optional<UserAccount> accountOp = userAccountDao.findById(id);
		if(accountOp.get() != null) {
			UserAccount account = accountOp.get();
			if(paramsMap.get("mobileNumber") != null) {
				account.setMobileNumber(paramsMap.get("mobileNumber").toString());
			}
			if(paramsMap.get("weixinAccount") != null) {
				account.setWeixinAccount(paramsMap.get("weixinAccount").toString());
			}
			if(paramsMap.get("password") != null) {
				String password = paramsMap.get("password").toString();
				account.setPassword(Md5Utils.generate(password));
			}
			userAccountDao.save(account);
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("修改成功");
		return JSONUtils.objToJSON(result);
	}

	@Transactional(rollbackFor = Exception.class)
	public String delete(JSONObject requestObj) {
		if (requestObj != null) {
			if (requestObj.getAsString("id") != null) {
				deleteSingle(requestObj.getAsString("id"));
			}
			if (requestObj.get("id[]") != null) {
				JSONArray idList = (JSONArray) requestObj.get("id[]");
				for (int i = 0; i < idList.size(); i++) {
					String id = idList.get(i).toString();
					deleteSingle(id);
				}
			}
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("删除成功");
		return JSONUtils.objToJSON(result);
	}

	public void deleteSingle(String idStr) {
		Integer id = Integer.parseInt(idStr);
		Optional<UserAccount> accountOp = this.userAccountDao.findById(id);
		if (accountOp.get() != null) {
			this.userAccountDao.delete(accountOp.get());
		}

	}
	
}
