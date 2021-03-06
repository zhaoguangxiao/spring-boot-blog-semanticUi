package com.zhao.guang.xiao.top.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/6 11:00
 */

@Data
@Entity
@Table(name = "t_tag")
public class TagBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "标签名称不能为空")
    private String name;


    @ToString.Exclude
    @ManyToMany(mappedBy = "tagBeans")
    private List<BlogBean> blogBeans = new ArrayList<>();


    public TagBean() {
    }
}
