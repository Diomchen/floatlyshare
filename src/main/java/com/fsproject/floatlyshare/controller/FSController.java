package com.fsproject.floatlyshare.controller;

import com.fsproject.floatlyshare.service.iserv.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class FSController {


    @RequestMapping("/")
    public String visitWaterFallPage(){
        return "waterFall.html";
    }

    @RequestMapping("/loginAndRegister")
    public String visitLoginAndRegister(){
        return "loginandregister.html";
    }

    @RequestMapping("/loginAndRegister/forgotPassword")
    public String visitForget(){
        return "forget.html";
    }
}
