package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.TagBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    List<TagBean> listTagBeans();

    List<TagBean> listTagBean(Integer size);


}
