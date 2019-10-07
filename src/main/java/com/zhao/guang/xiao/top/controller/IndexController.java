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


    @GetMapping("categary")
    public String toCategary() {
        return "category";
    }


    @GetMapping("tag")
    public String toTags() {
        return "tags";
    }


    @GetMapping("archives")
    public String toArchives() {
        return "archives";
    }


    @GetMapping("about")
    public String toAbout() {
        return "about";
    }


    @GetMapping("details")
    public String details() {
        return "details";
    }






//    后台
    @GetMapping("manager")
    public String indexManager(){
        return "admin/index";
    }

    @GetMapping("manager-category")
    public String managerCategory(){
        return "admin/publish";
    }




    @GetMapping("{id}/{name}")
    public String aop(@PathVariable Integer id,
                      @PathVariable String name) {
        log.info("-------aop -------");
        return "index";
    }

}
