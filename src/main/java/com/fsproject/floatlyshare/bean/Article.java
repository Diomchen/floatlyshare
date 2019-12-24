package com.fsproject.floatlyshare.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@TableName("article")
@Data
//@Builder
//@AllArgsConstructor
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

}
