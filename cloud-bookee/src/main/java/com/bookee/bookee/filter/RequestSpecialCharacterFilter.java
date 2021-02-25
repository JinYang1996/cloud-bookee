package com.bookee.bookee.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class RequestSpecialCharacterFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestSpecialCharacterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //1 判断开关状态
        //logger.error("请求特殊字符过滤校验！！！");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestUrl = req.getRequestURI();
        boolean isPass = false;

        //logger.error("请求类型："+request.getContentType());
        String reqContentType = request.getContentType();
        //!"application/x-www-form-urlencoded".equals(reqContentType)
        if(StringUtils.isNotBlank(reqContentType) && !reqContentType.contains("form")) {
            logger.error("非表单请求，开始过滤特殊字符："+requestUrl);
            ServletRequest requestWrapper = new RequestSpecialCharacterWrapper(req);
            filterChain.doFilter(requestWrapper, response);
        }else{
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}
