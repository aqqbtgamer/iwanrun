package com.iwantrun.core.service.application;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URL;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class ResourceUtilsTest {
	@Test
	public void testName() throws Exception {
		try {
			String fileUrl = "http://localhost:8089/iwant_admin/images/1524319241.png";
			
			String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
			
			filename = filename.replace(".", "_icon.");
			
			
			URL url=ResourceUtils.getURL(fileUrl);
			
			
			URI uri=url.toURI();
			System.out.println(uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
