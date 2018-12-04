package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.entity.VenueEntity;
import com.happybudui.venuesystem.mapper.VenueExternMapper;
import com.happybudui.venuesystem.mapper.VenueMapper;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static com.happybudui.venuesystem.wrapper.ResultGenerator.error;
import static com.happybudui.venuesystem.wrapper.ResultGenerator.success;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
////Written by Happybudui

@Service
public class VenueService {
    private final VenueMapper venueMapper;
    private final VenueExternMapper venueExternMapper;

    @Autowired
    public VenueService(VenueMapper venueMapper,VenueExternMapper venueExternMapper){
        this.venueMapper=venueMapper;
        this.venueExternMapper=venueExternMapper;
    }

    //添加场馆
    @Transactional
    public ResponseResult<Integer> addVenue(String venueName,String venuePlace,String venueDescription,
                                            String venuePrice,String venueAreaNum,String venueOpenTime,
                                            String venueCloseTime,String venueInterval){

        VenueEntity venueEntity=new VenueEntity(venueName,venuePlace,venueDescription,Double.valueOf(venuePrice),
                Integer.valueOf(venueAreaNum), Date.valueOf(venueOpenTime),Date.valueOf(venueCloseTime),Integer.valueOf(venueInterval),2);

        venueMapper.insertVenue(venueEntity);

        return success("Add venue info successfully!");
    }

    //获取所有的场馆信息
    @Transactional
    public ResponseResult<List<VenueEntity>> getAllVenueInfo(){
        List<VenueEntity> venueEntityList=venueMapper.getAllVenues();
        return ResultGenerator.success(venueEntityList);
    }

    //删除场馆信息
    @Transactional
    public ResponseResult<Integer> deleteVenue(String venueId){
        venueMapper.deleteVenueById(Integer.valueOf(venueId));
        venueExternMapper.deleteVenueExternById(Integer.valueOf(venueId));
        return  ResultGenerator.success("delete successfully!");
    }

    //关闭场馆
    @Transactional
    public ResponseResult<Integer> closeVenue(String venueId){
        venueMapper.changeVenueStatusClose(Integer.valueOf(venueId));
        return success("venue has closed!");
    }

    //打开场馆
    @Transactional
    public ResponseResult<Integer> openVenue(String venueId){
        venueMapper.changeVenueStatusOpen(Integer.valueOf(venueId));
        return success("venue has closed!");
    }

    //修改场馆租赁价格
    @Transactional
    public ResponseResult<Integer> changeVenuePrice(String venueId,String price){
        venueMapper.changeVenuePrice(Integer.valueOf(venueId),Double.valueOf(price));
        return success("price has changed!");
    }

    //修改场馆开放关闭时间 过于复杂暂时不实现
    @Transactional
    public ResponseResult<Integer> changeVenueOpenAndCloseTime(String venueId,String startTime,String endTime){
        return error("interface has not opened yet!");
    }

    //修改场馆描述
    @Transactional
    public ResponseResult<Integer> changeVenueDesciption(String venueId,String venueDescription){
        venueMapper.changeVenueDescription(Integer.valueOf(venueId),venueDescription);
        return success("venue description has modified!");
    }
}
