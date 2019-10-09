package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogLabelRepository;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 16:37
 */
@Service
public class BlogLabelServiceImpl implements BlogLabelService {


    @Autowired
    private BlogLabelRepository blogLabelRepository;


    @Override
    @Transactional
    public void saveTagBean(TagBean tagBean) {
        blogLabelRepository.save(tagBean);
    }

    @Override
    public TagBean getTagBeanById(Long id) {
        return blogLabelRepository.getOne(id);
    }

    @Override
    public Page<TagBean> listTagBean(Pageable pageable) {
        return blogLabelRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateTagBean(TagBean tagBean) {
        blogLabelRepository.save(tagBean);
    }

    @Override
    @Transactional
    public void removeTagBeanById(Long id) {
        blogLabelRepository.deleteById(id);
    }

    @Override
    public TagBean getTagBeanByName(String name) {
        return blogLabelRepository.findByName(name);
    }

    @Override
    public List<TagBean> listTagBeans() {
        return blogLabelRepository.findAll();
    }
}
