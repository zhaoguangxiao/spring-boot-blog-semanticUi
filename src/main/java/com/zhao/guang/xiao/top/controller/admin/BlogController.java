package com.zhao.guang.xiao.top.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 10:02
 */
@Controller
@RequestMapping("/admin")
public class BlogController {


    @RequestMapping("/blog/list")
    public String list(){
        return "admin/article/list";
    }


    @RequestMapping("/blog/form")
    public String form(){
        return "admin/article/form";
    }










}
