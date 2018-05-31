package com.iwantrun.core.service.application.service;

import java.util.List;

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
import com.iwantrun.core.service.utils.JSONUtils;
import com.iwantrun.core.service.utils.Md5Utils;

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
		String md5Password=Md5Utils.generate(account.getPassword());
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
		String loginId=account.getLoginId();
		String password=account.getPassword();
		if(StringUtils.isEmpty(loginId)) {
			return "请输入账号";
		}
		if(StringUtils.isEmpty(password)) {
			return "请输入密码";
		}
		
		String md5Passrword=Md5Utils.generate(password);
		
		PurchaserAccount find= dao.findByLoginIdAndPassword(loginId,md5Passrword);
		
		if(find==null) {
			return "账号密码不匹配";
		}
		return null;
	}
	
	public String findPurchaseUserPaged(JSONObject obj) {
		Integer pageSize = Integer.parseInt(environment.getProperty("common.pageSize"));
		int pageIndex = Integer.parseInt(obj.getAsString("pageIndex"));
		Integer loginId = obj.getAsNumber("loginId").intValue();
		String name = obj.getAsString("name");
		String mobileNumber = obj.getAsString("mobileNumber");
		Integer role = obj.getAsNumber("role").intValue();
		Pageable page =  PageRequest.of(pageIndex, pageSize, Sort.Direction.ASC, "id");
		Integer totalNum = dao.countByMutipleParams(loginId, name, role, mobileNumber, jpqlExecute);
		List<MixedUserResponse> content = dao.findByMutipleParams(loginId, name, role, mobileNumber, jpqlExecute, pageSize, pageIndex);
		PageImpl<MixedUserResponse> result = new PageImpl<MixedUserResponse>(content, page, totalNum);
		return JSONUtils.objToJSON(result);
	}
}
