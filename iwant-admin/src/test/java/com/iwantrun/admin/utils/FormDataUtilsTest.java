package com.iwantrun.admin.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.Test;

public class FormDataUtilsTest {

	@Test
	public void testFormData2Json() {
		HttpServletRequest request =EasyMock.createMock(HttpServletRequest.class);
		EasyMock.expect(request.getParameter("name")).andReturn("test-name").anyTimes();
		EasyMock.expect(request.getParameterValues("items[]")).andReturn(new String[] {
				"item1",
				"item2",
				"item3"
		}).anyTimes();
		EasyMock.replay(request);
		List<String> paramList = new ArrayList<String>();
		paramList.add("name");
		paramList.add("items[]");
		String result = FormDataUtils.formData2Json(request, paramList);
		System.out.println(result);
		assertEquals(false, "{}".equals(result));
	}

}
