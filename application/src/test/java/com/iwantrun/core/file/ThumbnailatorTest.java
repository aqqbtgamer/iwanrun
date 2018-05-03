package com.iwantrun.core.file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.iwantrun.core.service.utils.ThumbnailatorUtils;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailatorTest {
	
	@Test
	public void test() throws IOException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		Thumbnails.of("D:/JavaProject/iwanrun/iwant-admin/src/main/resources/static/images/1524319241.png").size(2560, 2048).toFile("C:\\Users\\user\\Desktop\\a380_2560x2048.jpg");
	}
	
	@Test
	public  void test2() throws IOException {		
		String path = ThumbnailatorTest.class.getClassLoader().getResource("/").getPath();
		System.out.println(path);
	}
	
	@Test
	public void testName() throws Exception {
		String urlstr="http://localhost:8089/iwant_admin/images/1524319241.pngd";
		String path = this.getClass().getClassLoader().getResource("").getPath();
		URL url=new URL(urlstr);
		url.getFile();
		url.getUserInfo();
		url.openStream();
		Thumbnails.of(url).size(2560, 2048);
		//ThumbnailatorUtils.thumbnailator("");
	}
	
	@Test
	public void testName2() throws Exception {
		String url="http://127.0.0.1:9999/iwant_app/application/productionInfo/add";
		String contextPath="/iwant_app";
		System.out.println(url.split(contextPath)[0]+contextPath);
	}
	
	@Test
	public void testName3() throws Exception {
		String url="/D:/JavaProject/iwanrun/application/target/classes//static/icon/1524620559_1525331852103_icon.jpg";
		System.out.println(url.split("static")[1]);
	}
}
