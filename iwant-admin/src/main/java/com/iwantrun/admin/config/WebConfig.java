package com.iwantrun.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.iwantrun.admin.intercepter.LoginInterceptor;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackageClasses=WebConfig.class)
@Component
public class WebConfig  implements WebMvcConfigurer{

	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        //拦截规则：除了login，其他都拦截判断
	        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
	        .excludePathPatterns(
	        		new String[] {"/css/**",
	        				      "/icons/**",
	        				      "/images/**",
	        				      "/js/**",
	        				      "/json/**",
	        				      "/ueditor1_4_3_3-utf8-jsp/**",
	        				      "/login.html",
	        				      "/login",
	        				      "/getLoginToken"
	        		}		
	        );
	        WebMvcConfigurer.super.addInterceptors(registry);
	    }

}
