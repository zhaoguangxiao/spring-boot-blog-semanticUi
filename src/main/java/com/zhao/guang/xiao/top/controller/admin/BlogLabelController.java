package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.TagBean;
import com.zhao.guang.xiao.top.service.BlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 16:36
 */
@Controller
@RequestMapping("admin")
public class BlogLabelController {


    @Autowired
    private BlogLabelService blogLabelService;

    /**
     * 跳转标签列表页面
     *
     * @return
     */
    @GetMapping("labels")
    public String list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<TagBean> tagBeans = blogLabelService.listTagBean(pageable);
        model.addAttribute("tagBeans", tagBeans);
        return "admin/label/list";
    }

    /**
     * 跳转form表单页面
     *
     * @return
     */
    @GetMapping("label")
    public String form(Model model) {
        model.addAttribute("tagBean", new TagBean());
        return "admin/label/form";
    }


    @GetMapping("label/{id}")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        TagBean tagBean = blogLabelService.getTagBeanById(id);
        if (null == tagBean) {
            throw new NotFountException("文章标签不存在,请与管理员联系!");
        }
        model.addAttribute("tagBean", tagBean);
        return "admin/label/form";
    }


    @PutMapping("label")
    public String saveTagBean(@Valid TagBean tagBean,
                              BindingResult bindingResult,
                              Model model) {
        if (hasTagBeanNameExist(tagBean.getName())) {
            bindingResult.rejectValue("name", "nameError", "标签名称不能为空,请输入标签");
        }
        if (bindingResult.hasErrors()) {
            return "admin/label/form";
        }
        blogLabelService.updateTagBean(tagBean);
        //跳转标签列表
        return "redirect:/admin/labels";
    }


    @PostMapping("label")
    public String updateTagBean(@Valid TagBean tagBean,
                                BindingResult bindingResult,
                                Model model) {
        if (hasTagBeanNameExist(tagBean.getName())) {
            bindingResult.rejectValue("name", "nameError", "标签名称不能为空,请输入标签");
        }
        if (bindingResult.hasErrors()) {
            return "admin/label/form";
        }
        blogLabelService.saveTagBean(tagBean);
        //跳转标签列表
        return "redirect:/admin/labels";
    }


    @DeleteMapping("label/{id}")
    public String deleteTagBean(@PathVariable("id") Long id) {
        blogLabelService.removeTagBeanById(id);
        return "redirect:/admin/labels";
    }


    private boolean hasTagBeanNameExist(String tagName) {
        return null != blogLabelService.getTagBeanByName(tagName);
    }


}
