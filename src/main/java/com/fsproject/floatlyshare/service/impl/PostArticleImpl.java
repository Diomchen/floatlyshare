package com.fsproject.floatlyshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fsproject.floatlyshare.bean.Article;
import com.fsproject.floatlyshare.dao.PostArticleMapper;
import com.fsproject.floatlyshare.service.iserv.IPostArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostArticleImpl extends ServiceImpl<PostArticleMapper, Article> implements IPostArticle {

    @Autowired
    private PostArticleMapper postArticleMapper;

    @Override
    public boolean insertArticle(Article article){
        return article.insert();
    }

    @Override
    public List<Article> selectArticle() {

        List<Article> articles = postArticleMapper.selectList(null);
        return articles;
    }

    @Override
    public boolean selectArticleByAuthor(String authorName) {
        return false;
    }
}
