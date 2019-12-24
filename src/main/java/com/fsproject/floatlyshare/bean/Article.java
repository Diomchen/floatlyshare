package com.fsproject.floatlyshare.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@TableName("article")
@Data
public class Article extends Model<Article> {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String title;
    private String content;
    private String picture;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;
    private BigDecimal x;
    private BigDecimal y;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public Article(String title, String content, String picture, String author) {
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.author = author;
        this.date = new Date();
        this.x=null;
        this.y=null;
    }

    public Article(String title, String content, String picture, String author,BigDecimal x,BigDecimal y) {
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.author = author;
        this.date = new Date();
        this.x = x;
        this.y = y;
    }
}
