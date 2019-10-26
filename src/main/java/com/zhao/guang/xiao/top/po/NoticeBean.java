package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

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
public class NoticeBean implements Serializable {

    //已读
    public final static int READ_QUESTION = 1;
    //未读
    public final static int UNREAD_QUESTION = 0;

    //评论
    public final static int BLOG_TYPE_COMMENT = 1;
    //点赞
    public final static int BLOG_TYPE_LIKE = 2;
    //反对
    public final static int BLOG_TYPE_FANDUI = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 通知人
     */
    @ManyToOne
    @ToString.Exclude
    private UserBean notifier;

    /**
     * 消息接收者
     */
    @ManyToOne
    @ToString.Exclude
    private UserBean receiver;

    /**
     * 消息类型如问题回复类型/文章留言
     */
    private Integer messageType;

    /**
     * 文章id
     */
    @ManyToOne
    @ToString.Exclude
    private BlogBean blogBean;

    private Long createTime;

    private Integer status;

}
