package com.fsproject.floatlyshare.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IFavoriteMapper extends BaseMapper<Favorite> {

    Favorite getFavoriteByUserIdAndArticleId(int userId, Integer articleId);

    List<Article> getArticlesByUserId(int id);

    boolean deleteFavoriteByArticleId(int articleId,int userId);

    List<Article> getAllArticleByUserId(int id);
}
