package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/11 10:12
 */
@Controller
@RequestMapping("admin")
public class AdminController {


    @Autowired
    private NoticeService noticeService;


    @RequestMapping("index")
    public String index(Model model) {
        //查出全部未读的通知
        int unread = noticeService.findNoticeBeanByUnread();
        model.addAttribute("unread", unread);
        return "admin/index";
    }

}
