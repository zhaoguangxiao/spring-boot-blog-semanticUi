package com.zhao.guang.xiao.top.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.CommentBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.*;
import com.zhao.guang.xiao.top.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private CommentService commentService;


    @Autowired
    private SelectedArticlesService selectedArticlesService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 5) Pageable pageable,
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
        //热门文章 选取浏览量最高的几篇文章
        List<BlogBean> blogBeans = blogService.recommendBlogs(5);
        model.addAttribute("blogBeans", blogBeans);
        //最新评论 倒序排列评论
        List<CommentBean> commentBeanList = commentService.listCommentBeanByGroupBlogIdAndParentId(5);
        model.addAttribute("commentBeanList", commentBeanList);
        return "index";
    }


    @GetMapping("search")
    public String search(@PageableDefault(size = 5) Pageable pageable,
                         @RequestParam String search,
                         Model model) {
        Page<BlogBean> searchs = blogService.listBlogBeanBySearch(search, pageable);
        model.addAttribute("searchs", searchs);
        model.addAttribute("search", search);
        return "search";
    }


    @GetMapping("about")
    public String toAbout() {
        return "about";
    }


    @GetMapping("blog/{id}")
    public String details(@PathVariable Long id,
                          Model model) {
        BlogBean blogBean = blogService.getFrontEndBlogDetail(id);
        model.addAttribute("blogBean", blogBean);
        //查出当前文章的评论
        List<CommentBean> commentBeans = commentService.ListCommentBeanByBlogIdAndParentisNull(id);
        model.addAttribute("commentBeans", commentBeans);
        return "details";
    }


    @GetMapping("manager-category")
    public String managerCategory() {
        return "admin/publish";
    }


    @ResponseBody
    @GetMapping("scheduled")
    public Map<String, String> scheduledMethod() {
        return selectedArticlesService.getSelectedArticles();
    }



    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("userEntity");
        return "redirect:/";
    }
}
