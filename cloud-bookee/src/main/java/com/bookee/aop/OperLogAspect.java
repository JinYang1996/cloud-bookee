package com.bookee.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class OperLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(OperLogAspect.class);

    @Pointcut("execution(public * com.cpic.partshare.business.*.controller.*.*(..)) " +
            "|| execution(public * com.cpic.partshare.business.*.*Controller.*(..)) " +
            "|| execution(public * com.cpic.partshare.rule.controller.*.*(..)) " +
            "|| execution(public * com.cpic.partshare.servlet.*.*(..)) " +
            "|| execution(public * com.cpic.partshare.system.*.*Controller.*(..)) " +
            "|| execution(public * com.cpic.partshare.system.*.*.*Controller.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog(){
        //签名，可以理解成这个切入点的一个名称
        logger.info("切面处理");
    }

    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        String params = "";
        try {
            params = IOUtils.toString(request.getReader());
            if(StringUtils.isBlank(params)){
                params = JSON.toJSONString(request.getParameterMap());
            }
        } catch (IOException e) {
            e.getMessage();
        }
        // 记录下请求内容
        logger.error("请求开始时间：{}，请求接口 : {}，请求参数：{}" ,"",request.getRequestURL().toString(),params);
    }
    
    @After("controllerLog()")
    public void doAfter() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        logger.error("请求结束时间：{}，请求接口 : {}" ,"",request.getRequestURL().toString());
    }

    @AfterReturning(returning = "object", pointcut = "controllerLog()")
    public void doAfterReturning(Object object) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        logger.error("请求返回时间：{}，请求接口 : {}，返回数据：{}" ,"",request.getRequestURL().toString(), JSONObject.toJSONString(object));
    }
}
