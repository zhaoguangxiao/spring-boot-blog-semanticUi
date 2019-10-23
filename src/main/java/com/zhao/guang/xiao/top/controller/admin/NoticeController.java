package com.zhao.guang.xiao.top.controller.admin;

import com.zhao.guang.xiao.top.po.NoticeBean;
import com.zhao.guang.xiao.top.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/23 10:09
 */
@Controller
@RequestMapping("admin")
public class NoticeController {


    @Autowired
    private NoticeService noticeService;


    @GetMapping("notice")
    public String noticePage(@PageableDefault(size = 5, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             Model model) {
        Page<NoticeBean> noticeBeans = noticeService.findPage(pageable);
        model.addAttribute("noticeBeans", noticeBeans);
        return "admin/notice/list";
    }

    @GetMapping("notice/{id}")
    public String updateNotice(@PathVariable Long id) {
        //已读
        noticeService.updateNoticeBeanById(id, NoticeBean.READ_QUESTION);
        return "redirect:/admin/notice";
    }


    @DeleteMapping("notice/{id}")
    public String deleteById(@PathVariable Long id) {
        noticeService.deleteNoticeBean(id);
        return "redirect:/admin/notice";
    }


}
