package com.fsproject.floatlyshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fsproject.floatlyshare.bean.User;

public interface IUserService extends IService<User> {
    User selectUser(int id);
}
