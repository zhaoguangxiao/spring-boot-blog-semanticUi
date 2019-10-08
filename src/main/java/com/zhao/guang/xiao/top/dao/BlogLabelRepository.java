package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.TagBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 16:38
 */
public interface BlogLabelRepository  extends JpaRepository<TagBean,Long> {

    TagBean findByName(String name);

}
