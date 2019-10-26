package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping("manager")
    public String list(Model model) {
        UserBean userBean = userService.userManger();
        model.addAttribute("qqcount", userService.findUserBeanByTypeId(UserBean.USER_QQ).size());
        model.addAttribute("githubcount", userService.findUserBeanByTypeId(UserBean.USER_GITHUB).size());
        model.addAttribute("userBean", userBean);
        return "admin/user/list";
    }


    @GetMapping("manager/{id}")
    public String form(@PathVariable Long id,
                       Model model) {
        UserBean userBean = userService.getOne(id);
        if (null == userBean) {
            throw new NotFountException("当前管理员不存在请重试");
        }
        model.addAttribute("userBean", userBean);
        return "admin/user/form";
    }


    @PostMapping("manager")
    public String save(UserBean userBean) {
        userService.save(userBean);
        return "redirect:/admin/manager";
    }


}
