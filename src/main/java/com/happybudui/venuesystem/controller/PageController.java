package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

//用于控制返回静态网页
@Controller("/")
public class PageController {
    private final MailService mailService;
    @Autowired
    public PageController(MailService mailService){
        this.mailService=mailService;
    }

    @GetMapping("index")
    String index(HttpSession session){
        return "index";
    }

    @GetMapping("user/activemail")
    String activeMail(@RequestParam("maskid")String maskId,@RequestParam("vercode")String verCode,HttpSession session){
        boolean result=true;
        if(mailService.verMail(maskId,verCode)){
            return "mailverified";
        }else{
            return "Illegal Operation!";
        }
    }

}
