package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 11:04
 */
@Data
@Entity
@Table(name = "t_user")
public class UserBean implements Serializable {


    public static final int USER_GITHUB = 1;
    public static final int USER_QQ = 2;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Long createTime;
    private Long updateTime;
    private String descript;


    @ToString.Exclude
    @OneToMany(mappedBy = "userBean")
    private List<BlogBean> blogBeanList = new ArrayList<>();

    public UserBean() {
    }


}
