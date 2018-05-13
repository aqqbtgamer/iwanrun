package com.iwantrun.core.service.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.dao.PurchaserAccountDao;
import com.iwantrun.core.service.application.domain.PurchaserAccount;
import com.iwantrun.core.service.application.transfer.PurchaserAccountRequest;
import com.iwantrun.core.service.utils.Md5Utils;

@Service
public class PurchaserAccountService {
	@Autowired
	private Environment environment;
	@Autowired
	private PurchaserAccountDao dao;

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
}
