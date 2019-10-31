package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.UserBean;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 9:39
 */
public interface GithubService {

    String genState();

    boolean checkState(String status);


    UserBean saveGithubUser(String result);

}
