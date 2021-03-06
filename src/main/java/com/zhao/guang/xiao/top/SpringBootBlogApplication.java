package com.zhao.guang.xiao.top;

import com.zhao.guang.xiao.top.config.MyLocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;

/**
 * EnableScheduling 开启定时任务
 *
 * @author Administrator
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogApplication.class, args);
    }


    /**
     * 引入国际化配置
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
