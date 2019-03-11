package com.iwantrun.front.service;

import java.util.List;

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
import com.iwantrun.front.utils.LoginTokenUtils;
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

		// 非注册时，再进行账号存在校验
		if (!purchaser.isRegister()) {
			String dbAccountJSON = findByMobileNumber(mobileNumber);
			if (StringUtils.isEmpty(dbAccountJSON)) {
				return "账号不存在";
			}
			List<PurchaserAccount> dbAccount = JSONUtils.toList(dbAccountJSON, PurchaserAccount.class);
			mobileNumber = dbAccount.get(0).getMobileNumber();
		}

		String smsCode = purchaser.getSmsCode();
		return validateSMSCodeParam(mobileNumber, smsCode, request);
	}

	public String findByMobileNumber(String mobileNumber) {
		JSONObject json = new JSONObject();
		json.put("mobileNumber", mobileNumber);
		String baseUrl = environment.getProperty("app.server");
		String findByMobileUrl = "/application/purchaserAccount/findByMobileNumber";
		String url = baseUrl + findByMobileUrl;
		Message message = new Message();
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();		
	}

	public String findByLoginId(String loginId) {
		JSONObject json = new JSONObject();
		json.put("loginId", loginId);
		String baseUrl = environment.getProperty("app.server");
		String findByLoginIdUrl = environment.getProperty("application.purchaserAccount.findByLoginId");
		String url = baseUrl + findByLoginIdUrl;
		Message message = new Message();
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();
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
			return "验证手机号未设置，请在个人中心设置";
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
			} else {
				expirty = environment.getProperty(CookieConstants.COOKIE_TEMP_EXPIRY_ACCESS_TOKEN_KEY);
			}
			CookieUtils.addCookie(expirty, CookieConstants.COOKIE_ACCESS_TOKEN_KEY, accessToken, response);
			CookieUtils.addCookie(expirty, CookieConstants.COOKIE_LOGIN_ID_KEY, loginId, response);
		}
	}

	/**
	 * 登出
	 * 
	 * @param response
	 */
	public void logout(HttpServletResponse response) {
		CookieUtils.addCookie(null, CookieConstants.COOKIE_ACCESS_TOKEN_KEY, null, response);
		CookieUtils.addCookie(null, CookieConstants.COOKIE_LOGIN_ID_KEY, null, response);
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

		boolean hasLogin = LoginTokenUtils.verifyLoginToken(request);
		if (!hasLogin) {
			return "请重新登录";
		}

		JSONObject json = JSONUtils.jsonToObj(param, JSONObject.class);
		String mobileNumber = json.getAsString("mobileNumber");
		String smsCode = json.getAsString("smsCode");		
		if (mobileNumber != null && smsCode != null) {
			String verifyResult = validateSMSCodeParam(mobileNumber, smsCode, request);
			if(verifyResult != null ) {
				return verifyResult;
			}
		}
		json.put("loginId", LoginTokenUtils.getLoginId(request));
		
		String addAndModifyInfoUrl = environment.getProperty("application.purchaserAccount.addAndModifyInfo");
		String baseUrl = environment.getProperty("app.server");
		String url = baseUrl + addAndModifyInfoUrl;
		Message message = new Message();
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		message.setAccessToken(CookieUtils.getLoginToken(request));
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();
	}

	public String findMixedByLoginId(HttpServletRequest request) {
		boolean hasLogin = LoginTokenUtils.verifyLoginToken(request);
		if (!hasLogin) {
			JSONObject result = new JSONObject();
			result.put("errMsg", "请重新登录");
			return result.toJSONString();
		}

		String loginId = LoginTokenUtils.getLoginId(request);
		String accessToken = CookieUtils.getLoginToken(request);

		return findMixedByLoginId(loginId, accessToken);
	}

	public String findMixedByLoginId(String loginId, String accessToken) {
		JSONObject json = new JSONObject();
		json.put("loginId", loginId);
		String baseUrl = environment.getProperty("app.server");
		String findMixedByLoginIdUrl = environment.getProperty("application.purchaserAccount.findMixedByLoginId");
		String url = baseUrl + findMixedByLoginIdUrl;
		Message message = new Message();
		message.setAccessToken(accessToken);
		message.setMessageBody(json.toJSONString());
		message.setRequestMethod(url);
		Message response = template.postForEntity(url, message, Message.class).getBody();
		return response.getMessageBody();
	}

	public String getWeixinLoginUrl() {			
		String baseUrl = environment.getProperty("application.weixinOpenAccount.baseUrl");
		return baseUrl;
	}
}
