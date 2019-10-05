package com.zhao.guang.xiao.top.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("{id}/{name}")
    public String aop(@PathVariable Integer id,
                      @PathVariable String name) {
        log.info("-------aop -------");
        return "index";
    }

}
