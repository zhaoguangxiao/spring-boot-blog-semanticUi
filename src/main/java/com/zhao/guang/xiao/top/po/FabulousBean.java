package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户点赞/反对 实体类
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/26 10:03
 */
@Data
@Entity
@Table(name = "t_fabulous")
public class FabulousBean implements Serializable {

    /**
     * 点赞
     */
    public static final Integer GIVE_LIKE = 1;
    /**
     * 反对
     */
    public static final Integer OPPOSITION = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Id
     */
    @ManyToOne
    @ToString.Exclude
    private UserBean userBean;
    /**
     * 博客id
     */
    @ManyToOne
    @ToString.Exclude
    private BlogBean blogBean;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 操作类型
     */
    private Integer type;


    public FabulousBean() {
    }

    public FabulousBean(UserBean userBean, BlogBean blogBean, Integer type) {
        this.createTime = System.currentTimeMillis();
        this.userBean = userBean;
        this.blogBean = blogBean;
        this.type = type;
    }
}
