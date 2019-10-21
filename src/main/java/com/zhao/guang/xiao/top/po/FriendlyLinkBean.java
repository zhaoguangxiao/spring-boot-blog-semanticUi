package com.zhao.guang.xiao.top.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 16:17
 */
@Data
@Entity
@Table(name = "t_friend_link")
public class FriendlyLinkBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String friendName;
    private String url;

}
