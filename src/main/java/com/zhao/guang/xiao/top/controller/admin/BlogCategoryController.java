package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/7 14:01
 */
@Slf4j
@Controller
@RequestMapping("admin")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * 分类新增
     *
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/categorys")
    public String list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<TypeBean> typeBeans = blogCategoryService.listTypeBean(pageable);
        model.addAttribute("typeBeans", typeBeans);
        return "admin/type/list";
    }

    /**
     * 跳转新增分类页面
     *
     * @return
     */
    @GetMapping("/category")
    public String form(Model model) {
        model.addAttribute("typeBean", new TypeBean());
        return "admin/type/form";
    }


    /**
     * 跳转分类编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/category/{id}")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        TypeBean typeBean = blogCategoryService.getTypeBean(id);
        if (null == typeBean) {
            throw new NotFountException("文章分类没有找到");
        }
        model.addAttribute("typeBean", typeBean);
        return "admin/type/form";
    }

    /**
     * 删除分类方法
     *
     * @param id
     * @return
     */
    @DeleteMapping("/category/{id}")
    public String deleteEmp(@PathVariable("id") Long id) {
        blogCategoryService.removeTypeBean(id);
        return "redirect:/admin/categorys";
    }


    /**
     * 使用put来接受修改分类页面
     *
     * @param typeBean
     * @return
     */
    @PutMapping("/category")
    public String updateEmp(@Valid TypeBean typeBean,
                            BindingResult bindingResult) {
        if (hasBlogCategoryNameExist(typeBean.getName())) {
            bindingResult.rejectValue("name", "nameError", "分类名已经存在,换个试一试!");
        }
        if (bindingResult.hasErrors()) {
            return "admin/type/form";
        }
        blogCategoryService.updateTypeBean(typeBean);
        return "redirect:/admin/categorys";
    }


    /**
     * post保存分类页面
     *
     * @param typeBean
     * @return
     */
    @PostMapping("/category")
    public String saveEmp(@Valid TypeBean typeBean,
                          BindingResult bindingResult) {
        //可以自己给指定属性添加错误消息返回到页面
        if (hasBlogCategoryNameExist(typeBean.getName())) {
            bindingResult.rejectValue("name", "nameError", "分类名已经存在,换个试一试!");
        }
        if (bindingResult.hasErrors()) {
            return "admin/type/form";
        }
        blogCategoryService.saveTypeBean(typeBean);
        return "redirect:/admin/categorys";
    }


    /**
     * 判断当前文章分类名称是否存在
     *
     * @param name
     * @return
     */
    private boolean hasBlogCategoryNameExist(String name) {
        return null != blogCategoryService.getTypeBeanByName(name);
    }

}
