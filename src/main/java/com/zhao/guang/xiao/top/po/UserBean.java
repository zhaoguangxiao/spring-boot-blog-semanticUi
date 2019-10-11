package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 11:04
 */
@Setter
@Getter
@Entity
@ToString
@Table(name = "t_user")
public class UserBean {

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

    @OneToMany(mappedBy = "userBean")
    private List<BlogBean> blogBeanList = new ArrayList<>();

    public UserBean() {
    }


}
