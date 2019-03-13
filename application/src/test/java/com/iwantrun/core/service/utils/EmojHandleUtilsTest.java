package com.iwantrun.core.service.utils;

import org.junit.Test;

public class EmojHandleUtilsTest {

	@Test
	public void test() {
		String containsEmoj = "回了\ud83d\ude02○◇";
		System.out.println(containsEmoj);
		System.out.println(EmojHandleUtils.replaceEmojWith(containsEmoj, '*'));
		System.out.println(EmojHandleUtils.isNOTEmojiCharacter('回'));
		System.out.println(EmojHandleUtils.isNOTEmojiCharacter('\ud83d'));
	}

}
