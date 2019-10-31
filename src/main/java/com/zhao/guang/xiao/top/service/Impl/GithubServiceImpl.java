package com.zhao.guang.xiao.top.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.GithubService;
import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 9:39
 */
@Service
public class GithubServiceImpl implements GithubService {


    @Autowired
    private UserService userService;


    private static String stats = null;


    @Override
    public String genState() {
        return stats = UUID.randomUUID().toString();
    }


    @Override
    public boolean checkState(String status) {
        return stats.equals(status);
    }

    @Override
    public UserBean saveGithubUser(String result) {
        JSONObject fromObject = JSON.parseObject(result);

        //唯一标识id
        String userid = fromObject.getString("id");
        String nickName = fromObject.getString("name");
        String avatar_url = fromObject.getString("avatar_url");

        UserBean userBean = new UserBean();
        userBean.setNickName(nickName);
        userBean.setAvatar(avatar_url);
        userBean.setType(UserBean.USER_GITHUB);
        userBean.setPassword(userid);
        //返回登录github对象
        return userService.saveGithub(userBean);
    }
}
