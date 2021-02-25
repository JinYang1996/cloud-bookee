package com.bookee.bookee.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class OldLoginInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(OldLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.error("----OldLoginInterceptor-----preHandle");
		logger.error("RequestURL:{},ContextPath:{}",request.getRequestURL(),request.getContextPath());
		response.sendRedirect("/system/user/login");
		return false;
    }

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.error("----OldLoginInterceptor-----postHandle");
		logger.error("RequestURL:{}",request.getRequestURL());
		logger.error("这个接口已经废弃:{}",request.getRequestURL());
	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		
	}
}
