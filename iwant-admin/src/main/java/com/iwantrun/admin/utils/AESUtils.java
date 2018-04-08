package com.iwantrun.admin.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密工具 可以解密
 * @author WXP22
 *
 */
public class AESUtils {
	
	private static final byte[] magic_array= new byte[] {-28,-20,-125,112,57,14,124,-3,-10,
			64,15,123,-55,-118,-38,-15};
	
	private static Cipher cipherDecode = null;
	
	private static Cipher cipherEncode = null;
	
	static {
		SecretKey key=new SecretKeySpec(magic_array, "AES");
		try {
			cipherEncode= Cipher.getInstance("AES");
			cipherEncode.init(Cipher.ENCRYPT_MODE, key);
			cipherDecode = Cipher.getInstance("AES");
			cipherDecode.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}		
	}
	
	public static String encode(String content)  {		
		try {
			if(StringUtils.isEmpty(content)) {
				return null ;
			}else {
				byte [] byte_encode=content.getBytes("utf-8");
				byte [] byte_AES=cipherEncode.doFinal(byte_encode);
				String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
				return AES_encode;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static String decode(String content) {
		try {
			if(StringUtils.isEmpty(content)) {
				return null ;
			}else {
				byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
				byte [] byte_decode=cipherDecode.doFinal(byte_content);
		        String AES_decode=new String(byte_decode,"utf-8");
		        return AES_decode;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
