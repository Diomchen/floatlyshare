package com.fsproject.floatlyshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.dao.IUserMapper;
import com.fsproject.floatlyshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
    @Autowired
    private IUserMapper iUserMapper;

    public User selectUser(int id){
        return iUserMapper.selectUser(id);
    }
}
