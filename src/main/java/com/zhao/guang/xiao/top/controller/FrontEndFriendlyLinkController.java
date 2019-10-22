package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.po.FriendlyLinkBean;
import com.zhao.guang.xiao.top.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/21 21:47
 */
@Controller
public class FrontEndFriendlyLinkController {


    @Autowired
    private FriendlyLinkService friendlyLinkService;


    @ResponseBody
    @GetMapping("links")
    public List<FriendlyLinkBean> listsFriendlyLink() {
        return friendlyLinkService.listFriendlyLink();
    }


}
