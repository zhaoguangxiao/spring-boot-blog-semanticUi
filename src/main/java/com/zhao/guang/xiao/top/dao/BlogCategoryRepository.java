package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.TypeBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 14:34
 */
public interface BlogCategoryRepository extends JpaRepository<TypeBean,Long> {


}
