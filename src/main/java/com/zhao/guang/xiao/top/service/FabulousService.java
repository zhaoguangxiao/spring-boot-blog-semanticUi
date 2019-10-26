package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.FabulousBean;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 10:11
 */
public interface FabulousService {


    /**
     * 添加一个点赞/反对
     *
     * @param fabulousBean
     */
    void saveFabulousBean(FabulousBean fabulousBean);

}
