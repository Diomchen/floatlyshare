package com.fsproject.floatlyshare.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fsproject.floatlyshare.bean.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostArticleMapper extends BaseMapper<Article> {
}
