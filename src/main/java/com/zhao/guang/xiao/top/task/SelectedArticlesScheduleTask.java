package com.zhao.guang.xiao.top.task;

import com.zhao.guang.xiao.top.service.SelectedArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/24 16:00
 */
@Component
public class SelectedArticlesScheduleTask {


    @Autowired
    private SelectedArticlesService selectedArticlesService;


    @Scheduled(cron = "${job.schedule}")
    private void configureTasks() {
        //清空maps集合
        selectedArticlesService.clearMaps();
    }


}
