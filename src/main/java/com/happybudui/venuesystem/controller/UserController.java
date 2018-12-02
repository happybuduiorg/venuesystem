package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.entity.UserEntity;
import com.happybudui.venuesystem.service.UserService;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    protected static Logger logger= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    ResponseResult<UserEntity> userLogin(@RequestParam(name = "usermail") String userMail, @RequestParam(name="userpassword")String userPassword, HttpSession session){
        return userService.userLogin(userMail,userPassword,session);
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    ResponseResult<Integer> userLogOut(HttpSession session){
        return userService.userLogOut(session);
    }

    @RequestMapping(value="register",method = RequestMethod.POST)
    ResponseResult<Integer> userRegister(@RequestParam(name="usermail")String userMail,@RequestParam(name="username")String userName,@RequestParam("userpassword")String userPassword,@RequestParam("userage")int userAge,@RequestParam("userGender")int userGender){
        return  userService.userRegister(userMail,userName,userPassword);
    }

    @RequestMapping(value = "getinfo",method = RequestMethod.POST)
    ResponseResult<UserEntity> getUserInfo(HttpSession session){
        return userService.getUserInfo(session);
    }

    @RequestMapping(value = "changepassword",method = RequestMethod.POST)
    ResponseResult<Integer> changePassword(@RequestParam(name="password")String password,@RequestParam(name="newpassword")String newpassword,HttpSession session){
        return userService.changePassWord(password,newpassword,session);
    }

    @RequestMapping(value = "isMailActive", method = RequestMethod.POST)
    ResponseResult<Integer> isMailActive(@RequestParam(name="usermail")String userMail){
        return userService.isMailActive(userMail);
    }

    @RequestMapping(value = "resetpassword", method = RequestMethod.POST)
    ResponseResult<Integer> resetpassword(@RequestParam(name="usermail")String userMail, @RequestParam(name="entercode")String enterCode, @RequestParam(name="newpassword")String newPassword){
        return userService.resetPassword(userMail, enterCode, newPassword);
    }

}
