package com.zhao.guang.xiao.top.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 通知实体类
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 10:38
 */
@Data
@Entity
@Table(name = "t_notice")
public class NoticeBean {

    //已读
    public final static String READ_QUESTION = "1";
    //未读
    public final static String UNREAD_QUESTION = "0";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 通知人
     */
    private UserBean notifier;

    /**
     * 消息接收者
     */
    private UserBean receiver;

    /**
     * 消息类型如问题回复类型/文章留言
     */
    private Integer messageType;

    /**
     * 文章id
     */
    private BlogBean blogBean;

    private Long createTime;

    private Integer status;

}
