package com.fsproject.floatlyshare.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@TableName("favorite")
@Data
public class Favorite extends Model<Favorite> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer articleId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
