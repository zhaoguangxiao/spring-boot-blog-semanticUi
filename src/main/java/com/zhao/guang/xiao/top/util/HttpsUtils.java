package com.zhao.guang.xiao.top.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Administrator
 */
public class HttpsUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);


    public static final MediaType mediaType
            = MediaType.get("application/json; charset=utf-8");
    public final static OkHttpClient client = new OkHttpClient();

    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }


    public static String post(String url, String json) {
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }


}
