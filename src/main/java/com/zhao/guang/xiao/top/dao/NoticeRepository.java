package com.zhao.guang.xiao.top.dao;

import com.zhao.guang.xiao.top.po.NoticeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 10:49
 */
public interface NoticeRepository extends JpaRepository<NoticeBean, Long> {


    @Query("select noticeBean  from NoticeBean noticeBean where noticeBean.status = ?1 ")
    Page<NoticeBean> findNoticeBeanByStatus(int unreadQuestion, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update NoticeBean bean set bean.status = :#{#status} where bean.id =:#{#id}")
    void updateStatusById(@Param("id") Long id, @Param("status") int status);


    @Query("select bean from  NoticeBean bean where bean.status=?1")
    List<NoticeBean> findNoticeBeanByUnread(int unreadQuestion);
}
