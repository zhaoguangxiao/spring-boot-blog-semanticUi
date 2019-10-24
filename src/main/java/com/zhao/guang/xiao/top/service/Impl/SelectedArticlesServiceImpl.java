package com.zhao.guang.xiao.top.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.service.SelectedArticlesService;
import com.zhao.guang.xiao.top.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 精选文章处理类
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/24 16:01
 */
@Service
@PropertySource(value = "classpath:tianxingData.properties")
public class SelectedArticlesServiceImpl implements SelectedArticlesService {


    @Value("${tian.xing.get.url}")
    private String tianxingUrl;

    @Value("${tian.xing.get.url.parm}")
    private String parm;


    private static Map<String, String> maps = new HashMap<>(3);


    @Override
    public Map<String, String> getSelectedArticles() {
        if (maps.isEmpty()) {
            return getArticle();
        } else {
            return maps;
        }
    }


    @Override
    public void clearMaps() {
        //清空精选文章集合
        maps.clear();
    }

    /**
     * 调用天行接口获取精选文章
     *
     * @return
     */
    private Map<String, String> getArticle() {
        String result = HttpClientUtils.sendGetRequest(tianxingUrl, parm);
        JSONObject jsonObject = JSON.parseObject(result);
        if ((int) jsonObject.get("code") == 200) {
            Object newslist = jsonObject.get("newslist");
            JSONArray jsonArray = JSONArray.parseArray(newslist.toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject each = jsonArray.getJSONObject(i);
                maps.put("content", each.getString("content"));
                maps.put("source", each.getString("source"));
                maps.put("author", each.getString("author"));
            }
        } else {
            maps.put("content", "忽如一夜春风来，千树万树梨花开。");
            maps.put("source", "白雪歌送武判官归京");
            maps.put("author", "岑参");
        }
        return maps;
    }


}
