package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.CommentBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.service.CommentService;
import com.zhao.guang.xiao.top.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/16 15:38
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;


    @Autowired
    private BlogService blogService;


    @GetMapping("comments/{blogId}")
    public String commentList(@PathVariable Long blogId,
                              Model model) {
        List<CommentBean> commentBeans = commentService.ListCommentBeanByBlogIdAndParentisNull(blogId);
        model.addAttribute("commentBeans", commentBeans);
        return "details :: comment-list";
    }


    @PostMapping("comments")
    public String saveCommentBean(CommentBean commentBean, HttpServletRequest request) {
        UserBean entity = (UserBean) request.getSession().getAttribute("userEntity");
        if (null == entity) {
            throw new NotFountException("当前未登录,无法进行留言,请先登录");
        }
        //保存评论人
        commentBean.setCommentator(entity);
        Long blogId = commentBean.getBlogBean().getId();
        commentBean.setBlogBean(blogService.getBlogBean(blogId));
        //添加一个留言
        commentService.saveCommentBean(commentBean);

        return "redirect:/comments/" + blogId;
    }


}
