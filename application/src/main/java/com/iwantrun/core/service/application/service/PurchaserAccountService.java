package com.iwantrun.core.service.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.constant.AdminApplicationConstants;
import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.dao.UserInfoAttachmentsDao;
import com.iwantrun.core.service.application.dao.UserInfoDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.domain.UserInfo;
import com.iwantrun.core.service.application.domain.UserInfoAttachments;
import com.iwantrun.core.service.application.enums.RoleType;
import com.iwantrun.core.service.application.enums.VerifyStatus;
import com.iwantrun.core.service.application.transfer.MixedUserResponse;
import com.iwantrun.core.service.application.transfer.PurchaserAccountRequest;
import com.iwantrun.core.service.application.transfer.SimpleMessageBody;
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.Md5Utils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;
import com.iwantrun.core.service.utils.SMSCodeUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class PurchaserAccountService {

	@Autowired
	private Environment environment;
	@Autowired
	private PurchaserAccountDao dao;
	@Autowired
	private UserInfoDao infoDao;
	@Autowired
	private UserInfoAttachmentsDao attachmentsDao;
	@Autowired
	private JPQLEnableRepository jpqlExecute;

	public String modifyPwd(PurchaserAccount account) {
		String loginId = account.getLoginId();
		PurchaserAccount dbAccount = dao.findByLoginId(loginId);
		if (dbAccount == null) {
			return "账号：" + loginId + "，不存在";
		}

		String md5Password = Md5Utils.generate(account.getPassword());
		dbAccount.setPassword(md5Password);
		PurchaserAccount saved = dao.save(dbAccount);
		if (saved == null) {
			return "数据保存失败，请重试";
		}
		return null;
	}

	public String register(PurchaserAccount account) {
		String loginId = account.getLoginId();
		PurchaserAccount dbAccount = dao.findByLoginId(loginId);
		if (dbAccount != null) {
			return "账号：" + loginId + "，已存在";
		}

		String md5Password = Md5Utils.generate(account.getPassword());
		account.setPassword(md5Password);
		account.setMobileNumber(loginId);
		account.setSysRoleId(RoleType.Purchase.getId());
		account.setStatus(VerifyStatus.Not_Verified.getId());
		PurchaserAccount saved = dao.save(account);
		if (saved == null) {
			return "数据保存失败，请重试";
		}
		return null;
	}

	public String validateRegisterParam(PurchaserAccountRequest accountRequest) {

		if (accountRequest == null || accountRequest.getAccount() == null) {
			return "请输入相关数据";
		}
		String mobile = accountRequest.getAccount().getLoginId();
		if (StringUtils.isEmpty(mobile)) {
			return "请输入账号";
		}
		String smsCode = accountRequest.getSmsCode();
		if (StringUtils.isEmpty(smsCode)) {
			return "短信验证码不能为空";
		}

		String password = accountRequest.getAccount().getPassword();

		if(StringUtils.isEmpty(password)) {
			return "密码不能为空";
		}
		
		int length = password.length();
		
		if(length < 8 || length > 16){
			return "密码长度必须大于等于8位，小于等于16位";
		}
		
		boolean matchered = password.matches(environment.getProperty("purchaser.account.password.regex"));

		if (!matchered) {
			return "密码格式错误，请重新输入";
		}
		return null;
	}

	public String validateLoginParam(PurchaserAccount account) {
		String loginId = account.getLoginId();
		String password = account.getPassword();
		if (StringUtils.isEmpty(loginId)) {
			return "请输入账号";
		}
		if (StringUtils.isEmpty(password)) {
			return "请输入密码";
		}

		PurchaserAccount find = dao.findByLoginId(loginId);

		if (find == null) {
			return "用户不存在";
		}

		String dbPwd = find.getPassword();

		boolean correct = Md5Utils.verify(password, dbPwd);
		if (!correct) {
			return "账号密码不匹配";
		}
		return null;
	}

	public String findPurchaseUserPaged(JSONObject obj) {
		Integer pageSize = Integer.parseInt(environment.getProperty("common.pageSize"));
		String index = obj.getAsString("pageIndex");
		int pageIndex = index == null ? 1 : Integer.parseInt(index) + 1;
		String loginId = obj.getAsString("loginId");
		String name = obj.getAsString("name");
		String mobileNumber = obj.getAsString("mobileNumber");
		Integer role = obj.getAsNumber("role") == null ? null : obj.getAsNumber("role").intValue();
		Pageable page = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.ASC, "id");
		Long totalNum = dao.countByMutipleParams(loginId, name, role, mobileNumber, jpqlExecute);
		List<MixedUserResponse> content = dao.findByMutipleParams(loginId, name, role, mobileNumber, jpqlExecute,
				pageSize, pageIndex);
		PageImpl<MixedUserResponse> result = new PageImpl<MixedUserResponse>(content, page, totalNum);
		return PageDataWrapUtils.page2JsonNoCopy(result);
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackOn = Exception.class)
	public String addPurchaseUserAndRelated(Map<String, Object> paramsMap) {
		String mobileNumber = (String) paramsMap.get("mobileNumber");
		List<PurchaserAccount> findList = dao.findByMobileNumber(mobileNumber);
		// check mobile number not occupy
		if (findList != null && findList.size() > 0) {
			// mobileNumber already taken
			SimpleMessageBody result = new SimpleMessageBody();
			result.setSuccessful(false);
			result.setDescription("手机号已被注册请选择其他手机号");
			return JSONUtils.objToJSON(result);
		} else {
			// combine PurchaserAccount
			PurchaserAccount account = new PurchaserAccount();
			account.setMobileNumber(mobileNumber);
			account.setLoginId(mobileNumber);
			if (paramsMap.get("password") != null) {
				String password = Md5Utils.generate(paramsMap.get("password").toString());
				account.setPassword(password);
			}
			if (paramsMap.get("wec") != null) {
				account.setWec((String) paramsMap.get("wec"));
			}
			if (paramsMap.get("aliPayId") != null) {
				account.setAliPayId((String) paramsMap.get("aliPayId"));
			}
			if (paramsMap.get("role") != null) {
				account.setSysRoleId(Integer.parseInt(paramsMap.get("role").toString()));
			}
			if (paramsMap.get("thirdPartyId1") != null) {
				account.setThirdPartyId1(paramsMap.get("thirdPartyId1").toString());
			}
			if (paramsMap.get("thirdPartyId2") != null) {
				account.setThirdPartyId2(paramsMap.get("thirdPartyId2").toString());
			}
			if (paramsMap.get("thirdPartyId3") != null) {
				account.setThirdPartyId3(paramsMap.get("thirdPartyId3").toString());
			}
			dao.saveAndFlush(account);
			int accountLoginId = account.getId();
			// combine userinfo
			UserInfo userInfo = new UserInfo();
			userInfo.setLoginInfoId(accountLoginId);
			userInfo.setName(paramsMap.get("name").toString());
			List<String> genderList = (List<String>) paramsMap.get("gender[]");
			if (genderList != null) {
				userInfo.setGender(Integer.parseInt(genderList.get(0)));
			}
			if (paramsMap.get("contractMobile") != null) {
				userInfo.setContractMobile(paramsMap.get("contractMobile").toString());
			}
			if (paramsMap.get("email") != null) {
				userInfo.setEmail(paramsMap.get("email").toString());
			}
			if (paramsMap.get("companyTypeId") != null) {
				userInfo.setCompanyTypeId(Integer.parseInt(paramsMap.get("companyTypeId").toString()));
			}
			if (paramsMap.get("companySizeId") != null) {
				userInfo.setCompanySizeId(Integer.parseInt(paramsMap.get("companySizeId").toString()));
			}
			if (paramsMap.get("companyName") != null) {
				userInfo.setCompanyName(paramsMap.get("companyName").toString());
			}
			if (paramsMap.get("role") != null) {
				String role = paramsMap.get("role").toString();
				Integer roleId = Integer.parseInt(role);
				if (RoleType.Advisor.equals(RoleType.matchById(roleId))) {
					userInfo.setVerified(VerifyStatus.Verifed.getId());
				} else {
					userInfo.setVerified(VerifyStatus.Not_Verified.getId());
				}
			} else {
				userInfo.setVerified(VerifyStatus.Not_Verified.getId());
			}
			infoDao.saveAndFlush(userInfo);
			// save attachments
			if (paramsMap.get("imgManage[]") != null) {
				List<String> attchPaths = (List<String>) paramsMap.get("imgManage[]");
				List<UserInfoAttachments> attchList = new ArrayList<UserInfoAttachments>();
				for (String path : attchPaths) {
					UserInfoAttachments attach = new UserInfoAttachments();
					int fileNameIndex = path.lastIndexOf("/");
					attach.setFileName(path.substring(fileNameIndex + 1));
					attach.setFilePath(path);
					attach.setPagePath(AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
					attach.setUserInfoId(userInfo.getId());
					attchList.add(attach);
				}
				attachmentsDao.saveAll(attchList);
			}
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("添加成功");
		return JSONUtils.objToJSON(result);
	}

	public String get(Integer id) {
		Optional<PurchaserAccount> accountOp = dao.findById(id);
		if (accountOp.get() != null) {
			PurchaserAccount account = accountOp.get();
			JSONObject returnObj = new JSONObject();
			returnObj.put("purchaseAccount", JSONValue.toJSONString(account));
			List<UserInfo> userInfoList = infoDao.findByLoginInfoId(id);
			if (userInfoList != null && userInfoList.size() > 0) {
				UserInfo userInfo = userInfoList.get(0);
				returnObj.put("userInfo", JSONValue.toJSONString(userInfo));
				Integer userInfoId = userInfo.getId();
				List<UserInfoAttachments> userAttachList = attachmentsDao.findByUserInfoIdAndPagePath(userInfoId,
						AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
				returnObj.put("attachList", JSONValue.toJSONString(userAttachList));
			}
			return returnObj.toJSONString();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackOn = Exception.class)
	public String modify(String idStr, Map<String, Object> paramsMap) {
		Integer id = Integer.parseInt(idStr);
		Optional<PurchaserAccount> accountOp = dao.findById(id);
		if (accountOp.get() == null) {
			SimpleMessageBody result = new SimpleMessageBody();
			result.setSuccessful(false);
			result.setDescription("要修改的id在数据库中找不到对应的值");
			return JSONUtils.objToJSON(result);
		} else {
			PurchaserAccount account = accountOp.get();
			if (paramsMap.get("password") != null) {
				String password = Md5Utils.generate(paramsMap.get("password").toString());
				account.setPassword(password);
			}
			if (paramsMap.get("wec") != null) {
				account.setWec((String) paramsMap.get("wec"));
			}
			if (paramsMap.get("aliPayId") != null) {
				account.setAliPayId((String) paramsMap.get("aliPayId"));
			}
			if (paramsMap.get("thirdPartyId1") != null) {
				account.setThirdPartyId1(paramsMap.get("thirdPartyId1").toString());
			}
			if (paramsMap.get("thirdPartyId2") != null) {
				account.setThirdPartyId2(paramsMap.get("thirdPartyId2").toString());
			}
			if (paramsMap.get("thirdPartyId3") != null) {
				account.setThirdPartyId3(paramsMap.get("thirdPartyId3").toString());
			}
			dao.save(account);
			List<UserInfo> infoList = infoDao.findByLoginInfoId(id);
			UserInfo userInfo = null;
			if (infoList != null && infoList.size() > 0) {
				userInfo = infoList.get(0);
			} else if (paramsMap.get("name") != null) {
				userInfo = new UserInfo();
			}
			if (userInfo != null) {
				userInfo.setName(paramsMap.get("name").toString());
				List<String> genderList = (List<String>) paramsMap.get("gender[]");
				if (genderList != null) {
					userInfo.setGender(Integer.parseInt(genderList.get(0)));
				}
				if (paramsMap.get("contractMobile") != null) {
					userInfo.setContractMobile(paramsMap.get("contractMobile").toString());
				}
				if (paramsMap.get("email") != null) {
					userInfo.setEmail(paramsMap.get("email").toString());
				}
				if (paramsMap.get("companyTypeId") != null) {
					userInfo.setCompanyTypeId(Integer.parseInt(paramsMap.get("companyTypeId").toString()));
				}
				if (paramsMap.get("companySizeId") != null) {
					userInfo.setCompanySizeId(Integer.parseInt(paramsMap.get("companySizeId").toString()));
				}
				if (paramsMap.get("companyName") != null) {
					userInfo.setCompanyName(paramsMap.get("companyName").toString());
				}
				infoDao.saveAndFlush(userInfo);
				List<UserInfoAttachments> userAttachList = attachmentsDao.findByUserInfoIdAndPagePath(userInfo.getId(),
						AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
				attachmentsDao.deleteAll(userAttachList);
				// save new attachments
				if (paramsMap.get("imgManage[]") != null) {
					List<String> attchPaths = (List<String>) paramsMap.get("imgManage[]");
					List<UserInfoAttachments> attchList = new ArrayList<UserInfoAttachments>();
					for (String path : attchPaths) {
						UserInfoAttachments attach = new UserInfoAttachments();
						int fileNameIndex = path.lastIndexOf("/");
						attach.setFileName(path.substring(fileNameIndex + 1));
						attach.setFilePath(path);
						attach.setPagePath(AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
						attach.setUserInfoId(userInfo.getId());
						attchList.add(attach);
					}
					attachmentsDao.saveAll(attchList);
				}
			}
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("修改成功");
		return JSONUtils.objToJSON(result);
	}

	@Transactional(rollbackOn = Exception.class)
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
		Optional<PurchaserAccount> accountOp = dao.findById(id);
		if (accountOp.get() != null) {
			List<UserInfo> infoList = infoDao.findByLoginInfoId(id);
			if (infoList != null && infoList.size() > 0) {
				UserInfo userInfo = infoList.get(0);
				List<UserInfoAttachments> userAttachList = attachmentsDao.findByUserInfoIdAndPagePath(userInfo.getId(),
						AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
				attachmentsDao.deleteAll(userAttachList);
				infoDao.delete(userInfo);
			}
			dao.deleteById(id);
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public String apply(String idStr) {
		Integer id = Integer.parseInt(idStr);
		Optional<PurchaserAccount> accountOp = dao.findById(id);
		if (accountOp.get() != null) {
			List<UserInfo> infoList = infoDao.findByLoginInfoId(id);
			if (infoList != null && infoList.size() > 0) {
				UserInfo userInfo = infoList.get(0);
				userInfo.setVerified(VerifyStatus.Verifed.getId());
				infoDao.save(userInfo);
			}
		}
		SimpleMessageBody result = new SimpleMessageBody();
		result.setSuccessful(true);
		result.setDescription("审批成功");
		return JSONUtils.objToJSON(result);
	}

	public String validateLoginParam(PurchaserAccountRequest accountRequest) {
		PurchaserAccount account = accountRequest.getAccount();
		boolean messageLogin = accountRequest.isMessageLogin();
		if (messageLogin) {
			if (account == null || account.getLoginId() == null) {
				return "请输入账号";
			}
			String smsCode = accountRequest.getSmsCode();
			if (smsCode == null) {
				return "请输入短信验证码";
			}
			return null;
		} else {
			return validateLoginParam(account);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public String addAndModifyInfo(String dataJson) {
		JSONObject paramJSON = JSONUtils.jsonToObj(dataJson, JSONObject.class);
		String loginId = paramJSON.getAsString("loginId");
		PurchaserAccount dbAccount = dao.findByLoginId(loginId);
		if (dbAccount == null) {
			return "用户不存在";
		}
		String mobileNumber = paramJSON.getAsString("mobileNumber");
		if (mobileNumber != null) {
			if (!SMSCodeUtils.isMobileNum(mobileNumber)) {
				return "验证手机格式不正确";
			}
			dbAccount.setMobileNumber(mobileNumber);
		}
		Integer loginInfoId = dbAccount.getId();
		List<UserInfo> dbUserInfos = infoDao.findByLoginInfoId(loginInfoId);
		UserInfo dbUserInfo = null;
		if (CollectionUtils.isEmpty(dbUserInfos)) {
			dbUserInfo = new UserInfo();
			dbUserInfo.setLoginInfoId(loginInfoId);
			dbUserInfo.setName(loginId);
			// 数据库没有Info信息，先保存
			dbUserInfo = infoDao.saveAndFlush(dbUserInfo);
		} else {
			dbUserInfo = dbUserInfos.get(0);
		}
		String name = paramJSON.getAsString("name");
		String question = paramJSON.getAsString("question");
		String answer = paramJSON.getAsString("answer");
		String companyName = paramJSON.getAsString("companyName");
		Number companySizeId = paramJSON.getAsNumber("companySizeId");
		Number companyTypeId = paramJSON.getAsNumber("companyTypeId");
		if (name != null) {
			dbUserInfo.setName(name);
		}
		if (question != null) {
			dbUserInfo.setQuestion(question);
		}
		if (answer != null) {
			dbUserInfo.setAnswer(answer);
		}
		if (companyName != null) {
			dbUserInfo.setCompanyName(companyName);
		}
		if (companySizeId != null) {
			dbUserInfo.setCompanySizeId(companySizeId.intValue());
		}
		if (companyTypeId != null) {
			dbUserInfo.setCompanyTypeId(companyTypeId.intValue());
		}

		Integer dbUserInfoId = dbUserInfo.getId();

		if (paramJSON.get("imgManage[]") != null) {
			@SuppressWarnings("unchecked")
			List<String> attchPaths = (List<String>) paramJSON.get("imgManage[]");

			if (dbUserInfoId != null) {
				// 先删除
				attachmentsDao.deleteByUserInfoIdAndPagePath(dbUserInfoId,
						AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
				List<UserInfoAttachments> attchList = new ArrayList<UserInfoAttachments>();
				for (String path : attchPaths) {
					UserInfoAttachments attach = new UserInfoAttachments();
					int fileNameIndex = path.lastIndexOf("/");
					attach.setFileName(path.substring(fileNameIndex + 1));
					attach.setFilePath(path);
					attach.setPagePath(AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
					attach.setUserInfoId(dbUserInfo.getId());
					attchList.add(attach);
				}
				// 再添加
				if (attchList.size() > 0) {
					attachmentsDao.saveAll(attchList);
				}
			}

		}

		if (paramJSON.get("headimg") != null) {
			if (dbUserInfoId != null) {
				// 先删除
				attachmentsDao.deleteByUserInfoIdAndPagePath(dbUserInfoId, AdminApplicationConstants.USER_HEAD_IMG);
				String path = (String) paramJSON.get("headimg");
				List<UserInfoAttachments> attchList = new ArrayList<UserInfoAttachments>();
				UserInfoAttachments attach = new UserInfoAttachments();
				int fileNameIndex = path.lastIndexOf("/");
				attach.setFileName(path.substring(fileNameIndex + 1));
				attach.setFilePath(path);
				attach.setPagePath(AdminApplicationConstants.USER_HEAD_IMG);
				attach.setUserInfoId(dbUserInfoId);
				attchList.add(attach);
				attachmentsDao.saveAll(attchList);
			}
		}

		PurchaserAccount newAccount = dao.saveAndFlush(dbAccount);
		UserInfo newUserInfo = infoDao.saveAndFlush(dbUserInfo);
		MixedUserResponse mixed = new MixedUserResponse(newAccount, newUserInfo, null);
		return JSONUtils.objToJSON(mixed);
	}

	public List<String> getCCFilePaths(String loginId) {
		List<String> ccFilePaths = new ArrayList<>();
		MixedUserResponse dbMixed = findMixedObjByLoginId(loginId);
		if (dbMixed != null) {
			List<UserInfoAttachments> companyCredentials = dbMixed.getCompanyCredentials();
			if (companyCredentials != null) {
				for (UserInfoAttachments atts : companyCredentials) {
					ccFilePaths.add(atts.getFilePath());
				}
			}
		}
		return ccFilePaths;
	}

	public String findMixedByLoginId(String dataJSON) {
		JSONObject paramJSON = JSONUtils.jsonToObj(dataJSON, JSONObject.class);
		String loginId = paramJSON.getAsString("loginId");
		MixedUserResponse response = findMixedObjByLoginId(loginId);
		return JSONUtils.objToJSON(response);
	}

	public MixedUserResponse findMixedObjByLoginId(String loginId) {
		if (!StringUtils.isEmpty(loginId)) {
			List<MixedUserResponse> mixedUserResponses = dao.findByMutipleParams(loginId, null, null, null, jpqlExecute,
					1, 0);
			if (CollectionUtils.isNotEmpty(mixedUserResponses)) {
				MixedUserResponse response = mixedUserResponses.get(0);
				UserInfo userInfo = response.getUserInfo();
				if (userInfo != null) {
					Integer userInfoId = userInfo.getId();
					List<UserInfoAttachments> companyCredentials = attachmentsDao
							.findByUserInfoIdAndPagePath(userInfoId, AdminApplicationConstants.USER_COMPANY_CREDENTIAL);
					List<UserInfoAttachments> headImgs = attachmentsDao.findByUserInfoIdAndPagePath(userInfoId,
							AdminApplicationConstants.USER_HEAD_IMG);
					response.setCompanyCredentials(companyCredentials);
					response.setHeadImgs(headImgs);
				}
				return response;
			}
		}
		return null;
	}

	public String findByLoginId(JSONObject requestObj) {
		if (requestObj == null || StringUtils.isEmpty(requestObj.getAsString("loginId"))) {
			return null;
		}
		PurchaserAccount dbAccount = dao.findByLoginId(requestObj.getAsString("loginId"));
		return JSONUtils.objToJSON(dbAccount);
	}
}
