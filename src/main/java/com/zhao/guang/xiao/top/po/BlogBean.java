package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 10:45
 */
@Setter
@Getter
@Entity
@ToString
@Table(name = "t_blog")
public class BlogBean {

    public BlogBean() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    //首图
    private String firstPicture;
    private String flag;
    private Integer views;
    //赞赏开启
    private boolean appreciation;
    //版权是否开启
    private boolean shareStatement;
    //是否开启评论
    private boolean commentOpen;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    private Long createTime;
    private Long updateTime;


    @ManyToOne
    private TypeBean typeBean;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<TagBean> tagBeans = new ArrayList<>();

    @ManyToOne
    private UserBean userBean;

    @OneToMany(mappedBy = "blogBean")
    private List<CommentBean> commentBeans = new ArrayList<>();



}
