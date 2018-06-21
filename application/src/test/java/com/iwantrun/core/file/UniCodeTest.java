package com.iwantrun.core.file;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniCodeTest {
@Test
public void testName() throws Exception {
	//\u3010\u6C90\u8DD1\u3011\u60A8\u7684\u9A8C\u8BC1\u7801\u662F
	Character c='\u3010';
	c='\u6C90';
	c='\u8DD1';
	c='\u3011';
	c='\u60A8';
	c='\u7684';
	c='\u9A8C';
	c='\u8BC1';
	c='\u7801';
	c='\u662F';
	System.out.println(c);
}
}
