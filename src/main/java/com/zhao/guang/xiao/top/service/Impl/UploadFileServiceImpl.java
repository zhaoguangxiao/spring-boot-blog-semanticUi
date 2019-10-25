package com.zhao.guang.xiao.top.service.Impl;

import com.zhao.guang.xiao.top.service.UploadFileService;
import com.zhao.guang.xiao.top.util.OSSUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/25 15:35
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {


    @Override
    public String uploadImage(String originalFilename, InputStream inputStream, long size) throws Exception {
        //把图片上传至阿里oss
        OSSUtil.uploadFile(originalFilename, inputStream, size);
        //返回url
        return OSSUtil.getVisitUrl(originalFilename);
    }

}
