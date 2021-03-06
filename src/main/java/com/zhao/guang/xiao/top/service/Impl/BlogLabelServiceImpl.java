package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogLabelRepository;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Transactional(rollbackFor=Exception.class)
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
    @Transactional(rollbackFor=Exception.class)
    public void updateTagBean(TagBean tagBean) {
        blogLabelRepository.save(tagBean);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
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

    @Override
    public List<TagBean> listTagBean(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogBeans.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogLabelRepository.findTop(pageable);
    }
}
