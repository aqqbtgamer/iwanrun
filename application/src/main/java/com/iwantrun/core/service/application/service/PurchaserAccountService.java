package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.dao.JPQLEnableRepository;
import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.transfer.MixedUserResponse;
import com.iwantrun.core.service.application.transfer.PurchaserAccountRequest;
import com.iwantrun.core.service.utils.Md5Utils;
import com.iwantrun.core.service.utils.PageDataWrapUtils;

import net.minidev.json.JSONObject;

@Service
public class PurchaserAccountService {
	@Autowired
	private Environment environment;
	@Autowired
	private PurchaserAccountDao dao;
	@Autowired
	private JPQLEnableRepository jpqlExecute;

	public String register(PurchaserAccount account) {
		String md5Password = Md5Utils.generate(account.getPassword());
		account.setPassword(md5Password);
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
		String smsCode = accountRequest.getSmsCode();
		if (StringUtils.isEmpty(smsCode)) {
			return "短信验证码不能为空";
		}
		// 短信验证码校验
		// String validated = validate(smsCode)

		String password = accountRequest.getAccount().getPassword();
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
		int pageIndex = index == null ? 1 : Integer.parseInt(index);
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

	@Transactional
	public String addPurchaseUserAndRelated(Map<String, Object> paramsMap) {
		String mobileNumber = (String) paramsMap.get("mobileNumber");
		return null;
	}
}
