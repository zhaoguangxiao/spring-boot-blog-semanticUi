package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.TagBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 16:37
 */
public interface BlogLabelService {

    void saveTagBean(TagBean tagBean);

    TagBean getTagBeanById(Long id);

    Page<TagBean> listTagBean(Pageable pageable);

    void updateTagBean(TagBean tagBean);

    void removeTagBeanById(Long id);

    TagBean getTagBeanByName(String name);


}
