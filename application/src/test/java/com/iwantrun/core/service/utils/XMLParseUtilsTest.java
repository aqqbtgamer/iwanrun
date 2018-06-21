package com.iwantrun.core.service.utils;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class XMLParseUtilsTest {
	 @Test
	public void testName() throws Exception {
		 String xml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms>\r\n" + 
		 		" <returnstatus>Success</returnstatus>\r\n" + 
		 		" <message>ok</message>\r\n" + 
		 		" <remainpoint>7863</remainpoint>\r\n" + 
		 		" <taskID>93390617</taskID>\r\n" + 
		 		" <successCounts>1</successCounts></returnsms>";
		 
		 xml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms>\r\n" + 
		 		" <returnstatus>Faild</returnstatus>\r\n" + 
		 		" <message>用户名或密码不能为空</message>\r\n" + 
		 		" <remainpoint>0</remainpoint>\r\n" + 
		 		" <taskID>0</taskID>\r\n" + 
		 		" <successCounts>0</successCounts></returnsms>";
		 XMLParseUtils.parseText(xml);
	}
}
