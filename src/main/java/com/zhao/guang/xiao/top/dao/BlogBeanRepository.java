package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.BlogBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:43
 */
public interface BlogBeanRepository extends JpaRepository<BlogBean, Long>, JpaSpecificationExecutor<BlogBean> {


    @Query("select blog from BlogBean blog")
    List<BlogBean> recommendBlogs(Pageable pageable);


    @Query("select blog from BlogBean blog where blog.title like ?1 or blog.content like ?1ORDER BY blog.top DESC,blog.createTime DESC")
    Page<BlogBean> findBlogBeanBySearch(String search, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update BlogBean blog set blog.views= blog.views + 1 where blog.id = :#{#id}")
    void updateByViewCount(@Param("id") Long id);


    @Query("select blog from BlogBean blog  ORDER BY blog.top DESC,blog.createTime DESC")
    Page<BlogBean> findAllByGroupByTop(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update BlogBean blog set blog.likeCount = blog.likeCount + 1 where blog.id = :#{#id}")
    void updateByLikeCount(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query("update BlogBean blog set blog.oppositionCount = blog.oppositionCount + 1 where blog.id = :#{#id}")
    void updateByoppositionCount(@Param("id") Long id);
}
