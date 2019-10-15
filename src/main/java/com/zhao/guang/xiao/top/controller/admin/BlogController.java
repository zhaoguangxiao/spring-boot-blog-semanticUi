package com.zhao.guang.xiao.top.controller.admin;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.po.UserBean;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 10:02
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;


    @Autowired
    private BlogLabelService blogLabelService;

    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       BlogBean blogBean,
                       Model model) {
        Page<BlogBean> blogBeans = blogService.ListBlogBean(pageable, blogBean);
        //文章标签列表
        List<TypeBean> categorys = blogCategoryService.listBlogCategorys();
        model.addAttribute("categorys", categorys);
        model.addAttribute("blogBeans", blogBeans);
        return "admin/article/list";
    }


    @PostMapping("search")
    public String search(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         String title,
                         Boolean recommend,
                         TypeBean typeBean,
                         Model model) {
        BlogBean blogBean = new BlogBean();
        blogBean.setTypeBean(typeBean);
        blogBean.setTitle(title);
        blogBean.setRecommend(recommend);
        log.info("{}", blogBean);
        Page<BlogBean> blogBeans = blogService.ListBlogBean(pageable, blogBean);
        model.addAttribute("blogBeans", blogBeans);
        return "admin/article/list::list_article";
    }


    @GetMapping("/blog")
    public String form(Model model) {
        //查询全部分类
        List<TypeBean> categorys = blogCategoryService.listBlogCategorys();
        //查询全部标签
        List<TagBean> tagBeans = blogLabelService.listTagBeans();
        model.addAttribute("categorys", categorys);
        model.addAttribute("tagBeans", tagBeans);
        model.addAttribute("blogBean", new BlogBean());
        return "admin/article/form";
    }


    @GetMapping("blog/{id}")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        BlogBean blogBean = blogService.getBlogBean(id);
        if (null == blogBean) {
            throw new NotFountException("当前博客文章不存在无法进行编辑");
        }
        setBlogBeanCategoryAndLabel(model);
        model.addAttribute("blogBean", blogBean);
        return "admin/article/form";
    }

    private void setBlogBeanCategoryAndLabel(Model model) {
        //查询全部分类
        List<TypeBean> categorys = blogCategoryService.listBlogCategorys();
        //查询全部标签
        List<TagBean> tagBeans = blogLabelService.listTagBeans();
        model.addAttribute("categorys", categorys);
        model.addAttribute("tagBeans", tagBeans);
    }


    @PostMapping("blog")
    public String saveBlogBean(@Valid BlogBean blogBean,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               Model model) {
        if (bindingResult.hasErrors()) {
            setBlogBeanCategoryAndLabel(model);
            return "admin/article/form";
        }
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        if (null == user) {
            throw new NotFountException("当前尚未登录,无法添加博客");
        }
        blogBean.setUserBean(user);
        blogService.saveBlogBean(blogBean);
        return "redirect:/admin/blogs";
    }


    @DeleteMapping("blog/{id}")
    public String deleteBlogBean(@PathVariable("id") Long id) {
        blogService.removeBlogBean(id);
        return "redirect:/admin/blogs";
    }


}
