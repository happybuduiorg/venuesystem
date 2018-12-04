package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.entity.UserExternEntity;
import com.happybudui.venuesystem.mapper.UserMapper;
import com.happybudui.venuesystem.service.VenueService;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.happybudui.venuesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private AdminService adminService;
    private VenueService venueService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // 插入用户
    @RequestMapping(value = "insertuser",method = RequestMethod.POST)
    ResponseResult<Integer>insertUser(@RequestParam(name="usermail")String usermail,@RequestParam(name="username")String username,@RequestParam(name="userpassword")String userpassword) {
        return adminService.insertUser(usermail,username,userpassword);
    }

    // 删除用户
    @RequestMapping(value = "deleteuser", method = RequestMethod.GET)
    ResponseResult<Integer> deleteUserById(@RequestParam(name = "userid") String userId){
        return adminService.deleteUserById(userId);
    }

    // 更新用户名
    @RequestMapping(value = "updateuser", method = RequestMethod.POST)
    ResponseResult<Integer>updateUserNameById(@RequestParam(name="userid")String userId,@RequestParam(name = "username") String username) {
        return adminService.updateUserNameById(userId, username);
    }

    // 冻结账户
    @RequestMapping(value = "frozenuseraccount",method = RequestMethod.POST)
    ResponseResult<Integer> frozenUserById(@RequestParam(name = "userid") String userId){
        return adminService.frozenUserById(userId);
    }

    // 添加场馆
    @RequestMapping(value = "addVenue", method = RequestMethod.POST)
    ResponseResult<Integer> addVenue(@RequestParam("venuename") String venueName, @RequestParam("venueplace") String venuePlace, @RequestParam("venuedescription")String venueDescription,
                                     @RequestParam("venueprice") String venuePrice, @RequestParam("venueareanum")String venueAreaNum, @RequestParam("venueopentime")String venueOpenTime,
                                     @RequestParam("venueclosetime") String venueCloseTime, @RequestParam("venueinterval")String venueInterval){
        return venueService.addVenue(venueName, venuePlace, venueDescription,
                venuePrice, venueAreaNum, venueOpenTime,
                venueCloseTime, venueInterval);
    }

    // 删除场馆
    @RequestMapping(value = "deleteVenue", method = RequestMethod.POST)
    ResponseResult<Integer> deleteVenue(@RequestParam("venueid") String venueId){
        return venueService.deleteVenue(venueId);
    }

    // 设置场馆关闭
    @RequestMapping(value = "closeVenue", method = RequestMethod.POST)
    ResponseResult<Integer> closeVenue(@RequestParam("venueid") String venueId){
        return venueService.closeVenue(venueId);
    }

    // 设置场馆开放
    @RequestMapping(value = "openVenue", method = RequestMethod.POST)
    ResponseResult<Integer> openVenue(@RequestParam("venueid") String venueId){
        return venueService.openVenue(venueId);
    }

    // 更改场馆价格
    @RequestMapping(value = "changeVenuePrice", method = RequestMethod.POST)
    ResponseResult<Integer> changeVenuePrice(@RequestParam("venueid") String venueId, @RequestParam("venueprice") String venuePrice){
        return venueService.changeVenuePrice(venueId, venuePrice);
    }

    // 更改场馆描述
    @RequestMapping(value = "changeVenueDescription", method = RequestMethod.POST)
    ResponseResult<Integer> changeVenueDesciption(@RequestParam("venueid") String venueId, @RequestParam("venuedescription") String venueDescription){
        return venueService.changeVenueDesciption(venueId, venueDescription);
    }

    //上传文件接口
    @RequestMapping("upload")
    public ResponseResult<Integer> upLoad(HttpServletRequest request, MultipartFile file) {
        String upLoadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";

        File dir = new File(upLoadDir);
        if (dir.exists() == false) {
            dir.mkdir();
        }

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String saveName = UUID.randomUUID() + suffix;

        File fileToSave = new File(upLoadDir + saveName);

        try {
            file.transferTo(fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.success();
    }



}