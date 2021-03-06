package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogCategoryRepository;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
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
 * @date 2019/10/7 14:33
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {


    @Autowired
    private BlogCategoryRepository blogCategoryRepository;



    @Override
    @Transactional(rollbackFor=Exception.class)
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
    @Transactional(rollbackFor=Exception.class)
    public TypeBean updateTypeBean(TypeBean typeBean) {
        return blogCategoryRepository.save(typeBean);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void removeTypeBean(Long id) {
        blogCategoryRepository.deleteById(id);
    }


    @Override
    public TypeBean getTypeBeanByName(String name) {
        return blogCategoryRepository.findByName(name);
    }

    @Override
    public List<TypeBean> listBlogCategorys() {
        return blogCategoryRepository.findAll();
    }


    @Override
    public List<TypeBean> listBlogCategory(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogBeanList.size");
        Pageable pageable =PageRequest.of(0,size,sort);
        return blogCategoryRepository.findTop(pageable);
    }
}
