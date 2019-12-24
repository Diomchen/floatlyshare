package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Controller
@Slf4j
public class PostArticleController {
    OkHttpClient client = new OkHttpClient();

    @Autowired
    public IPostArticle iPostArticle;

    @RequestMapping(value="/PostArticle",method = RequestMethod.POST)
    public String PostArticle(@RequestParam("url") String url, @RequestParam("title") String title , @RequestParam("content") String content, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String ip = getYourIp(request);
        ArrayList<BigDecimal> pos = getYourPos("https://restapi.amap.com/v3/ip?ip="+ip+"&key=f184010ea5dbd52318221bd12db320d3",client);
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setPicture(url);
        article.setAuthor(user.getNickname());
        article.setX(pos.get(0));
        article.setY(pos.get(1));
        article.setDate(new Date());
        boolean success = iPostArticle.insertArticle(article);
        if(success){
            return "redirect:/";
        }
        else{
            return "error.html";
        }
    }

    public static ArrayList<BigDecimal> getYourPos(String url,OkHttpClient client) throws IOException {
        ArrayList<BigDecimal> pos = new ArrayList<>();
        String res = "";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()){
            res = response.body().string();
        }

        Map maps = (Map)JSON.parse(res);
        String rectangle = (String) maps.get("rectangle");
        if(maps.get("status").equals("1") && maps.get("infocode").equals("10000")){
            String leftDownPos = rectangle.split(";")[0];
            String rightUpPos = rectangle.split(";")[1];
            BigDecimal y1 = new BigDecimal(leftDownPos.split(",")[1]);
            BigDecimal y2 = new BigDecimal(rightUpPos.split(",")[1]);
            BigDecimal x1 = new BigDecimal(leftDownPos.split(",")[0]);
            BigDecimal x2 = new BigDecimal(rightUpPos.split(",")[0]);

            pos.add((new BigDecimal(leftDownPos.split(",")[0])).add(new BigDecimal(rightUpPos.split(",")[0])).divide(new BigDecimal(2)));
            pos.add((new BigDecimal(leftDownPos.split(",")[1])).add(new BigDecimal(rightUpPos.split(",")[1])).divide(new BigDecimal(2)));

        }
        else{
            pos.add(null);
            pos.add(null);
        }
        System.out.println(pos.get(0));
        return pos;
    }
    public static String getYourIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
