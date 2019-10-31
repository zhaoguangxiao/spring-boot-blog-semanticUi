package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.UserBeanRepository;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBean save(UserBean userBean) {
        if (null == userBean.getId()) {
            //创建时间
            userBean.setCreateTime(System.currentTimeMillis());
            //更新时间
            userBean.setUpdateTime(System.currentTimeMillis());
        } else {
            //更新时间
            userBean.setUpdateTime(System.currentTimeMillis());
        }
        return userBeanRepository.save(userBean);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBean saveGithub(UserBean userBean) {
        //判断当前用户是否存在 根据密码 和 type
        UserBean bean = hasUserByTypeAndPwd(userBean.getPassword(), UserBean.USER_GITHUB);
        if (null == bean) {
            userBean.setCreateTime(System.currentTimeMillis());
            userBean.setUpdateTime(System.currentTimeMillis());
        } else {
            userBean.setId(bean.getId());
            userBean.setUpdateTime(System.currentTimeMillis());
        }
        userBeanRepository.save(userBean);
        return userBean;
    }

    @Override
    public UserBean userManger() {
        return userBeanRepository.findManagerUserBean();
    }

    @Override
    public List<UserBean> findUserBeanByTypeId(int typeId) {
        return userBeanRepository.findUserBeanByTypeId(typeId);
    }

    @Override
    public UserBean getOne(Long id) {
        return userBeanRepository.getOne(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBean saveQQ(UserBean userBean) {
        //判断当前qq用户是否存在
        UserBean bean = hasUserByTypeAndPwd(userBean.getPassword(), UserBean.USER_QQ);
        if (null == bean) {
            bean.setCreateTime(System.currentTimeMillis());
            bean.setUpdateTime(System.currentTimeMillis());
        } else {
            userBean.setId(bean.getId());
            bean.setUpdateTime(System.currentTimeMillis());
        }
        return userBeanRepository.save(bean);
    }


    private UserBean hasUserByTypeAndPwd(String pwd, int type) {
        return userBeanRepository.findByPasswordAndType(pwd, type);
    }
}
