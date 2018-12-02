package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.entity.UserEntity;
import com.happybudui.venuesystem.entity.UserExternEntity;
import com.happybudui.venuesystem.mapper.UserExternMapper;
import com.happybudui.venuesystem.mapper.UserMapper;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static com.happybudui.venuesystem.wrapper.ResultGenerator.success;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserExternMapper userExternMapper;
    private final MailService mailService;

    @Autowired
    public UserService(UserMapper userMapper,UserExternMapper userExternMapper,MailService mailService){
        this.userMapper = userMapper;
        this.userExternMapper=userExternMapper;
        this.mailService = mailService;
    }

    //用户注册接口
    @Transactional
    public ResponseResult<Integer> userRegister(String userMail,String userName,String userPassword){
        UserEntity userEntity=userMapper.getUserInfoByMail(userMail);

        if(userEntity!=null){
            return ResultGenerator.error("username has exists!");
        }

        UserEntity newUserEntity=new UserEntity(userMail,userName,userPassword);
        UserExternEntity newUserExternEntity=new UserExternEntity(newUserEntity.getUserId());

        if(userMapper.insertUser(newUserEntity)==1&&userExternMapper.insertUserExternInfo(newUserExternEntity)==1){
            mailService.sendActiveMail(newUserEntity.getUserId(),newUserEntity.getUserMail());
            return  success("add user successfully!");
        }else{
            return ResultGenerator.error("add user failed!");
        }
    }

    //用户登陆
    @Transactional
    public ResponseResult<UserEntity> userLogin(String userMail, String userPassword, HttpSession session){

        UserEntity userEntity=userMapper.getUserInfoByMail(userMail);
        if(userEntity==null){
            return ResultGenerator.error("user doesn't exist!");
        }

        if(userEntity.getUserPassword().equals(userPassword)){
            if(userEntity.getUserStatus()==1){
                return ResultGenerator.error("account has been frozen!");
            }

            session.setAttribute("isLogin","true");
            session.setAttribute("userId",userEntity.getUserId());
            session.setAttribute("userMail",userEntity.getUserMail());
            session.setAttribute("userName",userEntity.getUserName());
            return success(userEntity);

        }else{
            return ResultGenerator.error("wrong name or password!");
        }
    }

    //用户注销
    @Transactional
    public ResponseResult<Integer> userLogOut(HttpSession session){
        session.invalidate();
        return ResultGenerator.success("Logout successfully!");
    }

    //如果用户登陆 那么可以获取自己的信息
    @Transactional
    public ResponseResult<UserEntity> getUserInfo(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId!=null&&userMapper.getUserInfoById(userId) != null) {
            return success(userMapper.getUserInfoById(userId));
        }

        return ResultGenerator.error("Inner Error!");
    }

    //判断邮箱是否注册并发送找回邮件
    @Transactional
    public ResponseResult<Integer> isMailActive(String userMail){
        if(userMapper.getUserIsMailActiveByMail(userMail)){
            mailService.sendVerCodeMail(userMail);
            return success();
        }
        return ResultGenerator.error("Error!");
    }

    //用户找回密码验证en
    @Transactional
    public ResponseResult<Integer> resetPassword(String userMail, String enterCode, String newpassword){
        if(mailService.verCodeMail(userMail, enterCode)){
            UserEntity thisuser = userMapper.getUserInfoByMail(userMail);
            userMapper.updateUserPasswordById(thisuser.getUserId(), newpassword);
            return success("reset password successfully!");
        }
        else{
            return ResultGenerator.error("Error!");
        }
    }

    //用户修改密码
    @Transactional
    public ResponseResult<Integer> changePassWord(String userPassword,String newPassword,HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null && userMapper.getUserInfoById(userId) != null) {
            UserEntity thisUser=userMapper.getUserInfoById(userId);
            if(thisUser.getUserPassword().equals(userPassword)){
                userMapper.updateUserPasswordById(thisUser.getUserId(),newPassword);
                return ResultGenerator.success("change Password successfully!");

            }else{
                return ResultGenerator.error("Wrong Password!");
            }
        }
        return ResultGenerator.error("Inner Error!");
    }

    //用户修改昵称
    @Transactional
    public ResponseResult<Integer> changeUserName(String newUserName,HttpSession session){
        String userId = (String)session.getAttribute("userId");
        if (userId != null && userMapper.getUserInfoById(userId) != null) {
              userMapper.updateUserNameById(userId,newUserName);
              return success("Change nickName Successfully!");
        }else{
            return ResultGenerator.error("Inner Error!");
        }
    }
}
