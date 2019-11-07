package com.fsproject.floatlyshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.dao.IUserMapper;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Autowired
    private IUserMapper iUserMapper;

    public User selectUser(String mail){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("mail",mail);
        List<User> batchUser = iUserMapper.selectByMap(columnMap);
        return batchUser.get(0);
    }

    public boolean insertUser(User newUser) {
        return iUserMapper.insert(newUser)!=0;
    }

    public boolean loginUser(String mail,String password){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("mail",mail);
        columnMap.put("password",password);
        List<User> batchUser = iUserMapper.selectByMap(columnMap);
        return batchUser.size() == 1;
    }
}
