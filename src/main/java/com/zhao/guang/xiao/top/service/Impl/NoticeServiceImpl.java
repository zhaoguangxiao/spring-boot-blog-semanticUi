package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.NoticeRepository;
import com.zhao.guang.xiao.top.po.NoticeBean;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.NoticeService;
import com.zhao.guang.xiao.top.service.UserService;
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

    @Autowired
    private UserService userService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeBean saveNoticeBean(NoticeBean noticeBean) {
        //设置创建时间
        noticeBean.setCreateTime(System.currentTimeMillis());
        //设置状态为未读
        noticeBean.setStatus(NoticeBean.UNREAD_QUESTION);
        //设置消息接受者
        noticeBean.setReceiver(getReceiver());
        return noticeRepository.save(noticeBean);
    }


    private UserBean getReceiver() {
        return userService.userManger();
    }


    @Override
    public Page<NoticeBean> findPage(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNoticeBeanById(Long id, int status) {
        noticeRepository.updateStatusById(id, status);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
