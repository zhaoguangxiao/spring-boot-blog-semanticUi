package com.zhao.guang.xiao.top.springbootblog.service;

import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.springbootblog.SpringBootBlogApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/12 10:11
 */
@Slf4j
public class BlogServiceTestCase extends SpringBootBlogApplicationTests {


    @Autowired
    private BlogService blogService;


    @Test
    public void assertNotNull(){
        Assert.assertNotNull(blogService);
    }


    @Test
    public void findBlogBeanByKey(){
        BlogBean blogBean = blogService.getBlogBean(1111L);
        log.info("{}",blogBean);
    }



    @Test
    public void listBlogBean(){
        List<BlogBean> blogBeans = blogService.listBlogBean();
        log.info("{}",blogBeans);
    }

}
