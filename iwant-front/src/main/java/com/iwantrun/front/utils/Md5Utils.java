package com.iwantrun.front.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * md5 加密工具 加盐且无法解密
 * @author WXP22
 *
 */
public class Md5Utils {
	
	 /**
	  * 加盐MD5  随机掺入盐 无法解密和逆向
	  * @param password
	  * @return
	  */
	 public static String generate(String password) {  
         Random r = new Random();  
         StringBuilder sb = new StringBuilder(16);  
         sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
         int len = sb.length();  
         if (len < 16) {  
             for (int i = 0; i < 16 - len; i++) {  
                 sb.append("0");  
             }  
         }  
         String salt = sb.toString();  
         password = md5Hex(password + salt);  
         char[] cs = new char[48];  
         for (int i = 0; i < 48; i += 3) {  
             cs[i] = password.charAt(i / 3 * 2);  
             char c = salt.charAt(i / 3);  
             cs[i + 1] = c;  
             cs[i + 2] = password.charAt(i / 3 * 2 + 1);  
         }  
         return new String(cs);  
     }
	 
	 /**
	  * 校验原文和加过盐后的密文是否一致
	  * @param password
	  * @param md5
	  * @return
	  */
	 public static boolean verify(String password, String md5) {  
         char[] cs1 = new char[32];  
         char[] cs2 = new char[16];  
         for (int i = 0; i < 48; i += 3) {  
             cs1[i / 3 * 2] = md5.charAt(i);  
             cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);  
             cs2[i / 3] = md5.charAt(i + 1);  
         }  
         String salt = new String(cs2);  
         return md5Hex(password + salt).equals(new String(cs1));  
     }  
	 
	 /**
	  * 原始md5
	  * @param src
	  * @return
	  */
	 private static String md5Hex(String src) {  
         try {  
             MessageDigest md5 = MessageDigest.getInstance("MD5");  
             byte[] bs = md5.digest(src.getBytes());  
             return new String(new Hex().encode(bs));  
         } catch (Exception e) {  
             return null;  
         }  
     }  
	 
	 
	 
	 public static String SHA1(String decript){  
	        try {  
	            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");  
	            digest.update(decript.getBytes());  
	            byte messageDigest[] = digest.digest();  
	            // Create Hex String  
	            StringBuffer hexString = new StringBuffer();  
	            // 字节数组转换为 十六进制 数  
	                for (int i = 0; i < messageDigest.length; i++) {  
	                    String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
	                    if (shaHex.length() < 2) {  
	                        hexString.append(0);  
	                    }  
	                    hexString.append(shaHex);  
	                }  
	                return hexString.toString();  
	       
	            } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	            }  
	            return "";  
	    }  


}
