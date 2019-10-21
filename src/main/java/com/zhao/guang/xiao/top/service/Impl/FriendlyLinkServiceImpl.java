package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.dao.FriendlyLinkRepository;
import com.zhao.guang.xiao.top.service.FriendlyLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
