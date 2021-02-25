package com.bookee.bookee.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.error("----AdminLoginInterceptor-----preHandle");
		logger.error("RequestURL:{}",request.getRequestURL());
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
    }

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.error("----AdminLoginInterceptor-----postHandle");
		logger.error("RequestURL:{}",request.getRequestURL());
	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.error("----AdminLoginInterceptor-----afterCompletion");
		long endTime = System.currentTimeMillis();
		long startTime = (long) request.getAttribute("startTime");
		logger.error("RequestURL:{}，该方法用时:{}",request.getRequestURL(),endTime-startTime);
		
	}
}
