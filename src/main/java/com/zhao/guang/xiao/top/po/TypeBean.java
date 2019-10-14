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
 * @date 2019/10/6 10:58
 */
@Data
@Entity
@Table(name = "t_type")
public class TypeBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;


    @ToString.Exclude
    @OneToMany(mappedBy = "typeBean")
    private List<BlogBean> blogBeanList = new ArrayList<>();



}
