package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.Favorite;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IFavoriteService;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private IPostArticle iPostArticle;

    @RequestMapping("/add")
    @ResponseBody
    public Object addFavorite(@RequestBody JSONObject param, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        JSONObject result = new JSONObject();
        result.put("status", 0);
        if (user != null) {
            String articleId = param.get("articleId").toString();
            Favorite favoriteByUserIdAndArticleId = favoriteService.getFavoriteByUserIdAndArticleId(user.getId(), Integer.valueOf(articleId));

            if (favoriteByUserIdAndArticleId == null) {
                Favorite favorite = new Favorite();
                favorite.setArticleId(Integer.valueOf(articleId));
                favorite.setUserId(user.getId());
                favoriteService.save(favorite);
                result.put("status", 200);
            } else {
                result.put("status", 201);
            }
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteFavorite(@RequestBody JSONObject param, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        JSONObject result = new JSONObject();
        result.put("status", 0);
        if (user != null) {
            String articleId = param.get("articleId").toString();
            boolean isDelete = favoriteService.deleteFavoriteByArticleId(Integer.valueOf(articleId),user.getId());
            if (isDelete) {
                result.put("status", 200);
            } else {
                result.put("status", 201);
            }
        }
        return result;
    }

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public Object deleteArticle(@RequestBody JSONObject param, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        JSONObject result = new JSONObject();
        result.put("status", 0);
        if (user != null) {
            String articleId = param.get("articleId").toString();
            boolean isDelete = favoriteService.deleteArticleByArticleId(Integer.valueOf(articleId));
            if (isDelete) {
                result.put("status", 200);
            } else {
                result.put("status", 201);
            }
        }
        return result;
    }

    @RequestMapping("/getPosAndImg")
    @ResponseBody
    public Object getPosAndImage(@RequestBody JSONObject param, Model model, HttpServletRequest request){
        List<Article> articles = (List<Article>) iPostArticle.selectArticle();
        ArrayList<BigDecimal> posX = new ArrayList<>();
        ArrayList<BigDecimal> posY = new ArrayList<>();
        ArrayList<String> picS = new ArrayList<>();
        for (int i=0 ; i<articles.size() ; i++){
            if(articles.get(i).getX()==null||articles.get(i).getY()== null||articles.get(i).getPicture()==null){
                continue;
            }
            else{
                posX.add(articles.get(i).getX());
                posY.add(articles.get(i).getY());
                picS.add(articles.get(i).getPicture());
            }
        }
        JSONObject result = new JSONObject();
        result.put("status", 0);

        if (!posX.isEmpty()) {
            result.put("status", 200);
            result.put("posX",posX);
            result.put("posY",posY);
            result.put("pictures",picS);
        } else {
            result.put("status", 201);
        }
        return result;
    }
}
