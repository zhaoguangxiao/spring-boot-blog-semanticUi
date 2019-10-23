package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.NoticeRepository;
import com.zhao.guang.xiao.top.po.NoticeBean;
import com.zhao.guang.xiao.top.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return noticeRepository.save(noticeBean);
    }



}
