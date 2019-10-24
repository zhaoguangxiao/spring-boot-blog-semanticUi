package com.zhao.guang.xiao.top.service;

import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/24 16:01
 */
public interface SelectedArticlesService {

    Map<String, String> getSelectedArticles();

    void clearMaps();
}
