package com.iwantrun.core.service.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void test() {
		System.out.println(AESUtils.encode("admin1876"));	
		System.out.println(AESUtils.decode("DBpGYjGCS1moV9uToJf0QtSWWAhZEvZnxJNAVoq5Xd2Rz8w/hyDfimu5ZDCgi7QtrCB1hdya57aEmxPb7pwjoYE7iIXFJyjmPvhhZMDk8hl5KbUPS9NhWU+nGugV7ra62ARFjYIcZxcVr1NCr2qzDw=="));
		Date date = new Date();
		date.setTime(1559387946308L);
		System.out.println(date.toLocaleString());
	}
	
	@Test
	public void test2() throws Exception {
		System.out.println(AESUtils.encode("123123"));	
	}
	
	@Test
	public void testReg() {
		String regRex = "^([a-zA-Z]+.*[0-9]+.*[!@#$%^&*]+)|([a-zA-Z]+.*[!@#$%^&*]+.*[0-9]+)|([0-9]+.*[!@#$%^&*]+.*[a-zA-Z]+)|([0-9]+.*[a-zA-Z]+.*[!@#$%^&*]+)|([!@#$%^&*]+.*[a-zA-Z]+.*[0-9]+)|([!@#$%^&*]+.*[0-9]+.*[a-zA-Z]+)$";
		Pattern pattern = Pattern.compile(regRex);
		Matcher match = pattern.matcher("a1234a!");
		System.out.println(match.matches());
		
	}
	
	@Test
	public void testMain() {
		System.out.println(lengthOfLongestSubstring(""));
	}
	
	
	 public int lengthOfLongestSubstring(String s) {
	        int max = 0 ;
	        int length = s.length();
	        char[] c = s.toCharArray();
	        for(int startIndex = 0;startIndex < length ; startIndex++){
	            int remainMaxLength = length - startIndex ;
	            if(remainMaxLength < max){
	                break ;
	            }
	            char[] tryMax = new char[remainMaxLength] ;
	        LoopI:  for(int i = 0; i<remainMaxLength ; i++){
	                char test = c[i+startIndex] ;
	                if(i-1 >= 0){
	                	boolean duplicate = false ;
	                    for(int m = 0 ; m<= i-1 ; m++){
	                        if(tryMax[m] == test){	                         
	                            break LoopI;
	                        }
	                    }
	                    if(!duplicate) {
	                    	tryMax[i] = test ;
	                    }
	                }else{
	                    tryMax[i] = test ;
	                }
	            }
	            boolean end = false ;
	           for(int i = 0 ; i < tryMax.length ; i++) {
	        	   if(tryMax[i] == 0) {
	        		   if(i > max) {
	        			   max = i ;	        			   
	        		   }
	        		   end = true ;
        			   break;
	        	   }
	           }
	           if(!end) {
	        	   if(tryMax.length > max) {
	        		   max = tryMax.length;
	        	   }
	           }
	        }
	        return max ;
	    }
}
