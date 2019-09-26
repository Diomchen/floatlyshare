package com.fsproject.floatlyshare.service.iserv;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fsproject.floatlyshare.bean.User;

public interface IUserService extends IService<User> {
    User selectUser(int id);
    boolean insertUser(User newUser);
    boolean loginUser(String mail,String password);
}
