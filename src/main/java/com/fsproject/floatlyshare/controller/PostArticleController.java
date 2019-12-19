package com.fsproject.floatlyshare.controller;

import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class PostArticleController {

    @Autowired
    public IPostArticle iPostArticle;

    @RequestMapping(value="/PostArticle",method = RequestMethod.POST)
    public String PostArticle(@RequestParam("url") String url,@RequestParam("title") String title ,@RequestParam("content") String content,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Article article = new Article(title, content, url, user.getNickname());
        boolean success = iPostArticle.insertArticle(article);
        if(success){
            return "redirect:/";
        }
        else{
            return "error.html";
        }
    }

}
