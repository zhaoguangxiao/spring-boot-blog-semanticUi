package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.UserBeanRepository;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 17:18
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserBeanRepository userBeanRepository;


    @Override
    public UserBean checkUser(String uname, String pwd) {
//        获取加密后的密码与数据库密码进行对比
        String md5Password = DigestUtils.md5DigestAsHex(pwd.getBytes());
        return userBeanRepository.findByUserNameAndPassword(uname, md5Password);
    }
}
