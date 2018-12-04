package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.bean.AreaBean;
import com.happybudui.venuesystem.entity.VenueEntity;
import com.happybudui.venuesystem.service.VenueService;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Controller
@RequestMapping("/venue/")
public class VenueController {
    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService){
        this.venueService=venueService;
    }

    // 查看指定场馆场地信息
    @RequestMapping(value = "getVenueAreaInfo", method = RequestMethod.POST)
    ResponseResult<AreaBean> getVenueAreaInfo(@RequestParam("venueid") String venueId){
        return venueService.getVenueAreaInfo(venueId);
    }

    // 查看所有场馆信息
    @RequestMapping(value = "getAllVenueInfo", method = RequestMethod.POST)
    ResponseResult<List<VenueEntity>> getAllVenueInfo(){
        return venueService.getAllVenueInfo();
    }
}
