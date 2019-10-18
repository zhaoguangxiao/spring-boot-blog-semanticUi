package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.CommentBean;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/16 15:58
 */
public interface CommentService {

    List<CommentBean> listCommentBeanByBlogId(Long blogId);


    CommentBean saveCommentBean(CommentBean commentBean);


    List<CommentBean> ListCommentBeanByBlogIdAndParentisNull(Long blogId);

    List<CommentBean> listCommentBeanByGroupBlogIdAndParentId(Integer size);




}
