package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/22 10:32
 */
@Controller
public class FrontEndArchivesController {


    @Autowired
    private BlogService blogService;


    @GetMapping("archives")
    public String toArchives(Model model) {
        Map<String, List<BlogBean>> listMap = blogService.archiverBlogBean();
        model.addAttribute("archives", listMap);
        model.addAttribute("size", blogService.countBlogBean());
        return "archives";
    }
}
