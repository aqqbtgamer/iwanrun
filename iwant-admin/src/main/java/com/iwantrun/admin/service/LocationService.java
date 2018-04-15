package com.iwantrun.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwantrun.admin.constant.AdminApplicationConstants;
import com.iwantrun.admin.utils.CookieUtils;

@Service
public class LocationService {
	
	@Autowired  
    private Environment env;  
	
	@Autowired
    private RestTemplate restTemplate;
	
	@SuppressWarnings("unused")
	public String addLocation(HttpServletRequest request) {
		//1.解析传过来的参数
		//'activity_province_code',
        //'activity_city_code',
        //'activity_dist_code',
        //'location',
        //'mainImage',
        //'imgManage'
		String name = request.getParameter("name");
		String locationType = request.getParameter("location_type_code");
		String[] specialTagsArray = request.getParameterValues("special_tags[]");
		String groupNumber = request.getParameter("group_number_limit_code");
		String province = request.getParameter("activity_province_code");
		String city = request.getParameter("activity_city_code");
		String district = request.getParameter("activity_dist_code");
		String location = request.getParameter("location");
		String mainImage = request.getParameter("mainImage");
		String[] imgManage = request.getParameterValues("imgManage[]");
		String details = request.getParameter("_ue");
		String token = CookieUtils.getLoginToken(request);
		
		return AdminApplicationConstants.HTTP_RESULT_FAILED ;
	}

}
