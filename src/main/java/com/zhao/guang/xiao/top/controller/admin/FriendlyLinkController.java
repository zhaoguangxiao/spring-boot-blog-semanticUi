package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.FriendlyLinkBean;
import com.zhao.guang.xiao.top.service.FriendlyLinkService;
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
 * @date 2019/10/18 16:25
 */
@Controller
@RequestMapping("admin")
public class FriendlyLinkController {


    @Autowired
    private FriendlyLinkService friendlyLinkService;


    @GetMapping("links")
    public String toPage(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        Page<FriendlyLinkBean> linkBeans = friendlyLinkService.listFriendlyLink(pageable);
        model.addAttribute("linkBeans", linkBeans);
        return "admin/link/list";
    }


    @GetMapping("link")
    public String form(Model model) {
        model.addAttribute("friendlyLinkBean", new FriendlyLinkBean());
        return "admin/link/form";
    }


    @PostMapping("link")
    public String saveFriendlyLink(@Valid FriendlyLinkBean friendlyLinkBean,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/link/form";
        }
        friendlyLinkService.saveFriendlyLinkBean(friendlyLinkBean);
        return "redirect:/admin/links";
    }


    @GetMapping("link/{id}")
    public String toEditPage(@PathVariable Long id,
                             Model model) {
        FriendlyLinkBean friendlyLinkBean = friendlyLinkService.getOne(id);
        if (null == friendlyLinkBean) {
            throw new NotFountException("友情链接不存在");
        }
        model.addAttribute("friendlyLinkBean", friendlyLinkBean);
        return "admin/link/form";
    }


    @DeleteMapping("link/{id}")
    public String deleteById(@PathVariable Long id) {
        friendlyLinkService.deleteById(id);
        return "redirect:/admin/links";
    }

}
