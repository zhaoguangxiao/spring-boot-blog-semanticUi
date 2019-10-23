package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.NoticeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 10:48
 */
public interface NoticeService {

    NoticeBean saveNoticeBean(NoticeBean noticeBean);

    Page<NoticeBean> findPage(Pageable pageable);

    void updateNoticeBeanById(Long id,int status);

    void deleteNoticeBean(Long id);

    int findNoticeBeanByUnread();

}
