package com.iwantrun.core.service.utils;

public class EmojHandleUtils {
	
	
    public static boolean isNOTEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
    
    
    public static String replaceEmojWith(String target, char replacement) {
    	if(target == null || target.length() == 0) {
    		return target;
    	}
    	StringBuffer buffer = new StringBuffer();
    	for(int i = 0 ; i<target.length() ; i++) {
    		char c = target.charAt(i);
    		if(isNOTEmojiCharacter(c)) {
    			buffer.append(c);
    		}else {
    			buffer.append(replacement);
    		}
    	}
    	return buffer.toString();
    }


}
