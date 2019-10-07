package com.zhao.guang.xiao.top.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 拦截后台登录请求
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 9:51
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ( null != request.getSession().getAttribute("user")){
            return true;
        }else {
            response.sendRedirect("/admin");
            return false;
        }
    }
}
