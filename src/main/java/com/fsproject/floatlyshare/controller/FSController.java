package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class FSController {


    @Autowired
    public IPostArticle iPostArticle;

    @RequestMapping("/")
    public String visitWaterFallPage(HttpServletRequest request){
        //从数据库加载
        List<Article> articles = iPostArticle.selectArticle();
        HttpSession session = request.getSession();
        session.setAttribute("articles",articles);
        return "waterFall.html";
    }

    @RequestMapping("/loginAndRegister")
    public String visitLoginAndRegister(){
        return "loginandregister.html";
    }

    @RequestMapping("/loginAndRegister/forgotPassword")
    public String visitForget(){
        return "forget.html";
    }

    @RequestMapping("/logined")
    public String logined2user(){
        return "UserManager.html";
    }

    @RequestMapping("/article/json")
    @ResponseBody
    public JSONObject ajaxCallBack(@RequestBody JSONObject article){
        String picurl = article.get("oldUrl").toString();
//        System.out.println("pic："+picurl);
        Article data = iPostArticle.selectArticleByPicImage(picurl);
        JSONObject result = new JSONObject();
//        System.out.println(data.getAuthor());
//        System.out.println(data.getDate());
        result.put("status",200);
        result.put("data",data);
        return result;
    }

}
