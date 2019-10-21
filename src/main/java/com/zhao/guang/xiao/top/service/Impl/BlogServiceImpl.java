package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogBeanRepository;
import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.util.MarkdownUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<BlogBean> blogBean = blogBeanRepository.findById(id);
        if (!blogBean.isPresent()) {
            throw new NotFountException("文章没有被找到,请与管理员联系");
        }
        return blogBean.get();
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
        if (null == blogBean.getId()) {
            //设置创建时间
            blogBean.setCreateTime(System.currentTimeMillis());
            //设置更新时间
            blogBean.setUpdateTime(System.currentTimeMillis());
        } else {
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


    @Override
    public Page<BlogBean> findPage(Pageable pageable) {
        return blogBeanRepository.findAll(pageable);
    }

    @Override
    public List<BlogBean> recommendBlogs(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "views");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogBeanRepository.recommendBlogs(pageable);
    }

    @Override
    public Page<BlogBean> listBlogBeanBySearch(String search, Pageable pageable) {
        return blogBeanRepository.findBlogBeanBySearch("%" + search + "%", pageable);
    }

    @Override
    public BlogBean getFrontEndBlogDetail(Long id) {
        Optional<BlogBean> blogBean = blogBeanRepository.findById(id);
        if (!blogBean.isPresent()) {
            throw new NotFountException("文章没有被找到,请与管理员联系");
        }
        BlogBean blogBean1 = blogBean.get();
        BlogBean bean = new BlogBean();
        BeanUtils.copyProperties(blogBean1, bean);
        bean.setContent(MarkdownUtil.markdownToHtmlExtensions(blogBean1.getContent()));
        return bean;
    }

    @Override
    public void updateByViewCount(Long id) {
        blogBeanRepository.updateByViewCount(id);
    }


    @Override
    public Page<BlogBean> listTagBean(Pageable pageable, Long labelId) {
        return blogBeanRepository.findAll(new Specification<BlogBean>() {
            @Override
            public Predicate toPredicate(Root<BlogBean> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tagBeans");
                return cb.equal(join.get("id"),labelId);
            }
        }, pageable);
    }
}

