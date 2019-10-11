package com.zhao.guang.xiao.top.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/11 10:12
 */
@Controller
@RequestMapping("admin")
public class AdminController {


    @RequestMapping("index")
    public String index(){
        return "admin/index";
    }

}
