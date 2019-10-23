package com.zhao.guang.xiao.top.service.Impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.zhao.guang.xiao.top.dao.NoticeRepository;
import com.zhao.guang.xiao.top.po.NoticeBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 10:48
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;


    @Override
    @Transactional
    public NoticeBean saveNoticeBean(NoticeBean noticeBean) {
        //设置创建时间
        noticeBean.setCreateTime(System.currentTimeMillis());
        //设置状态为未读
        noticeBean.setStatus(NoticeBean.UNREAD_QUESTION);
        //设置消息接受者
        UserBean bean = new UserBean();
        bean.setId(2L);
        noticeBean.setReceiver(bean);
        return noticeRepository.save(noticeBean);
    }


    @Override
    public Page<NoticeBean> findPage(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public void updateNoticeBeanById(Long id, int status) {
        noticeRepository.updateStatusById(id, status);
    }

    @Override
    @Transactional
    public void deleteNoticeBean(Long id) {
        noticeRepository.deleteById(id);
    }


    @Override
    public int findNoticeBeanByUnread() {
        //查出全部未读的通知
        List<NoticeBean> noticeBeanByUnread = noticeRepository.findNoticeBeanByUnread(NoticeBean.UNREAD_QUESTION);
        return noticeBeanByUnread.size();
    }
}
