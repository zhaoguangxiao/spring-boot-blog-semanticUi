package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.BlogBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:43
 */
public interface BlogBeanRepository extends JpaRepository<BlogBean, Long>, JpaSpecificationExecutor<BlogBean> {


    @Query("select blog from BlogBean blog where blog.recommend = true ")
    List<BlogBean> recommendBlogs(Pageable pageable);




    @Query("select blog from BlogBean blog where blog.title like ?1 or blog.content like ?1")
    Page<BlogBean> findBlogBeanBySearch(String search,Pageable pageable);

}
