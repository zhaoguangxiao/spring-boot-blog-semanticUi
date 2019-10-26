package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.FriendlyLinkRepository;
import com.zhao.guang.xiao.top.po.FriendlyLinkBean;
import com.zhao.guang.xiao.top.service.FriendlyLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 16:25
 */
@Slf4j
@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {


    @Autowired
    private FriendlyLinkRepository friendlyLinkRepository;


    @Override
    public Page<FriendlyLinkBean> listFriendlyLink(Pageable pageable) {
        return friendlyLinkRepository.findAll(pageable);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public FriendlyLinkBean saveFriendlyLinkBean(FriendlyLinkBean friendlyLinkBean) {
        return friendlyLinkRepository.save(friendlyLinkBean);
    }


    @Override
    public FriendlyLinkBean getOne(Long id) {
        return friendlyLinkRepository.getOne(id);
    }


    @Override
    public List<FriendlyLinkBean> listFriendlyLink() {
        return friendlyLinkRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteById(Long id) {
         friendlyLinkRepository.deleteById(id);
    }
}
