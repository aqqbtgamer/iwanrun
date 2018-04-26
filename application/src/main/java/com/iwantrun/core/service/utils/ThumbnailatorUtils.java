package com.iwantrun.core.service.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailatorUtils {
	@Autowired
	private static Environment env;

	public static final String IMAGE_ICON_TO_PATH_KEY = "production.info.main.image.icon.to.path";// 配置文件中的key值
	public static final String IMAGE_ICON_SIZE_ENV_KEY = "production.info.main.image.icon.size";// 配置文件中的key值
	public static final String IMAGE_ICON_SIZE_DEFAULT = "{\"width\":200, \"height\":200}";// 默认大小

	/**
	 * 生成主图缩略图
	 * 
	 * @param mainImageFullPath
	 *            图片全路径
	 * @throws IOException
	 */
	public static void thumbnailator(String fullPath) throws IOException {
		Environment environment = new StandardEnvironment();
		if (!StringUtils.isEmpty(fullPath)) {
			File file = new File(fullPath);
			if (file != null && !file.exists()) {
				String iconSizeStr = environment.getProperty(IMAGE_ICON_SIZE_ENV_KEY, IMAGE_ICON_SIZE_DEFAULT);
				String toPath = environment.getProperty(IMAGE_ICON_TO_PATH_KEY);
				if (StringUtils.isEmpty(toPath)) {
					//toPath = ThumbnailatorUtils.class.getClassLoader().getResource("").getPath();
					toPath = fullPath.substring(0, fullPath.lastIndexOf("."))+"_icon.jpg";
				}
				Map<String, Integer> iconSizeMap = JSONUtils.toMap(iconSizeStr, Integer.class);
				Thumbnails.of(fullPath).size(iconSizeMap.get("width"), iconSizeMap.get("height")).toFile(toPath);
			}
		}
	}
}
