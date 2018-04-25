package com.iwantrun.core.file;

import java.io.IOException;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailatorTest {
	
	@Test
	public void test() throws IOException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		Thumbnails.of("D:/JavaProject/iwanrun/iwant-admin/src/main/resources/static/images/1524319241.png").size(2560, 2048).toFile("C:\\Users\\user\\Desktop\\a380_2560x2048.jpg");
	}
	
	@Test
	public  void main() throws IOException {		
		String path = ThumbnailatorTest.class.getClassLoader().getResource("/").getPath();
		System.out.println(path);
	}
}
