package com.bookee.bookee.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("----LogInterceptor-----preHandle");
		logger.info("RequestURL:{}",request.getRequestURL());
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
    }

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("----LogInterceptor-----postHandle");
		logger.info("RequestURL:{}",request.getRequestURL());
	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("----LogInterceptor-----afterCompletion");
		long endTime = System.currentTimeMillis();
		long startTime = (long) request.getAttribute("startTime");
		logger.info("RequestURL:{}，该方法用时:{}",request.getRequestURL(),endTime-startTime);
		
	}
}
