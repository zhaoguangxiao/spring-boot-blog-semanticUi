package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/17 14:47
 */
@Controller
public class FrontEndBlogBeanController {


    @Autowired
    private BlogService blogService;




    @ResponseBody
    @PostMapping("addView")
    public Integer increaseViewCount(Long id) {
        //根据id添加 浏览量
        blogService.updateByViewCount(id);
        //查出当前浏览量
        BlogBean blogBean = blogService.getBlogBean(id);
        //返回当前添加后浏览量
        return blogBean.getViews();
    }



}
