package com.zhao.guang.xiao.top.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/25 16:34
 */
@Data
public class FileResultBean implements Serializable {

    /**
     * 0 表示上传失败，1 表示上传成功
     */
    private int success;
    /**
     * 提示的信息，上传成功或上传失败及错误信息等。
     */
    private String message;
    /**
     * 上传成功时才返回
     */
    private String url;


    public FileResultBean(int success, String url) {
        this.success = success;
        this.url = url;
    }


    public FileResultBean(int success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public FileResultBean() {}
}