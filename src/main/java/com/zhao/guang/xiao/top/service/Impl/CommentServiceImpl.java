package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.CommentRepository;
import com.zhao.guang.xiao.top.po.CommentBean;
import com.zhao.guang.xiao.top.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/16 15:58
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<CommentBean> listCommentBeanByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return commentRepository.findByBlogBeanId(blogId, sort);
    }

    @Override
    @Transactional
    public CommentBean saveCommentBean(CommentBean commentBean) {
        Long commentParentId = commentBean.getParentComment().getId();
        if (commentParentId != -1) commentBean.setParentComment(commentRepository.getOne(commentParentId));
        else commentBean.setParentComment(null);
        commentBean.setCreateTime(System.currentTimeMillis());
        return commentRepository.save(commentBean);
    }
}
