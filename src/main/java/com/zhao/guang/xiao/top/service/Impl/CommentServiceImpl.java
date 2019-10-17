package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.CommentRepository;
import com.zhao.guang.xiao.top.po.CommentBean;
import com.zhao.guang.xiao.top.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/16 15:58
 */
@Service
public class CommentServiceImpl implements CommentService {


    private List<CommentBean> templateReplys = new ArrayList<>();


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


    @Override
    public List<CommentBean> ListCommentBeanByBlogIdAndParentisNull(Long blogId) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        List<CommentBean> commentBean = commentRepository.findByBlogBeanIdAndParentCommentNull(blogId, sort);
        return eachCommentBean(commentBean);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param commentBeans
     * @return
     */
    private List<CommentBean> eachCommentBean(List<CommentBean> commentBeans) {
        List<CommentBean> commentBeanList = new ArrayList<>();
        commentBeans.forEach(commentBean -> {
            CommentBean bean = new CommentBean();
            BeanUtils.copyProperties(commentBean, bean);
            commentBeanList.add(bean);
        });
        //合并评论的各层子代到第一级子代集合中
        commentBeanChildren(commentBeanList);
        return commentBeanList;
    }

    /**
     * @param commentBeanList root根节点 blog不为空的对象集合
     */
    private void commentBeanChildren(List<CommentBean> commentBeanList) {
        commentBeanList.forEach(commentBean -> {
            List<CommentBean> replyComments = commentBean.getReplyComments();
            replyComments.forEach(each -> {
                //循环迭代 找出子代 存放在templateReplys集合中
                recursively(each);
            });
            //修改顶级节点的reply集合为迭代处理后的集合
            commentBean.setReplyComments(templateReplys);
            //清除临时存放区
            templateReplys = new ArrayList<>();
        });
    }

    /**
     * 递归迭代,剥洋葱
     *
     * @param commentBean
     */
    private void recursively(CommentBean commentBean) {
        //顶节点添加到历史存放的集合
        templateReplys.add(commentBean);
        if (commentBean.getReplyComments().size() > 0) {
            List<CommentBean> replyComments = commentBean.getReplyComments();
            replyComments.forEach(reply -> {
                templateReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            });
        }
    }


}
