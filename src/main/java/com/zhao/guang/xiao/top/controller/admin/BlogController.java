package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import com.zhao.guang.xiao.top.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 10:02
 */
@Controller
@RequestMapping("/admin")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 10, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       BlogBean blogBean,
                       Model model) {
        Page<BlogBean> blogBeans = blogService.ListBlogBean(pageable, blogBean);
        //文章标签列表
        List<TypeBean> categorys = blogCategoryService.listBlogCategorys();
        model.addAttribute("categorys", categorys);
        model.addAttribute("blogBeans", blogBeans);
        return "admin/article/list";
    }

    @GetMapping("/blog")
    public String form() {
        return "admin/article/form";
    }


    @GetMapping("blog/{id}")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        BlogBean blogBean = blogService.getBlogBean(id);
        if (null == blogBean) {
            throw new NotFountException("当前博客文章不存在无法进行编辑");
        }
        model.addAttribute("blogBean", blogBean);
        return "admin/article/form";
    }


    @PostMapping("blog")
    public String saveBlogBean(@Valid BlogBean blogBean,
                               BindingResult bindingResult,
                               Model model) {
        return "redirect:/admin/blogs";
    }


    @PutMapping("blog")
    public String updateBlogBean(@Valid BlogBean blogBean,
                                 BindingResult bindingResult,
                                 Model model) {

        return "redirect:/admin/blogs";
    }


    @DeleteMapping("blog/{id}")
    public String deleteBlogBean(@PathVariable("id") Long id) {
        blogService.removeBlogBean(id);
        return "redirect:/admin/blogs";
    }


}
