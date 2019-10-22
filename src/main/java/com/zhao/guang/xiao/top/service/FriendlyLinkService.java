package com.zhao.guang.xiao.top.service;

import com.zhao.guang.xiao.top.po.FriendlyLinkBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 16:25
 */
public interface FriendlyLinkService {


    FriendlyLinkBean getOne(Long id);


    Page<FriendlyLinkBean> listFriendlyLink(Pageable pageable);



    List<FriendlyLinkBean> listFriendlyLink();


    FriendlyLinkBean saveFriendlyLinkBean(FriendlyLinkBean friendlyLinkBean);


    void deleteById(Long id);
}
