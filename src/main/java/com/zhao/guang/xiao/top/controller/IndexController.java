package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import com.zhao.guang.xiao.top.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/3 10:01
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;


    @Autowired
    private BlogCategoryService blogCategoryService;


    @Autowired
    private BlogLabelService blogLabelService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        //保存文章列表
        Page<BlogBean> page = blogService.findPage(pageable);
        model.addAttribute("pages", page);
        //文章分类
        List<TypeBean> typeBeans = blogCategoryService.listBlogCategory(6);
        model.addAttribute("typeBeans", typeBeans);
        //文章标签
        List<TagBean> tagBeanList = blogLabelService.listTagBean(10);
        model.addAttribute("tagBeanList", tagBeanList);
        //最新推荐      recommend
        List<BlogBean> blogBeans = blogService.recommendBlogs(5);
        model.addAttribute("blogBeans", blogBeans);
        return "index";
    }


    @GetMapping("search")
    public String search(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String search,
                         Model model) {
        Page<BlogBean> searchs = blogService.listBlogBeanBySearch(search, pageable);
        model.addAttribute("searchs", searchs);
        model.addAttribute("search", search);
        return "search";
    }


    @GetMapping("categary")
    public String toCategary() {
        return "category";
    }


    @GetMapping("tag")
    public String toTags() {
        return "tags";
    }


    @GetMapping("archives")
    public String toArchives() {
        return "archives";
    }


    @GetMapping("about")
    public String toAbout() {
        return "about";
    }


    @GetMapping("blog/{id}")
    public String details(@PathVariable Long id,
                          Model model) {
        BlogBean blogBean = blogService.getBlogBean(id);
        model.addAttribute("blogBean", blogBean);
        return "details";
    }



    @GetMapping("manager-category")
    public String managerCategory() {
        return "admin/publish";
    }


    @GetMapping("{id}/{name}")
    public String aop(@PathVariable Integer id,
                      @PathVariable String name) {
        log.info("-------aop -------");
        return "index";
    }

}
