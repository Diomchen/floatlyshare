package com.fsproject.floatlyshare.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fsproject.floatlyshare.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper extends BaseMapper<User> {
    User selectUser(int id);
}
