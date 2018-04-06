package com.iwantrun.admin.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.iwantrun.admin.intercepter.LoginInterceptor;

@Configurable
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
	        				      "/ueditor1_2_2_0-utf8-php/**",
	        				      "/login.html",
	        				      "/login"
	        		}		
	        );
	        WebMvcConfigurer.super.addInterceptors(registry);
	    }

}
