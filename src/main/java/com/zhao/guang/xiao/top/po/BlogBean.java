package com.zhao.guang.xiao.top.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.Proxy;

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
    /**
     * 草稿
     */
    public static final String BLOG_ENABLE = "1";

    /**
     * 发布
     */
    public static final String BLOG_DISABLE = "0";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    //首图
    private String firstPicture;


    private int views;
    //赞赏开启
    private boolean appreciation;
    //版权是否开启
    private boolean shareStatement;
    //是否开启评论
    private boolean commentOpen;
    //发布 草稿
    private int published;
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

    public BlogBean() {
    }


    public String formatTags() {
        if (CollectionUtils.isNotEmpty(tagBeans)) {
            StringBuilder sb = new StringBuilder(tagBeans.size());
            tagBeans.forEach(each -> {
                sb.append(each.getId());
                sb.append(",");
            });
            //截取最后一个逗号
            return sb.toString().substring(0, sb.length() - 1);
        }
        return null;
    }

}
