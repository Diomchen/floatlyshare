package com.fsproject.floatlyshare.controller;

import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.iserv.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        if(isLoginSuccess)
            return "UserManager.html";
        else
            return "error.html";
    }

}
