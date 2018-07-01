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
		String mobileNumber = account.getMobileNumber();
		String smsCode = purchaser.getSmsCode();
		return validateSMSCodeParam(mobileNumber, smsCode, request);
	}

	/**
	 * 短信验证码校验
	 * 
	 * @param mobileNumber
	 * @param smsCode
	 * @param request
	 * @return
	 */
	public String validateSMSCodeParam(String mobileNumber, String smsCode, HttpServletRequest request) {
		if (StringUtils.isEmpty(mobileNumber)) {
			return "请输入账号";
		}
		if (StringUtils.isEmpty(smsCode)) {
			return "请输入短信验证码";
		}

		String encryptedSMSCode = CookieUtils.getCookieValue(SMSCodeUtils.COOKIE_ENCRYPTED_SMS_CODE_KEY + mobileNumber,
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

	/**
	 * 采购用户-用户个人信息-增加和修改
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	public String addAndModifyInfo(String param, HttpServletRequest request) {
		if (StringUtils.isEmpty(param)) {
			return "请求参数不能为空";
		}

		String loginId = CookieUtils.getCookieValue(CookieConstants.COOKIE_LOGIN_ID_KEY, request);
		if (loginId == null) {
			return "请重新登录";
		}

		JSONObject json = JSONUtils.jsonToObj(param, JSONObject.class);
		String mobileNumber = json.getAsString("mobileNumber");
		String smsCode = json.getAsString("smsCode");
		if (mobileNumber != null && smsCode != null) {
			return validateSMSCodeParam(mobileNumber, smsCode, request);
		}

		json.put("loginId", loginId);
		String addAndModifyInfoUrl = environment.getProperty("application.purchaserAccount.addAndModifyInfo");
		String baseUrl = environment.getProperty("app.server");
		String url = baseUrl + addAndModifyInfoUrl;
		Message message = new Message();
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();
	}

	public String findMixedByLoginId(HttpServletRequest request) {
		String loginId = CookieUtils.getCookieValue(CookieConstants.COOKIE_LOGIN_ID_KEY, request);
		if (loginId == null) {
			JSONObject result = new JSONObject();
			result.put("errMsg", "请重新登录");
			return result.toJSONString();
		}
		JSONObject json = new JSONObject();
		json.put("loginId", loginId);
		String baseUrl = environment.getProperty("app.server");
		String findMixedByLoginIdUrl = environment.getProperty("application.purchaserAccount.findMixedByLoginId");
		String url = baseUrl + findMixedByLoginIdUrl;
		Message message = new Message();
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();
	}
}
