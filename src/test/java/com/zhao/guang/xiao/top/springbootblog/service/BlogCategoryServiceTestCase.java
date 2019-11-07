package com.zhao.guang.xiao.top.springbootblog.service;

import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import com.zhao.guang.xiao.top.springbootblog.SpringBootBlogApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/12 10:42
 */
@Slf4j
public class BlogCategoryServiceTestCase extends SpringBootBlogApplicationTests {


    @Autowired
    private BlogCategoryService blogCategoryService;


    @Test
    public void assertNotNull() {
        Assert.assertNotNull(blogCategoryService);
    }


    @Test
    public void getOne() {
//        TypeBean typeBean = blogCategoryService.getTypeBean(21L);
//        log.debug("{}", typeBean);
    }

    @Test
    public void test(){

    }


}
