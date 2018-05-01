package com.iwantrun.core.service.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailatorUtils {
	
	private static PropertyResolver pr=null;
	
	static {
		try {
			PropertySource<?>  ps = new ResourcePropertySource("application", "classpath:application.properties");
			MutablePropertySources mps=new MutablePropertySources();
			mps.addFirst(ps);
			pr=new PropertySourcesPropertyResolver(mps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static final String IMAGE_ICON_TO_PATH_KEY = "production.info.main.image.icon.to.path";// 缩略图存放位置
	public static final String IMAGE_ICON_SIZE_ENV_KEY = "production.info.main.image.icon.size";// 缩略图大小
	public static final String IMAGE_ICON_SIZE_DEFAULT = "{\"width\":200, \"height\":200}";// 缩略图默认大小
	public static final String IMAGE_ICON_TO_PATH_DEFAULT = "static/icon/";// 缩略图默认大小

	/**
	 * 生成主图缩略图
	 * 
	 * @param mainImageFullPath
	 *            图片全路径
	 * @throws IOException
	 */
	public static void thumbnailator(String fullPath) throws IOException {
		if (!StringUtils.isEmpty(fullPath)) {
			URL url = new URL(fullPath);
			InputStream inputStream = url.openStream();

			if (inputStream != null) {
				//缩略图存放位置
				String iconDir = pr.getProperty(IMAGE_ICON_TO_PATH_KEY, IMAGE_ICON_TO_PATH_DEFAULT);
				//缩略图存放位置
				String iconFullDirPath = ResourceUtils.getURL("classpath:").getPath() + iconDir;
				
				File iconFullDir = new File(iconFullDirPath);
				
				if(!iconFullDir.exists()) {
					iconFullDir.mkdirs();
				}
				// 文件名
				String filename = fullPath.substring(fullPath.lastIndexOf("/") + 1, fullPath.lastIndexOf("."));
				// 文件扩展名
				String fileExtension = fullPath.substring(fullPath.lastIndexOf("."), fullPath.length());
				//缩略图文件全称
				String iconFullPath = iconFullDirPath + filename + "_icon" + fileExtension;
				
				File iconFile = new File(iconFullPath);
				// 如果已存在，则使用系统当前时间
				if (iconFile.exists()) {
					iconFullPath = iconFullDirPath + filename + System.currentTimeMillis() + "_icon" + fileExtension;
				}
				
				String iconSizeStr = pr.getProperty(IMAGE_ICON_SIZE_ENV_KEY);
				Map<String, Integer> iconSizeMap = JSONUtils.toMap(iconSizeStr, Integer.class);
				//使用工具类完成缩略图的生成
				Thumbnails.of(inputStream).size(iconSizeMap.get("width"), iconSizeMap.get("height"))
				.toFile(iconFullPath);
				
			}
		}
	}
}
