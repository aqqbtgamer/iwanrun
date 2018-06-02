package com.iwantrun.core.service.utils;

import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.core.service.application.transfer.SMSCodeRequest;
import com.iwantrun.core.service.application.transfer.SMSCodeResponse;

import net.minidev.json.JSONObject;

/**
 * @author user 短信相关工具类
 */
public class SMSCodeUtils {

	public static String validate(JSONObject paramObj) {
		if (paramObj == null) {
			return "参数有误";
		}
		String mobile = paramObj.getAsString("mobile");
		if (StringUtils.isEmpty(mobile)) {
			return "请填写手机号";
		}
		if (!isMobileNum(mobile)) {
			return "手机号格式有误";
		}
		return null;
	}

	/**
	 * 校验手机号格式
	 *
	 * @param number
	 * @return
	 */
	public static boolean isMobileNum(String number) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、177、178、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String num = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中 的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		// matches():字符串是否在给定的正则表达式匹配
		return number.matches(num);
	}

	public static SMSCodeResponse getSMSCode(String mobile, Environment environment, RestTemplate template) {
		String url = getUrlWithParam(mobile, environment);
		String response = template.getForEntity(url, String.class).getBody();
		
		return null;
	}

	/**
	 * @param mobile
	 * @param environment
	 * @return http://dx1.xitx.cn:8888/sms.aspx?userid=6739&account=a10554&password=a123456&mobile=13*****86&action=send&content=【隔壁老王】您的验证码是131481
	 */
	public static String getUrlWithParam(String mobile, Environment environment) {
		String account = environment.getProperty("sms_code.get.request.account");
		String action = environment.getProperty("sms_code.get.request.action");
		String content = environment.getProperty("sms_code.get.request.content");
		String password = environment.getProperty("sms_code.get.request.password");
		String userid = environment.getProperty("sms_code.get.request.userid");
		String url = environment.getProperty("sms_code.get.request.url");

		String smsCode = RandomUtils.getSixCode();

		return url + "?userid=" + userid + "&account=" + account + "&password=" + password + "&mobile=" + mobile
				+ "&action=" + action + "&content=" + content + smsCode;
	}

	public static SMSCodeRequest encapsulateRequest(String mobile, Environment environment) {
		SMSCodeRequest request = new SMSCodeRequest();

		String account = environment.getProperty("sms_code.get.request.account");
		String action = environment.getProperty("sms_code.get.request.action");
		String content = environment.getProperty("sms_code.get.request.content");
		String password = environment.getProperty("sms_code.get.request.password");
		String userid = environment.getProperty("sms_code.get.request.userid");

		request.setAccount(account);
		request.setAction(action);
		request.setContent(content);
		request.setMobile(mobile);
		request.setPassword(password);
		request.setUserid(userid);

		return request;
	}

}
