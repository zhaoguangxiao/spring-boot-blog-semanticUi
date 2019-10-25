package com.zhao.guang.xiao.top.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * 文章上传至阿里云oss 服务器
 */
@Slf4j
@Component
@PropertySource("classpath:aliyunoss.properties")
public class OSSUtil {


    @Autowired
    private Environment env;


    private static String endpoint;

    /**
     * ram子账户AccessKeyID
     */
    private static String accessKeyId;
    /**
     * ram子账户AccessKeySecret
     */
    private static String accessKeySecret;
    /**
     * 文件存储名称
     */
    private static String bucketName;
    /**
     * 存放文件的路径
     */
    private static String savePath;

    @PostConstruct
    public void init() {
        endpoint = env.getProperty("aliyun.oss.endpoint");
        accessKeyId = env.getProperty("aliyun.oss.accessKeyId");
        accessKeySecret = env.getProperty("aliyun.oss.accessKeySecret");
        bucketName = env.getProperty("aliyun.oss.bucketName");
        savePath = env.getProperty("aliyun.oss.image");
    }

    /**
     * 设置URL过期时间为 1000*24*365*10
     */
    private static final int VISIT_URL_EXPIRATION = 1000 * 24 * 365 * 10;

    /**
     * uploadFile:上传文件到Oss
     *
     * @param imageName       图片名字
     * @param fileInputStream 图片流
     * @param fileSize        fileSize 图片大小
     * @throws Exception
     */
    public static void uploadFile(String imageName, InputStream fileInputStream, Long fileSize) throws Exception {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(fileSize);
            if (!savePath.endsWith("/")) {
                savePath = savePath + "/";
            }
            client.putObject(bucketName, savePath + imageName, fileInputStream, objectMeta);
        } catch (Exception e) {
            log.error("删除oss文件出错", e);
            throw new Exception("上传文件到oss出错");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                    client.shutdown();
                } catch (IOException e) {
                    log.error("删除oss文件出错", e);
                }
            }
        }
    }

    /**
     * 生成原图访问地址
     *
     * @param imageName
     * @return
     * @throws Exception
     */
    public static String getVisitUrl(String imageName) throws Exception {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, VISIT_URL_EXPIRATION);
            return client.generatePresignedUrl(bucketName, savePath + imageName, calendar.getTime()).toString();
        } catch (Exception e) {
            throw new Exception("生成访问地址出错");
        } finally {
            client.shutdown();
        }
    }

    /**
     * 删除Oss源文件
     *
     * @param imageName
     */
    public static void delete(String imageName) throws Exception {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            if (!savePath.endsWith("/")) {
                savePath = savePath + "/";
            }
            client.deleteObject(bucketName, savePath + imageName);
        } catch (Exception e) {
            log.error("删除oss文件出错", e);
            throw new Exception("删除oss文件出错");
        } finally {
            client.shutdown();
        }
    }

}
