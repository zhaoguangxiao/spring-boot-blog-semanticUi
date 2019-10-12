package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 11:01
 */
@Data
@Entity
@Table(name = "t_comment")
public class CommentBean implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private Long createTime;

    @ManyToOne
    private BlogBean blogBean;

    @OneToMany(mappedBy = "parentComment")
    private List<CommentBean> replyComments = new ArrayList<>();

    @ManyToOne
    private CommentBean parentComment;

    public CommentBean() {
    }

}
