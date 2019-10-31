package com.zhao.guang.xiao.top.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.QQService;
import com.zhao.guang.xiao.top.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/31 10:57
 */
@Service
public class QQServiceImpl implements QQService {


    @Autowired
    private UserService userService;


    @Override
    public String getAccessToken(String parm) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String token = null;

        HttpGet httpGet = new HttpGet(parm);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            if (result.indexOf("access_token") >= 0) {
                String[] array = result.split("&");
                for (String str : array) {
                    if (str.indexOf("access_token") >= 0) {
                        token = str.substring(str.indexOf("=") + 1);
                        break;
                    }
                }
            }
        }

        httpGet.releaseConnection();
        return token;
    }

    @Override
    public String getOpenID(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = parseJSONP(result);
        }

        httpGet.releaseConnection();

        if (jsonObject != null) {
            return jsonObject.getString("openid");
        } else {
            return null;
        }
    }


    @Override
    public JSONObject getUserInfo(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();


        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }

        httpGet.releaseConnection();

        return jsonObject;
    }


    private JSONObject parseJSONP(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");

        String json = jsonp.substring(startIndex + 1, endIndex);

        return JSONObject.parseObject(json);
    }


    @Override
    public UserBean saveQQUser(JSONObject jsonObject, String oppenId) {
        //openid,用来唯一标识qq用户
        //QQ名
        String nickname = (String) jsonObject.get("nickname");
        //大小为100*100像素的QQ头像URL
        String qqImage = (String) jsonObject.get("figureurl_qq_2");
        //写入数据库
        UserBean bean = new UserBean();
        bean.setAvatar(qqImage);
        bean.setNickName(nickname);
        bean.setPassword(oppenId);
        bean.setType(UserBean.USER_QQ);
        return userService.saveQQ(bean);
    }
}
