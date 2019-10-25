package com.zhao.guang.xiao.top.service;

import java.io.InputStream;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/25 15:35
 */
public interface UploadFileService {

    /**
     * 上传文件到阿里oss
     *
     * @param originalFilename
     * @param inputStream
     * @param size
     * @return
     */
    public String uploadImage(String originalFilename, InputStream inputStream, long size) throws Exception;

}
