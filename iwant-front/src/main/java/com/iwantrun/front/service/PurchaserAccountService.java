package com.iwantrun.front.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.front.constants.CookieConstants;
import com.iwantrun.front.domain.PurchaserAccount;
import com.iwantrun.front.transfer.Message;
import com.iwantrun.front.transfer.PurchaserAccountRequest;
import com.iwantrun.front.utils.AESUtils;
import com.iwantrun.front.utils.CookieUtils;
import com.iwantrun.front.utils.JSONUtils;
import com.iwantrun.front.utils.SMSCodeUtils;

import net.minidev.json.JSONObject;

@Service
public class PurchaserAccountService {
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate template;

	/**
	 * 采购用户修改密码
	 * 
	 * @param purchaser
	 * @return
	 */
	public Message modifyPwd(PurchaserAccountRequest purchaser) {
		String register = environment.getProperty("application.purchaserAccount.pwd.modify");
		String baseUrl = environment.getProperty("app.server");

		String url = baseUrl + register;

		Message message = new Message();
		message.setMessageBody(JSONUtils.objToJSON(purchaser));
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}

	/**
	 * 采购用户注册
	 * 
	 * @param purchaser
	 * @return PurchaserAccount
	 */
	public Message register(PurchaserAccountRequest purchaser) {
		String register = environment.getProperty("application.purchaserAccount.register");
		String baseUrl = environment.getProperty("app.server");

		String url = baseUrl + register;

		Message message = new Message();
		message.setMessageBody(JSONUtils.objToJSON(purchaser));
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}

	/**
	 * 采购用户登录
	 * 
	 * @param session
	 * @param purchaser
	 * @return
	 */
	public Message login(PurchaserAccountRequest purchaser) {
		String login = environment.getProperty("application.purchaserAccount.login");
		String baseUrl = environment.getProperty("app.server");
		String url = baseUrl + login;

		Message message = new Message();
		message.setMessageBody(JSONUtils.objToJSON(purchaser));
		message.setRequestMethod(url);
		return template.postForEntity(url, message, Message.class).getBody();
	}

	/**
	 * 获取短信验证码校验结果
	 * 
	 * @param request
	 * @param purchaser
	 * @return
	 */
	public Message getVaidateSMSCodeResult(HttpServletRequest request, PurchaserAccountRequest purchaser) {
		String errorMsg = this.validateSMSCodeParam(request, purchaser);
		if (errorMsg != null) {
			Message response = new Message();
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("errorMsg", errorMsg);
			response.setMessageBody(responseJSON.toJSONString());
			return response;
		}
		return null;
	}

	/**
	 * 短信验证码校验
	 * 
	 * @param request
	 * @param purchaser
	 * @return
	 */
	public String validateSMSCodeParam(HttpServletRequest request, PurchaserAccountRequest purchaser) {
		PurchaserAccount account = purchaser.getAccount();
		String loginId = account.getLoginId();
		if (StringUtils.isEmpty(loginId)) {
			return "请输入账号";
		}
		String smsCode = purchaser.getSmsCode();
		if (StringUtils.isEmpty(smsCode)) {
			return "请输入短信验证码";
		}

		String encryptedSMSCode = CookieUtils.getCookieValue(SMSCodeUtils.COOKIE_ENCRYPTED_SMS_CODE_KEY + loginId,
				request);

		if (StringUtils.isEmpty(encryptedSMSCode)) {
			return "请重新获取短信验证码";
		}

		String decryptedSMSCode = AESUtils.decode(encryptedSMSCode);
		if (!smsCode.equals(decryptedSMSCode)) {
			return "短信验证码不匹配";
		}
		return null;
	}

	/**
	 * 将token写入cookie
	 * 
	 * @param isAutoLogin
	 * 
	 * @param accessToken
	 * @param loginId
	 * @param response
	 */
	public void addCookieForToken(boolean isAutoLogin, String accessToken, String loginId,
			HttpServletResponse response) {
		if (!StringUtils.isEmpty(accessToken)) {
			String expirty = null;
			if (isAutoLogin) {
				expirty = environment.getProperty(CookieConstants.COOKIE_EXPIRY_ACCESS_TOKEN_KEY);
			}
			CookieUtils.addCookie(expirty, CookieConstants.COOKIE_ACCESS_TOKEN_KEY, accessToken, response);
			CookieUtils.addCookie(expirty, CookieConstants.COOKIE_LOGIN_ID_KEY, loginId, response);
		}
	}

	public Message addAndModifyInfo(String param, HttpServletRequest request) {
		if (!StringUtils.isEmpty(param)) {

			String addAndModifyInfoUrl = environment.getProperty("application.purchaserAccount.addAndModifyInfo");
			String baseUrl = environment.getProperty("app.server");
			String url = baseUrl + addAndModifyInfoUrl;

			JSONObject json = JSONUtils.jsonToObj(param, JSONObject.class);
			String loginId = CookieUtils.getCookieValue(CookieConstants.COOKIE_LOGIN_ID_KEY, request);
			json.put("loginId", loginId);

			Message message = new Message();
			message.setMessageBody(json.toJSONString());
			message.setRequestMethod(url);

			Message result = template.postForEntity(url, message, Message.class).getBody();

			return result;
		}
		return null;
	}
}
