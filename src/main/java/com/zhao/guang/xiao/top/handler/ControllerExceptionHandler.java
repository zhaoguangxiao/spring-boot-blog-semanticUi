package com.zhao.guang.xiao.top.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/5 13:53
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 标识这个方法是可以做异常处理的
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        log.error("Request URL :{} , Exception :{}", request.getRequestURI(), exception);


        if (null != AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class)) {
            throw exception;
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", request.getRequestURI());
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
