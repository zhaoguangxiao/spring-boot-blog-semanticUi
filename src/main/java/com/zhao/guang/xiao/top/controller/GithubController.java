package com.zhao.guang.xiao.top.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.GithubService;
import com.zhao.guang.xiao.top.service.UserService;
import com.zhao.guang.xiao.top.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/17 19:23
 */
@Slf4j
@Controller
@PropertySource(value = "classpath:github.properties")
public class GithubController {


    @Autowired
    private GithubService githubService;


    @Autowired
    private UserService userService;


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.directurl}")
    private String redirectUrl;


    @Value("${github.request.url}")
    private String request_https;

    @Value("${github.user.url}")
    private String request_user_http;


    @ResponseBody
    @GetMapping("github/oauth")
    public Map<String, String> github() {
        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.put("clientId", clientId);
        hashMap.put("redirectUrl", redirectUrl);
        hashMap.put("status", githubService.genState());
        return hashMap;
    }


    @GetMapping("github/login")
    public String githubLogin() {
        String url = "https://github.com/login/oauth/authorize?client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=user&state=STATE";
        String replace = url.replace("CLIENT_ID", clientId).replace("REDIRECT_URI", redirectUrl).replace("STATE",githubService.genState());
        return "redirect:" + replace;
    }


    @GetMapping("callback")
    public void githubCallBack(@RequestParam("code") String code,
                               @RequestParam("state") String state,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        // 验证state，如果不一致，可能被CSRF攻击
        if (!githubService.checkState(state)) {
            throw new NotFountException("State验证失败");
        }
        //验证成功
        // 2、向GitHub认证服务器申请令牌
        // 传递参数grant_type、code、redirect_uri、client_id
        String param = "grant_type=authorization_code&code=" + code + "&redirect_uri=" +
                redirectUrl + "&client_id=" + clientId + "&client_secret=" + clientSecret;

        // 申请令牌，注意此处为post请求
        // QQ获取到的access token具有3个月有效期，用户再次登录时自动刷新。
        String result = HttpClientUtils.sendPostRequest(request_https, param);

        /*
         * result示例：
         * 成功：access_token=A24B37194E89A0DDF8DDFA7EF8D3E4F8&expires_in=7776000&refresh_token=BD36DADB0FE7B910B4C8BBE1A41F6783
         */
        Map<String, String> resultMap = HttpClientUtils.params2Map(result);
        // 如果返回结果中包含access_token，表示成功
        if (!resultMap.containsKey("access_token")) {
            throw new NotFountException("获取token失败");
        }
        // 得到token
        String accessToken = resultMap.get("access_token");

        // 3、使用Access Token来获取用户的OpenID
        String meParams = "access_token=" + accessToken;
        String meResult = HttpClientUtils.sendGetRequest(request_user_http, meParams);
        // 成功返回如下：callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        log.info(meResult);
        JSONObject fromObject = JSON.parseObject(meResult);

        //唯一标识id
        String userid = fromObject.getString("id");
        String nickName = fromObject.getString("name");
        String avatar_url = fromObject.getString("avatar_url");

        UserBean userBean = new UserBean();
        userBean.setNickName(nickName);
        userBean.setAvatar(avatar_url);
        userBean.setType(UserBean.USER_GITHUB);
        userBean.setPassword(userid);

        userService.saveGithub(userBean);
        request.getSession().setAttribute("userEntity", userBean);


        // QQ登录有点特殊，参数放在#后面，后台无法获取#后面的参数，只能用JS做中间转换
        String html = "<!DOCTYPE html>" +
                "<html lang=\"zh-cn\">" +
                "<head>" +
                "   <title>GitHub重定向页面</title>" +
                "   <meta charset=\"utf-8\"/>" +
                "</head>" +
                "<body>" +
                "<script type=\"text/javascript\">" +
                "window.opener.location.reload(); " +
                "    this.window.opener = null;" +
                "    window.close();" +
                "   </script>" +
                "</body>" +
                "</html>";


        response.getWriter().print(html);
    }

}
