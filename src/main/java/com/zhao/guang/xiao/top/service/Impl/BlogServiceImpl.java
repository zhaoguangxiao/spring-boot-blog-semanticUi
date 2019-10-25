package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.BlogBeanRepository;
import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.BlogBean;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.util.MarkdownUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/9 9:42
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private EntityManager em;


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
                if (0 != blogBean.getTop()) {
                    lists.add(cd.equal(root.<Boolean>get("top"), blogBean.getTop()));
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
    public Long countBlogBean() {
        return blogBeanRepository.count();
    }

    @Override
    public Page<BlogBean> listTagBean(Pageable pageable, Long labelId) {
        return blogBeanRepository.findAll(new Specification<BlogBean>() {
            @Override
            public Predicate toPredicate(Root<BlogBean> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tagBeans");
                return cb.equal(join.get("id"), labelId);
            }
        }, pageable);
    }


    @Override
    public Map<String, List<BlogBean>> archiverBlogBean() {
        Map<String, List<BlogBean>> maps = new HashMap<>();
        List<String> yearAndMonths = getGroupByYearAndMonths();
        yearAndMonths.forEach(each -> {
            maps.put(each, getBlogBean(each));
        });
        return maps;
    }

    /**
     * 获取文章根据年月分组
     *
     * @return
     */
    private List<String> getGroupByYearAndMonths() {
        String sql = "select FROM_UNIXTIME(blogbean.create_time/1000,'%Y-%m') AS months from t_blog blogbean group by FROM_UNIXTIME(blogbean.create_time/1000,'%Y-%m')  ORDER BY months DESC";
        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }


    /**
     * 根据年份查出当前所有文章
     *
     * @param years
     * @return
     */
    private List<BlogBean> getBlogBean(String years) {
        String sql = "select * from  t_blog  blogBean where FROM_UNIXTIME(blogbean.create_time/1000,'%Y-%m') = ?";
        Query query = em.createNativeQuery(sql, BlogBean.class);
        query.setParameter(1, years);
        return query.getResultList();
    }


}

