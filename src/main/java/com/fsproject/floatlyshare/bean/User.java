package com.fsproject.floatlyshare.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@TableName("user")
@Data
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

    public User(String nickname,String mail,String password){
        this.nickname = nickname;
        this.mail = mail;
        this.password = password;
    }

}
