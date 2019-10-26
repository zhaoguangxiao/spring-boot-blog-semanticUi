package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.FabulousRepository;
import com.zhao.guang.xiao.top.po.FabulousBean;
import com.zhao.guang.xiao.top.po.NoticeBean;
import com.zhao.guang.xiao.top.service.BlogService;
import com.zhao.guang.xiao.top.service.FabulousService;
import com.zhao.guang.xiao.top.service.NoticeService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zhao.guang.xiao.top.po.NoticeBean.BLOG_TYPE_LIKE;

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


    @Autowired
    private NoticeService noticeService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveFabulousBean(FabulousBean fabulousBean) {
        FabulousBean bean = findOneByUserIdAndBlogId(fabulousBean.getUserBean().getId(), fabulousBean.getBlogBean().getId());
        //添加一个通知
        NoticeBean noticeBean = new NoticeBean();
        if (null == bean) {
            fabulousRepository.save(fabulousBean);
            if (ObjectUtils.equals(FabulousBean.GIVE_LIKE, fabulousBean.getType())) {
                blogService.updateByLikeCount(fabulousBean.getBlogBean().getId());
                //消息类型评论
                noticeBean.setMessageType(NoticeBean.BLOG_TYPE_LIKE);
            } else {
                blogService.updateByoppositionCount(fabulousBean.getBlogBean().getId());
                //消息类型评论
                noticeBean.setMessageType(NoticeBean.BLOG_TYPE_FANDUI);
            }
            //消息创建人
            noticeBean.setNotifier(fabulousBean.getUserBean());
            noticeBean.setBlogBean(fabulousBean.getBlogBean());
            noticeService.saveNoticeBean(noticeBean);
            return true;
        }
        return false;
    }


    @Override
    public FabulousBean findOneByUserIdAndBlogId(Long userId, Long blogId) {
        return fabulousRepository.findByUserIdAndBlogId(userId, blogId);
    }
}
