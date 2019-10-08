package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogCategoryRepository;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 14:33
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {


    @Autowired
    private BlogCategoryRepository blogCategoryRepository;



    @Override
    @Transactional
    public void saveTypeBean(TypeBean type) {
         blogCategoryRepository.save(type);
    }

    @Override
    public TypeBean getTypeBean(Long id) {
        return blogCategoryRepository.getOne(id);
    }

    @Override
    public Page<TypeBean> listTypeBean(Pageable pageable) {
        return blogCategoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public TypeBean updateTypeBean(TypeBean typeBean) {
        return blogCategoryRepository.save(typeBean);
    }

    @Override
    @Transactional
    public void removeTypeBean(Long id) {
        blogCategoryRepository.deleteById(id);
    }


    @Override
    public TypeBean getTypeBeanByName(String name) {
        return blogCategoryRepository.findByName(name);
    }
}