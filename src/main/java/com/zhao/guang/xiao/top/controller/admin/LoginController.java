package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 17:24
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;



    @GetMapping
    public String loginPage(){
        return "admin/login";
    }


    @PostMapping("login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        UserBean userBean = userService.checkUser(userName, password);
        if (null != userBean){
            session.setAttribute("user",userBean);
            return "redirect:/admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码不正确");
            return "redirect:/admin";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }



}
