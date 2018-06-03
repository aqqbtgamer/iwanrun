package com.iwantrun.core.service.utils;

import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_ACCOUNT_KEY;
import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_ACTION_KEY;
import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_CONTENT_KEY;
import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_MOBILE_KEY;
import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_PASST_KEY;
import static com.iwantrun.core.constant.SMSCodeConstants.REQ_URI_VARIABLE_USER_ID_KEY;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.core.service.application.transfer.SMSCodeRequest;
import com.iwantrun.core.service.application.transfer.SMSCodeResponse;

/**
 * @author user 短信相关工具类
 */
public class SMSCodeUtils {

	/**
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186 电信：133、153、177、178、180、189、（1349卫通）
	 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、7、8中
	 * 的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
	 */
	public static final String MOBILE_NUMBER_REGEX = "[1][3578]\\d{9}";

	/**
	 * 校验 SMSCodeRequest和手机号
	 * 
	 * @param smsCodeRequest
	 * @return
	 */
	public static String validate(SMSCodeRequest smsCodeRequest) {
		if (smsCodeRequest == null) {
			return "参数有误";
		}

		String mobile = smsCodeRequest.getMobile();

		return validate(mobile);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static String validate(String mobile) {
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
		if (number == null)
			return false;
		// matches():字符串是否在给定的正则表达式匹配
		return number.matches(MOBILE_NUMBER_REGEX);
	}

	/**
	 * 生成短信验证码，并发送到手机
	 * 
	 * @param request
	 *            对象的mobile属性非空，必须设值
	 * @return
	 */
	public static SMSCodeResponse getSMSCode(SMSCodeRequest request) {
		RestTemplate template = new RestTemplate();
		
		// 生成短信验证码
		String smsCode = RandomUtils.getSixCode();
		request.setSmsCode(smsCode);
		
		// 获取URI后面的短信参数
		Map<String, String> uriVariables = getUriVariables(request);

		// 发送短信
		String sendResult = template.getForEntity(request.getUrl(), String.class, uriVariables).getBody();
		
		// 解析结果
		Map<String, String> parsed = XMLParseUtils.parseText(sendResult);
		
		String sendResultJson = JSONUtils.objToJSON(parsed);
		
		SMSCodeResponse response = JSONUtils.jsonToObj(sendResultJson, SMSCodeResponse.class);
		
		response.setMobile(request.getMobile());
		response.setSmsCode(smsCode);
		return response;
	}

	/**
	 * 获取短信发送接口参数，使用 org.springframework.web.client.RestTemplate
	 * .getForEntity(String url, Class<T>responseType, Map<String, ?> uriVariables)
	 * 这个方法时，uriVariables代表URI后面的参数，但url写法要跟下面类似
	 * http://dx1.xitx.cn:8888/sms.aspx?userid={userid}&account={account}&password={password}&mobile={mobile}&action={action}&content={content}
	 * uriVariables的key值就是上面{}里面的值，比如userid
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getUriVariables(SMSCodeRequest request) {
		Map<String, String> uriVariables = new HashMap<>();

		String account = request.getAccount();
		String action = request.getAction();
		String content = request.getContent();
		String password = request.getPassword();
		String userid = request.getUserid();
		String mobile = request.getMobile();
		String smsCode = request.getSmsCode();

		content += smsCode;

		uriVariables.put(REQ_URI_VARIABLE_ACCOUNT_KEY, account);
		uriVariables.put(REQ_URI_VARIABLE_ACTION_KEY, action);
		uriVariables.put(REQ_URI_VARIABLE_CONTENT_KEY, content);
		uriVariables.put(REQ_URI_VARIABLE_PASST_KEY, password);
		uriVariables.put(REQ_URI_VARIABLE_USER_ID_KEY, userid);
		uriVariables.put(REQ_URI_VARIABLE_MOBILE_KEY, mobile);

		return uriVariables;
	}

	/**
	 * 通过查看Spring RestTemplate的API后，发现有URI后的参数可以通过Map传递，故废弃此方法
	 * 
	 * @param mobile
	 * @param environment
	 * @return http://dx1.xitx.cn:8888/sms.aspx?userid=6739&account=a10554&password=a123456&mobile=13*****86&action=send&content=【沐跑】您的验证码是131481
	 */
	@Deprecated
	public static String getUrlWithParam(SMSCodeRequest request) {
		String account = request.getAccount();
		String action = request.getAction();
		String content = request.getContent();
		String password = request.getPassword();
		String userid = request.getUserid();
		String url = request.getUrl();
		String mobile = request.getMobile();
		String smsCode = request.getSmsCode();

		return url + "?userid=" + userid + "&account=" + account + "&password=" + password + "&mobile=" + mobile
				+ "&action=" + action + "&content=" + content + smsCode;
	}

}
