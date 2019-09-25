package com.fsproject.floatlyshare.controller;

import com.fsproject.floatlyshare.bean.User;
import com.fsproject.floatlyshare.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class FSController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String visitWaterFallPage(){
        return "waterFall.html";
    }

    @RequestMapping("/loginAndRegist")
    public String visitLoginAndRequest(){
        User user = userService.selectUser(1);
        log.info(String.valueOf(user.getId()));
        System.out.println(user.getId());
        System.out.println(user.toString());
        return "loginandregist.html";
    }


}
