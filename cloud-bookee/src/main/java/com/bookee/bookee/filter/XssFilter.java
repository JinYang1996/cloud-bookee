package com.bookee.bookee.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class XssFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);
	public List<String> excludes = new ArrayList<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("xss filter init ====================");
		if (logger.isDebugEnabled()) {
			logger.debug("xss filter init ====================");
		}
		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; url != null && i < url.length; i++) {
				excludes.add(url[i]);
			}
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("xss filter is open");
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String requestUrl = req.getRequestURI();
		if (handleExcludeURL(req, resp)) {
			chain.doFilter(request, response);
			return;
		}

		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {

		if (excludes == null || excludes.isEmpty()) {
			return false;
		}

		String url = request.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}

		return false;
	}

}
