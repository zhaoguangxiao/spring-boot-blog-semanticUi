package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
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
 * @date 2019/10/21 14:07
 */
@Controller
public class FrontEndCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;


    @Autowired
    private BlogService blogService;


    @GetMapping("categary/{id}")
    public String toCategary(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             @PathVariable Long id,
                             Model model) {
        //查询全部分类数据
        List<TypeBean> typeBeans = blogCategoryService.listBlogCategory(10000);
        TypeBean typeBean = new TypeBean();
        if (0 == id) {
            id = typeBeans.get(0).getId();
            typeBean.setId(id);
        } else {
            typeBean.setId(id);
        }
        model.addAttribute("typeBeans", typeBeans);
        BlogBean bean = new BlogBean();
        bean.setTypeBean(typeBean);
        Page<BlogBean> blogBeans = blogService.ListBlogBean(pageable, bean);
        model.addAttribute("pages", blogBeans);
        model.addAttribute("selectionId", id);
        return "category";
    }


}
