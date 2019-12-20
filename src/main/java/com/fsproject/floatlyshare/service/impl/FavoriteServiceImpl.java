package com.fsproject.floatlyshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.bean.Favorite;
import com.fsproject.floatlyshare.dao.IFavoriteMapper;
import com.fsproject.floatlyshare.service.iserv.IFavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteServiceImpl extends ServiceImpl<IFavoriteMapper, Favorite> implements IFavoriteService {
    @Override
    public Favorite getFavoriteByUserIdAndArticleId(int userId, Integer articleId) {
        return this.baseMapper.getFavoriteByUserIdAndArticleId(userId,articleId);
    }

    @Override
    public List<Article> getArticlesByUserId(int id) {
        return this.baseMapper.getArticlesByUserId(id);
    }
}
