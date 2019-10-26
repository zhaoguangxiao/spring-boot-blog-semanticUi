package com.zhao.guang.xiao.top.po;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 16:17
 */
@Data
@Entity
@Table(name = "t_friend_link")
public class FriendlyLinkBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "友情链接名称不能为空")
    private String friendName;


    @NotBlank(message = "友情链接url不能为空")
    private String url;

}
