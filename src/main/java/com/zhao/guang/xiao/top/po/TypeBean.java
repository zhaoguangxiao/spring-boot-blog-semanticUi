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
 * @date 2019/10/6 10:58
 */
@Setter
@Getter
@Entity
@ToString
@Table(name = "t_type")
public class TypeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "typeBean")
    private List<BlogBean> blogBeanList = new ArrayList<>();


    public TypeBean() {
    }
}
