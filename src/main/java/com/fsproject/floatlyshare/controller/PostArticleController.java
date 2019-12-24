package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import com.fsproject.floatlyshare.service.iserv.IUserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
//        String ip = getYourIp(request);
        ArrayList<Float> pos = getYourPos("https://restapi.amap.com/v3/ip?key=f184010ea5dbd52318221bd12db320d3",client);
//        Article article = new Article(title, content, url, user.getNickname());
//        boolean success = iPostArticle.insertArticle(article);
//        if(success){
            return "redirect:/";
//        }
//        else{
//            return "error.html";
//        }
    }

    public static ArrayList<Float> getYourPos(String url,OkHttpClient client) throws IOException {
        ArrayList<Float> pos = new ArrayList<>();
        String res = "";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()){
            res = response.body().string();
        }
        return pos;
    }
}
