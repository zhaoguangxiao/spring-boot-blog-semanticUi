package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.FabulousBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 10:12
 */
public interface FabulousRepository extends JpaRepository<FabulousBean, Long> {


    @Query("select bean from FabulousBean bean where bean.userBean.id =?1 and bean.blogBean.id = ?2")
    FabulousBean findByUserIdAndBlogId(Long userId, Long blogId);

}
