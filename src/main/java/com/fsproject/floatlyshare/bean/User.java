package com.fsproject.floatlyshare.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@TableName("user")
@Setter
@Getter
@ToString
public class User extends Model<User> {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String nickname;
    private String mail;
    private String password;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
