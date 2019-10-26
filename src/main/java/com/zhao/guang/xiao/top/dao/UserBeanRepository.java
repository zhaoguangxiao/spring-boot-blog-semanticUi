package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 17:21
 */
public interface UserBeanRepository extends JpaRepository<UserBean,Long> {


    UserBean findByUserNameAndPassword(String userName,String password);


    UserBean findByPasswordAndType(String password,Integer type);


    @Query("select bean from UserBean bean where bean.userName is not null")
    UserBean findManagerUserBean();

}
