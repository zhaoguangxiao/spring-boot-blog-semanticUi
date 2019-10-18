package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.service.GithubService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/18 9:39
 */
@Service
public class GithubServiceImpl implements GithubService {


    private static String stats = null;


    @Override
    public String genState() {
        return stats = UUID.randomUUID().toString();
    }


    @Override
    public boolean checkState(String status) {
        return stats.equals(status);
    }

}
