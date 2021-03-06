package com.iwantrun.front.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iwantrun.front.transfer.WeixingTicketCache;
import com.iwantrun.front.transfer.WeixingTokenCache;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class WeiXinUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WeiXinUtils.class);
	
	private static WeixingTokenCache tokenCache ;
	
	private static WeixingTicketCache ticketCache ;
	
	
	public synchronized static String getAcessToken(String appId, String secret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        String accessToken = null;
        if(validateToken()) {
        	return tokenCache.getAccessToken();
        }else {
        	 try
             {
                 URL urlGet = new URL(url);
                 HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
                 http.setRequestMethod("GET"); // 必须是get方式请求
                 http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                 http.setDoOutput(true);
                 http.setDoInput(true);
                 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
                 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
                 http.connect();
                 InputStream is = http.getInputStream();
                 int size = is.available();
                 byte[] jsonBytes = new byte[size];
                 is.read(jsonBytes);
                 String message = new String(jsonBytes, "UTF-8");
                 logger.info("message token: "+ message);
                 JSONObject jsonObj = (JSONObject) JSONValue.parse(message);
                 accessToken = jsonObj.getAsString("access_token");
                 if(accessToken != null) {
                	 WeixingTokenCache cache = new WeixingTokenCache();
                     cache.setAccessToken(accessToken);
                     cache.setGenerateDate(new Date());
                     tokenCache = cache ;
                 }                
                 is.close();
             }
             catch (Exception e)
             {
             	logger.error("获取微信token失败",e);
             }
        	 logger.info("token: "+ accessToken);
             return accessToken;
        }
       
	}
	
	
	public  synchronized static String getAcessTicket(String accessToken) {
		String ticket = null;  
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken +"&type=jsapi";//这个url链接和参数不能变  
        if(validateTicket()) {
        	return ticketCache.getAccessTicket();
        }else {
        	try {  
                URL urlGet = new URL(url);  
                HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
                http.setRequestMethod("GET"); // 必须是get方式请求  
                http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
                http.setDoOutput(true);  
                http.setDoInput(true);  
                System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
                System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒  
                http.connect();  
                InputStream is = http.getInputStream();  
                int size = is.available();  
                byte[] jsonBytes = new byte[size];  
                is.read(jsonBytes);  
                String message = new String(jsonBytes, "UTF-8");  
                logger.info("message ticket: "+ message);
                JSONObject jsonObj = (JSONObject) JSONValue.parse(message);           
                ticket = jsonObj.getAsString("ticket");  
                if(ticket != null) {
                	WeixingTicketCache cache = new WeixingTicketCache();
                    cache.setAccessTicket(ticket);
                    cache.setGenerateDate(new Date());
                    ticketCache = cache;
                }                
                is.close();  
            } catch (Exception e) {  
            	logger.error("获取微信ticket失败",e); 
            }  
            return ticket;  
    	}
        }
	
	public synchronized static String getOpenAcessToken(String appid,String secret,String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=${appid}&secret=${secret}&code=${code}&grant_type=authorization_code";
		String sendingUrl = url.replace("${appid}", appid).replace("${secret}", secret).replace("${code}", code);
		try {
			URL urlGet = new URL(sendingUrl);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒  
            http.connect();  
            InputStream is = http.getInputStream();  
            int size = is.available();  
            byte[] jsonBytes = new byte[size];  
            is.read(jsonBytes);  
            String message = new String(jsonBytes, "UTF-8"); 
            logger.info("message open acess token: "+ message);
            return message;
		}catch(Exception e) {
			logger.error("获取微信open access token失败",e);
			return null;
		}
		
	}
	
	public synchronized static String getOpenUserInfo(String acessToken, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=${acessToken}&openid=${openId}";
		String sendingUrl = url.replace("${acessToken}", acessToken).replace("${openId}", openId);
		try {
			URL urlGet = new URL(sendingUrl);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒  
            http.connect();  
            InputStream is = http.getInputStream();  
            int size = is.available();  
            byte[] jsonBytes = new byte[size];  
            is.read(jsonBytes);  
            String message = new String(jsonBytes, "UTF-8"); 
            logger.info("message UserInfo: "+ message);
            return message;
		}catch(Exception e) {
			logger.error("获取微信open access token失败",e);
			return null;
		}
	}
	
	public synchronized static String getMobileLoginUrl(String appId,String baseUrl) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		String sendingUrl = url.replace("${appId}", appId).replace("${url}", baseUrl);
		return sendingUrl;
	}
        
	
	private static boolean validateToken() {
		boolean result = false;
		if(tokenCache != null) {
			long current = System.currentTimeMillis();
			long tokenGenerateTime = tokenCache.getGenerateDate().getTime();
			if(current - tokenGenerateTime < WeixingTokenCache.acessCalidateTime) {
				result = true ;
			}			
		}
		return result;
	}
	
	private static boolean validateTicket() {
		boolean result = false;
		if(ticketCache != null) {
			long current = System.currentTimeMillis();
			long ticketGenerateTime = ticketCache.getGenerateDate().getTime();
			if(current - ticketGenerateTime < WeixingTokenCache.acessCalidateTime) {
				result = true ;
			}			
		}
		return result;
	}

}
