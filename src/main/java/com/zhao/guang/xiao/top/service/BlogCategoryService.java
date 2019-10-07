package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.TypeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 14:30
 */
public interface BlogCategoryService {

    TypeBean saveTypeBean(TypeBean type);

    TypeBean getTypeBean(Long id);

    Page<TypeBean> listTypeBean(Pageable pageable);


    TypeBean updateTypeBean(TypeBean typeBean);

    void removeTypeBean(Long id);

}
