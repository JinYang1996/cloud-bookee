package com.bookee.bookee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UrlFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(UrlFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig){
		logger.info("初始化过滤器：{}",filterConfig.getFilterName());
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		String url = request.getRequestURI();
		logger.info("进入urlFilter。。。。。");
		long startTime = System.currentTimeMillis();
		filterChain.doFilter(servletRequest, servletResponse);
		long endTime = System.currentTimeMillis();
		logger.info("请求处理完毕，处理时间：{}",endTime-startTime);
		
	}

}
