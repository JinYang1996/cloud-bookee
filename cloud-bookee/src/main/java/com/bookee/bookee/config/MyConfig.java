package com.bookee.bookee.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bookee.bookee.filter.RequestSpecialCharacterFilter;
import com.bookee.bookee.filter.UrlFilter;
import com.bookee.bookee.filter.XssFilter;
import com.bookee.bookee.interceptor.AdminLoginInterceptor;
import com.bookee.bookee.interceptor.LogInterceptor;
import com.bookee.bookee.interceptor.OldLoginInterceptor;

@Configuration
public class MyConfig implements WebMvcConfigurer {
	
	@Autowired
	UrlFilter urlFilter;
	
	@Autowired
	LogInterceptor logInterceptor;
	
	@Autowired
	OldLoginInterceptor oldLoginInterceptor;
	
	@Autowired
	AdminLoginInterceptor adminLoginInterceptor;
	
	@Autowired
	XssFilter xssFilter;
	
	@Autowired
	RequestSpecialCharacterFilter requestSpecialCharacterFilter;

	public FilterRegistrationBean<UrlFilter> urlFilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(urlFilter);
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
		
	}
	
	public FilterRegistrationBean<UrlFilter> xssFilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(xssFilter);
		filterRegistrationBean.setFilter(requestSpecialCharacterFilter);
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*,/drools/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(logInterceptor);
		
		registry.addInterceptor(oldLoginInterceptor).addPathPatterns("/system/user/oldLogin");
		
		registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/system/user/*").excludePathPatterns("/system/user/oldLogin");
	}
}
