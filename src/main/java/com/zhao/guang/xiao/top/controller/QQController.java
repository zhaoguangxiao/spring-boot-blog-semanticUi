package com.zhao.guang.xiao.top.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhao.guang.xiao.top.exception.NotFountException;
import com.zhao.guang.xiao.top.po.UserBean;
import com.zhao.guang.xiao.top.service.QQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/17 19:21
 */
@Slf4j
@Controller
@PropertySource(value = "classpath:qq.properties")
public class QQController {

    @Value("${login.qqAppId}")
    private String qqAppId;

    @Value("${login.qqAuthPath.backUrl}")
    private String backUrl;

    @Value("${login.qqQppleKey}")
    private String qqAppleKey;

    @Value("${login.qq.get.token}")
    private String getTokenUrl;

    @Value("${login.qq.get.openId}")
    private String getOpenIdUrl;


    @Value("${login.qq.get.user}")
    private String getUserUrl;


    @Autowired
    private QQService qqService;


    @ResponseBody
    @GetMapping("/qq/oauth")
    public Map<String, String> qq(HttpSession session) {
        Map<String, String> maps = Maps.newHashMap();
        //用于第三方应用防止CSRF攻击
        String state = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", state);
        maps.put("qqAppId", qqAppId);
        maps.put("backUrl", URLEncoder.encode(backUrl));
        maps.put("state", state);
        return maps;
    }


    /**
     * QQ回调
     *
     * @param request
     * @return
     */
    @GetMapping("/qqcalback")
    public void qqCallBack(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //qq返回的信息：http://graph.qq.com/demo/index.jsp?code=9A5F************************06AF&state=test
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String uuid = (String) session.getAttribute("state");

        //判断uuid 是否为空
        if (uuid != null) {
            if (!uuid.equals(state)) {
                log.error("QQ,state错误");
                throw new NotFountException("QQ,state错误");
            }
        }

        //Step2：通过Authorization Code获取Access Token
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + qqAppId +
                "&client_secret=" + qqAppleKey +
                "&code=" + code +
                "&redirect_uri=" + backUrl;

        String access_token = qqService.getAccessToken(url);

        log.info(access_token);


        //Step3: 获取回调后的 openid 值
        String openUrl = getOpenIdUrl.replace("ACCESS_TOKEN", access_token);

        String openid = qqService.getOpenID(openUrl);

        //Step4：获取QQ用户信息
        String userUrl = getUserUrl.replace("ACCESS_TOKEN", access_token).replace("APPID", qqAppId).replace("OPENID", openid);
        JSONObject jsonObject = qqService.getUserInfo(userUrl);

        //也可以放到Redis和mysql中
        UserBean userBean = qqService.saveQQUser(jsonObject, openid);
        //登录成功,写cookie
        request.getSession().setAttribute("userEntity", userBean);

        // QQ登录有点特殊，参数放在#后面，后台无法获取#后面的参数，只能用JS做中间转换
        String html = "<!DOCTYPE html>" +
                "<html lang=\"zh-cn\">" +
                "<head>" +
                "   <title>qq重定向页面</title>" +
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


        //java 输入出一个页面到前端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(html);
        writer.close();
    }


}
