package com.iwantrun.core.service.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.iwantrun.core.service.application.transfer.Message;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

@Service
public class RemoteFileService {
	
	Logger logger = LoggerFactory.getLogger(RemoteFileService.class);
	
	@Autowired  
    private Environment env;
	
	public Message uploadRemoteFile(Message message){
		Message result = new Message();
		result.setAccessToken(message.getAccessToken());
		result.setMessageBody("upload failed");
		String json = message.getMessageBody();
		JSONObject requestObj = (JSONObject) JSONValue.parse(json);
		long currenTime = System.currentTimeMillis();
		//1.转换base64
		String base64FileContent = requestObj.getAsString("base64_file");
		byte[] fileBytes = Base64.getDecoder().decode(base64FileContent);
		String fileName = requestObj.getAsString("fileName");
		int suffixIndex = fileName.lastIndexOf(".");
		String FileRealName = fileName.substring(0, suffixIndex);
		String suffixName = fileName.substring(suffixIndex+1);
		String uploadedName = FileRealName+"_"+currenTime+"."+suffixName ;
		try {
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			String uploadPath = env.getProperty("remote.file.upload");
			File uploadFolder = new File(path.getAbsolutePath(),uploadPath);
			if(!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			File upload = new File(uploadFolder.getAbsolutePath(),uploadedName);
			if(!upload.exists()) {
				upload.createNewFile();
			}
			@SuppressWarnings("resource")
			FileOutputStream out = new FileOutputStream(upload);
			out.write(fileBytes);
			out.flush();
			result.setMessageBody(env.getProperty("remote.file.visitpath")+uploadedName);
		} catch (FileNotFoundException e) {
			logger.error("path is not find for upload",e);
		} catch (IOException e) {
			logger.error("file write error",e);
		}
		return result;
	}

}
