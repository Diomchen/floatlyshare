package com.fsproject.floatlyshare.controller;

import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginAndRegist {

    @Autowired
    public IUserService userService;
    //注册
    @RequestMapping(value = "/loginAndRegister/register",method = RequestMethod.POST)
    public String getUserRegister(String nickname,String mail,String password){
        User newUser = new User(nickname,mail,password);
        boolean isInsertSuccess = userService.insertUser(newUser);

        if(isInsertSuccess)
            return "success.html";
        else
            return "error.html";
    }

    //登录
    @RequestMapping(value = "/loginAndRegister/login",method = RequestMethod.POST)
    public String userLogin(String mail,String password,HttpServletRequest request){
        boolean isLoginSuccess = userService.loginUser(mail,password);
        User user = userService.selectUser(mail);
        request.setAttribute("username",user.getNickname());
        request.setAttribute("mail",mail);
        if(isLoginSuccess)
            return "UserManager.html";
        else
            return "error.html";
    }

}
