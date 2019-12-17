package com.fsproject.floatlyshare.service.iserv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fsproject.floatlyshare.bean.Article;

import java.util.List;

public interface IPostArticle extends IService<Article> {
     boolean insertArticle(Article Article);
     List<Article> selectArticle();
     boolean selectArticleByAuthor(String authorName);
     Article selectArticleByPicImage(String url);
}
