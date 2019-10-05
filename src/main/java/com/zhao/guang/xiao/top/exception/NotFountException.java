package com.zhao.guang.xiao.top.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义博客没有找到异常类
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/5 14:24
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//定义这个异常返回的状态码
public class NotFountException extends RuntimeException {

    public NotFountException() {
    }


    public NotFountException(String message) {
        super(message);
    }


    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
