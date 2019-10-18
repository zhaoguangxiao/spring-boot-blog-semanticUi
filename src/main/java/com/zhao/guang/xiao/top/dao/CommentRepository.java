package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.CommentBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/16 15:59
 */
public interface CommentRepository extends JpaRepository<CommentBean, Long> {


    List<CommentBean> findByBlogBeanId(Long blogId, Sort sort);


    List<CommentBean> findByBlogBeanIdAndParentCommentNull(Long blogId, Sort sort);



    @Query("select commentBean from CommentBean commentBean group by commentBean.blogBean.id order by commentBean.createTime desc ")
    List<CommentBean> findAllCommentBeanByGroupBlogIdAndParentId(Pageable pageable);
}
