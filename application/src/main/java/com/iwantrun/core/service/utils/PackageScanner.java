package com.iwantrun.core.service.utils;

import java.io.File;
import java.util.List;

import org.springframework.util.StringUtils;

public class PackageScanner {
	
	public static String package2Dir(String packageName){
		if(!StringUtils.isEmpty(packageName)){
			String[] pathArray = packageName.split("\\.");
			String fullPath = "";
			for(String path : pathArray){
				fullPath = fullPath.concat(path).concat(File.separator);
			}
			return fullPath;
		}else{
			return null ;
		}
	}
	
	public static void scanClass(String path,List<File> loadClasses){
		File packageDir =  new File(path);		
		if(packageDir.isDirectory()){
			String[] fileArray = packageDir.list();
			for(String file : fileArray){
				scanClass(path+File.separator+file,loadClasses);
			}
		}else{
			if(packageDir.getName().endsWith(".class"))
			loadClasses.add(packageDir);
		}
	}

}
