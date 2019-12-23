package com.fsproject.floatlyshare.controller;

import com.alibaba.fastjson.JSONObject;
import com.fsproject.floatlyshare.bean.Favorite;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService favoriteService;

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
            System.out.println(isDelete);
            if (isDelete) {
                result.put("status", 200);
            } else {
                result.put("status", 201);
            }
        }
        return result;
    }
}
