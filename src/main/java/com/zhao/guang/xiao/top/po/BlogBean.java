package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 10:45
 */
@Data
@Entity
@Table(name = "t_blog")
public class BlogBean implements Serializable {
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

    @NotBlank(message = "文章标题不能为空")
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 首图
     */
    @NotBlank(message = "文章首图不能为空")
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
    @ToString.Exclude
    private TypeBean typeBean;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<TagBean> tagBeans = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private UserBean userBean;

    @ToString.Exclude
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
