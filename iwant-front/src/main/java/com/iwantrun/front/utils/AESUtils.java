package com.iwantrun.front.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
	
	public static String encode(String content) throws GeneralSecurityException, IOException {
		SecretKey key=new SecretKeySpec(magic_array, "AES");
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte [] byte_encode=content.getBytes("utf-8");
		byte [] byte_AES=cipher.doFinal(byte_encode);
		String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
		return AES_encode;
	}
	
	public static String decode(String content) throws GeneralSecurityException, IOException {
		SecretKey key=new SecretKeySpec(magic_array, "AES");
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
		byte [] byte_decode=cipher.doFinal(byte_content);
        String AES_decode=new String(byte_decode,"utf-8");
        return AES_decode;
	}
	
}
