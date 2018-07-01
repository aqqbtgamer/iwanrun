package com.iwantrun.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.iwantrun.admin.utils.CookieUtils;

import net.minidev.json.JSONObject;

@Service
public class UEditorService {
	
	public static final String METHOD_NAME_CONFIG = "config" ;
	
	public static final String UPLOAD_PATF = "ueUpload";
	
	public static final String UPLOAD_SCRAW_PATF = "ueScrawUpload";
	
	public static final String UPLOAD_VIDEO_PATF = "ueVideoUpload";
	
	public static final String UPLOAD_File_PATF = "ueFileUpload";
	
	private static final String COMMON_FILE_PREV = "";
	
	private static final int IMG_MAX_SIZE = 2048000000;
	
	private static final String[] IMG_ALLOWED_TYPE = new String[] {
			".png",
			".jpg",
			".jpeg",
			".gif",
			".bmp"
	};
	
	private static final String[] VIDEO_ALLOW_TYPE = new String[] {
			".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
	        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"
	};
	
	private static final String IMG_INSERT_ALIGN = "none";
	
	private static final String IMG_FIELD_NAME = "ueUpload";
	
	private static final String SCRAW_FIELD_NAME = "ueScrawUpload";
	
	private static final String UPLOAD_SUCCESS = "SUCCESS";
	
	@Autowired
	private RemoteFileService fileService ;
	
	public String getConfig() {
		JSONObject jsonConfig = new JSONObject();
		jsonConfig.put("imageActionName", UPLOAD_PATF);
		jsonConfig.put("imageMaxSize", IMG_MAX_SIZE);
		jsonConfig.put("imageAllowFiles", IMG_ALLOWED_TYPE);
		jsonConfig.put("imageCompressEnable", false);
		jsonConfig.put("imageInsertAlign", IMG_INSERT_ALIGN);
		jsonConfig.put("imageFieldName", IMG_FIELD_NAME);
		jsonConfig.put("imageUrlPrefix", COMMON_FILE_PREV);
		jsonConfig.put("scrawlActionName", UPLOAD_SCRAW_PATF);
		jsonConfig.put("scrawlFieldName", SCRAW_FIELD_NAME);
		jsonConfig.put("scrawlMaxSize", IMG_MAX_SIZE);
		jsonConfig.put("scrawlUrlPrefix", COMMON_FILE_PREV);
		jsonConfig.put("scrawlInsertAlign", IMG_INSERT_ALIGN);
		jsonConfig.put("videoActionName", UPLOAD_VIDEO_PATF);
		jsonConfig.put("videoFieldName", IMG_FIELD_NAME);
		jsonConfig.put("videoUrlPrefix", COMMON_FILE_PREV);
		jsonConfig.put("videoMaxSize", IMG_MAX_SIZE);
		jsonConfig.put("videoAllowFiles", VIDEO_ALLOW_TYPE);
		jsonConfig.put("fileActionName", UPLOAD_File_PATF);
		jsonConfig.put("fileFieldName", IMG_FIELD_NAME);
		jsonConfig.put("fileFieldName", IMG_FIELD_NAME);
		return jsonConfig.toJSONString();
	}

	public String upload(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtils.getLoginToken(request);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
		MultipartFile file = multiRequest.getFile(IMG_FIELD_NAME);
		String fileVisitPath = fileService.sendFileToApplication(file, token);
		String fileName = file.getOriginalFilename();
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("state", UPLOAD_SUCCESS);
		jsonResult.put("url", fileVisitPath);
		jsonResult.put("title", fileName);
		jsonResult.put("original", fileName);
		return jsonResult.toJSONString();
	}
	
	public String uploadScraw(HttpServletRequest request, HttpServletResponse response) {
		String token = CookieUtils.getLoginToken(request);
		String base64Image  = request.getParameter(SCRAW_FIELD_NAME);
		String fileName = "newScraw.jpg";
		String fileVisitPath = fileService.sendBase64ToApplication(base64Image, fileName, token);
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("state", UPLOAD_SUCCESS);
		jsonResult.put("url", fileVisitPath);
		jsonResult.put("title", fileName);
		jsonResult.put("original", fileName);
		return jsonResult.toJSONString();
	}

}
