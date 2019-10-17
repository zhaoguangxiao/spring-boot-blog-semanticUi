package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.BlogBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:39
 */
public interface BlogService {


    BlogBean getBlogBean(Long id);

    Page<BlogBean> ListBlogBean(Pageable pageable, BlogBean blogBean);

    BlogBean saveBlogBean(BlogBean blogBean);

    void removeBlogBean(Long id);

    List<BlogBean> listBlogBean();

    Page<BlogBean> findPage(Pageable pageable);

    List<BlogBean> recommendBlogs(Integer size);

    Page<BlogBean> listBlogBeanBySearch(String search, Pageable pageable);

    BlogBean getFrontEndBlogDetail(Long id);

    void updateByViewCount(Long id);
}
