package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.FabulousBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.FabulousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 10:10
 */
@Controller
public class FabulousController {

    @Autowired
    private FabulousService fabulousService;


    /**
     * 点赞处理事件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("addlike")
    public Map<String, String> addBlogLike(HttpServletRequest request,
                                           @NotBlank(message = "博客id不能为空") Long id,
                                           Integer typeId) {
        HashMap<String, String> hashMap = new HashMap<>();
        UserBean entity = (UserBean) request.getSession().getAttribute("userEntity");
        if (null == entity) {
            hashMap.put("code", "100");
            hashMap.put("message", "你当前还没登录,无法点赞");
            return hashMap;
        } else {
            BlogBean blogBean = new BlogBean();
            blogBean.setId(id);
            FabulousBean bean = new FabulousBean(entity, blogBean, typeId);
            boolean fabulousBean = fabulousService.saveFabulousBean(bean);
            if (fabulousBean){
                //添加点赞/反对成功
                hashMap.put("code", "200");
            }
            hashMap.put("message", "你已经点过赞了,不能重复点赞");
            return hashMap;
        }
    }


}
