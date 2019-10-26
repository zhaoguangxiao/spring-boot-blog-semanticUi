package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 20:37
 */
@Controller
@RequestMapping("admin")
public class UserController {


    @Autowired
    private UserService userService;




    @GetMapping("users")
    public String list(Model model){

        return "admin/user/list";
    }



}
