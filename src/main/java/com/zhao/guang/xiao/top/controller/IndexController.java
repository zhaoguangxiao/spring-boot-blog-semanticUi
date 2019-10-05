package com.zhao.guang.xiao.top.controller;

import com.zhao.guang.xiao.top.exception.NotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/3 10:01
 */
@Slf4j
@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

}
