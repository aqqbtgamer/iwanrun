package com.iwantrun.admin.service;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.iwantrun.admin.transfer.Message;

import net.minidev.json.JSONObject;

@Service
public class RemoteFileService {
	
	private Logger logger = LoggerFactory.getLogger(RemoteFileService.class);
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	public String sendFileToApplication(MultipartFile file,String token) {
		String result = "failed" ;
		//1.解析文件
		if(file.isEmpty()) {
			logger.warn("no file content can be found to sent sendFileToApplication service ended");
			return result;
		}else {
			try {
				byte[] fileContent = file.getBytes();
				String contentType = file.getContentType();
				String fileName = file.getOriginalFilename();
				//2.将文件内容base 64
				String base64FileContent = Base64.getEncoder().encodeToString(fileContent);
				JSONObject object = new JSONObject();
				object.put("base64_file", base64FileContent);
				object.put("fileName", fileName);
				object.put("contentType", contentType);
				Message message = new Message();
				message.setAccessToken(token);
				message.setMessageBody(object.toJSONString());
				message.setRequestMethod(env.getProperty("application.file.service"));
				String url = env.getProperty("application.serverbase")+env.getProperty("application.file.service");
				ResponseEntity<Message> response = null;
				try {
					response = restTemplate.postForEntity(url, message, Message.class);
				}catch(Exception e) {
					logger.error("errors sendding message to file service ",e);					
				}		
				return response != null ? env.getProperty("application.serverbase")+response.getBody().getMessageBody() : null;
			} catch (IOException e) {
				logger.error("errors found when transfer file to byte[]",e);
				return result;
			}
		}
	}
	
	public String sendBase64ToApplication(String base64,String fileName,String token) {
		JSONObject object = new JSONObject();
		object.put("base64_file", base64);
		object.put("fileName", fileName);
		Message message = new Message();
		message.setAccessToken(token);
		message.setMessageBody(object.toJSONString());
		message.setRequestMethod(env.getProperty("application.file.service"));
		String url = env.getProperty("application.serverbase")+env.getProperty("application.file.service");
		ResponseEntity<Message> response = null;
		try {
			response = restTemplate.postForEntity(url, message, Message.class);
		}catch(Exception e) {
			logger.error("errors sendding message to file service ",e);					
		}		
		return response != null ? env.getProperty("application.serverbase")+response.getBody().getMessageBody() : null;		
	}

}
