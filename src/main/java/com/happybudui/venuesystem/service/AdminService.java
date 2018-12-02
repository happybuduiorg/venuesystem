package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.entity.UserEntity;
import com.happybudui.venuesystem.mapper.UserMapper;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Service
public class AdminService {
    private final UserMapper userMapper;

    @Autowired
    public AdminService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Transactional
    public ResponseResult<UserEntity> getUserInfoById(String userId){
        UserEntity userEntity = userMapper.getUserInfoById(userId);

        if(userEntity!=null) {
            return ResultGenerator.success(userEntity);
        }else{
            return ResultGenerator.error(300,"can't find any userinfo!");
        }
    }

    @Transactional
    public ResponseResult<Integer> insertUser(String userName,String userMail,String userpassword){
        UserEntity userEntity=new UserEntity(userName,userMail,userpassword);

        if(userMapper.insertUser(userEntity)==1){
            return  ResultGenerator.success();
        }else{
            return ResultGenerator.error("insert failed!");
        }
    }

    @Transactional
    public ResponseResult<Integer> deleteUserById(String userid){
        if(userMapper.deleteUserById(userid)==1){
            return  ResultGenerator.success();
        }else{
            return  ResultGenerator.error("insert failed!");
        }
    }

    @Transactional
    public ResponseResult<Integer> updateUserNameById(String userId, String username){
        if(userMapper.updateUserNameById(userId,username)==1)
            return  ResultGenerator.success();
        else
            return  ResultGenerator.error("delete failed!");
    }

    @Transactional
    public ResponseResult<Integer> frozenUserById(String userId){
        if(userMapper.frozenUserById(userId)==1)
            return  ResultGenerator.success();
        else
            return  ResultGenerator.error("frozen failed!");
    }

}
