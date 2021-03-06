package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.UserBean;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 17:17
 */
public interface UserService {

    UserBean checkUser(String uname, String pwd);

    UserBean save(UserBean userBean);

    UserBean saveGithub(UserBean userBean);

    UserBean userManger();

    List<UserBean> findUserBeanByTypeId(int typeId);

    UserBean getOne(Long id);


    UserBean saveQQ(UserBean userBean);

}
