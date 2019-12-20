package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.Favorite;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IFavoriteService;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private IFavoriteService favoriteService;

    @RequestMapping("/")
    public String visitWaterFallPage(HttpServletRequest request, Model model) {
        //从数据库加载
        List<Article> articles = iPostArticle.selectArticle();
        model.addAttribute("articles", articles);
        return "waterFall.html";
    }

    @RequestMapping("/loginAndRegister")
    public String visitLoginAndRegister() {
        return "loginandregister.html";
    }

    @RequestMapping("/loginAndRegister/forgotPassword")
    public String visitForget() {
        return "forget.html";
    }

    @RequestMapping("/logined")
    public String logined2user(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<Article> articles = favoriteService.getArticlesByUserId(user.getId());
            model.addAttribute("articles", articles);
        }
        return "UserManager.html";
    }

    @RequestMapping("/article/json")
    @ResponseBody
    public JSONObject ajaxCallBack(@RequestBody JSONObject article, HttpServletRequest request) {
        String id = article.get("id").toString();
//        System.out.println("pic："+picurl);
        Article data = iPostArticle.getById(id);
        JSONObject result = new JSONObject();
//        System.out.println(data.getAuthor());
//        System.out.println(data.getDate());
        result.put("status", 200);
        result.put("data", data);
        result.put("alreadyAdded", false);
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Favorite favoriteByUserIdAndArticleId = favoriteService.getFavoriteByUserIdAndArticleId(user.getId(), Integer.valueOf(id));
            if (favoriteByUserIdAndArticleId != null) {
                result.put("alreadyAdded", true);
            }
        }
        return result;
    }

}
