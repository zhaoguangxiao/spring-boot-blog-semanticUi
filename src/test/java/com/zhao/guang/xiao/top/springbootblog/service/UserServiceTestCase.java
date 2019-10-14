package com.zhao.guang.xiao.top.springbootblog.service;

import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.UserService;
import com.zhao.guang.xiao.top.springbootblog.SpringBootBlogApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/14 10:22
 */
public class UserServiceTestCase extends SpringBootBlogApplicationTests {


    @Autowired
    private UserService userService;


    @Test
    public void assertNotNull(){
        Assert.assertNotNull(userService);
    }


    @Test
    public void createAdmin(){
        UserBean bean = new UserBean();
        //头像
        bean.setAvatar("https://picsum.photos/id/450/700/400");
        bean.setEmail("1764773283@qq.com");
        bean.setPassword(Md5Pwd("admin"));
        bean.setNickName("青春似水流年");
       // bean.setType();
        bean.setUserName("赵广晓");
        userService.save(bean);
    }



    private String Md5Pwd(String pwd){
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }



}
