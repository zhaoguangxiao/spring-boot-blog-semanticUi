package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.FabulousRepository;
import com.zhao.guang.xiao.top.po.FabulousBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.service.FabulousService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 10:11
 */
@Service
public class FabulousServiceImpl implements FabulousService {

    @Autowired
    private FabulousRepository fabulousRepository;


    @Autowired
    private BlogService blogService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveFabulousBean(FabulousBean fabulousBean) {
        FabulousBean bean = findOneByUserIdAndBlogId(fabulousBean.getUserBean().getId(), fabulousBean.getBlogBean().getId());
        if (null == bean) {
            fabulousRepository.save(fabulousBean);
            if (ObjectUtils.equals(FabulousBean.GIVE_LIKE, fabulousBean.getType())) {
                blogService.updateByLikeCount(fabulousBean.getBlogBean().getId());
            } else {
                blogService.updateByoppositionCount(fabulousBean.getBlogBean().getId());
            }
            return true;
        }
        return false;
    }


    @Override
    public FabulousBean findOneByUserIdAndBlogId(Long userId, Long blogId) {
        return fabulousRepository.findByUserIdAndBlogId(userId, blogId);
    }
}
