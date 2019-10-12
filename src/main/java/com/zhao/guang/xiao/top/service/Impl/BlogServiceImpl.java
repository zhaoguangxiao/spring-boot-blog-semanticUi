package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogBeanRepository;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.BlogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:42
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogBeanRepository blogBeanRepository;


    @Override
    public BlogBean getBlogBean(Long id) {
        return blogBeanRepository.getOne(id);
    }

    @Override
    public Page<BlogBean> ListBlogBean(Pageable pageable, BlogBean blogBean) {
        return blogBeanRepository.findAll(new Specification<BlogBean>() {
            @Override
            public Predicate toPredicate(Root<BlogBean> root, CriteriaQuery<?> cq, CriteriaBuilder cd) {
                ArrayList<Predicate> lists = new ArrayList<>();
                if (isNotBlank(blogBean.getTitle())) {
                    //title 模糊查询
                    lists.add(cd.like(root.<String>get("title"), "%" + blogBean.getTitle() + "%"));
                }
                if (null != blogBean.getTypeBean() && null != blogBean.getTypeBean().getId()) {
                    //根据下拉框选择的id 进行精准查询
                    lists.add(cd.equal(root.<TypeBean>get("typeBean").get("id"), blogBean.getTypeBean().getId()));
                }
                if (blogBean.isRecommend()) {
                    lists.add(cd.equal(root.<Boolean>get("recommend"), blogBean.isRecommend()));
                }

                cq.where(lists.toArray(new Predicate[lists.size()]));

                return null;
            }
        }, pageable);
    }

    @Override
    @Transactional
    public BlogBean saveBlogBean(BlogBean blogBean) {
        if (null == blogBean.getId()){
            //设置创建时间
            blogBean.setCreateTime(System.currentTimeMillis());
            //设置更新时间
            blogBean.setUpdateTime(System.currentTimeMillis());
        }else {
            //设置更新时间
            blogBean.setUpdateTime(System.currentTimeMillis());
        }
        return blogBeanRepository.save(blogBean);
    }

    @Override
    @Transactional
    public void removeBlogBean(Long id) {
        blogBeanRepository.deleteById(id);
    }


    @Override
    public List<BlogBean> listBlogBean() {
        return blogBeanRepository.findAll();
    }
}
