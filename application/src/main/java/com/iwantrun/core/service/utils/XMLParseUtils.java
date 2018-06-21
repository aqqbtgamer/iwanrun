package com.iwantrun.core.service.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.controller.SMSCodeController;

/**
 * 
 * @author sanny 各种格式的XML解析
 *
 */
public class XMLParseUtils {

	private static final Logger logger = LoggerFactory.getLogger(SMSCodeController.class);

	/**
	 * xml字符串解析
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseText(String xml) {

		logger.info("开始对XML数据进行解析，XML：{}", xml);

		Map<String, String> result = new HashMap<>();
		try {
			if (!StringUtils.isEmpty(xml)) {
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();
				List<Element> elements = root.elements();
				for (Element element : elements) {
					result.put(element.getName(), element.getTextTrim());
				}
			}
		} catch (Exception e) {
			logger.error("开始对XML数据进行解析，异常：{}", e);
		}
		return result;
	}

}
