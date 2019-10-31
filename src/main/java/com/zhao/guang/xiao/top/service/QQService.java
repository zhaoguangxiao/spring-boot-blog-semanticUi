package com.zhao.guang.xiao.top.service;

import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.po.UserBean;

import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/31 10:57
 */
public interface QQService {

    public String getAccessToken(String parm) throws IOException;

    public String getOpenID(String url) throws IOException;

    public JSONObject getUserInfo(String userUrl) throws IOException;

    public UserBean saveQQUser(JSONObject jsonObject,String oppenId);


}
