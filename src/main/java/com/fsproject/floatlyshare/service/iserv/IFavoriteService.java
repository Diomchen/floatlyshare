package com.fsproject.floatlyshare.service.iserv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.Favorite;

import java.util.List;


public interface IFavoriteService extends IService<Favorite> {

    Favorite getFavoriteByUserIdAndArticleId(int userId, Integer articleId);

    List<Article> getArticlesByUserId(int id);
}
