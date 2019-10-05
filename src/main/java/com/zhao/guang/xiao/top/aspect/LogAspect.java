package com.zhao.guang.xiao.top.aspect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/5 14:45
 */
@Slf4j
@Aspect //进行切面操作
@Component  //使springboot可以扫描到这个类
public class LogAspect {

    @Pointcut("execution(* com.zhao.guang.xiao.top.controller.*.*(..))")
    public void log() {

    }

    /**
     * 日志执行之前
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        log.info("Request : {}", requestLog);
    }

    /**
     * 日志执行之后
     */
    @After("log()")
    public void doAfter() {
        log.info("------after------");
    }

    /**
     * 日志返回给用户结果
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        log.info("reslt----- 返回结果" + result);
    }


    @Data
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog() {
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

    }


}
