package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import com.zhao.guang.xiao.top.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/21 17:14
 */
@Controller
public class FrontEndLabelController {


    @Autowired
    private BlogLabelService blogLabelService;


    @Autowired
    private BlogService blogService;


    @GetMapping("label/{labelId}")
    public String toLabelPage(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                              @PathVariable Long labelId,
                              Model model){

        //查询全部标签数据
        List<TagBean> tagBeans = blogLabelService.listTagBeans();
        TagBean typeBean = new TagBean();
        if (0 == labelId) {
            labelId = tagBeans.get(0).getId();
            typeBean.setId(labelId);
        } else {
            typeBean.setId(labelId);
        }
        model.addAttribute("tagBeans", tagBeans);
        Page<BlogBean> blogBeans = blogService.listTagBean(pageable, labelId);
        model.addAttribute("pages", blogBeans);
        model.addAttribute("selectionId", labelId);
        return "tags";
    }



}
