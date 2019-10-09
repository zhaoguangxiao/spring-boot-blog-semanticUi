package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.BlogBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:43
 */
public interface BlogBeanRepository extends JpaRepository<BlogBean,Long>, JpaSpecificationExecutor<BlogBean> {

}
