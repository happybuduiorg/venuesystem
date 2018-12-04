package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.bean.AreaBean;
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
import java.util.Map;

import static com.happybudui.venuesystem.wrapper.ResultGenerator.error;
import static com.happybudui.venuesystem.wrapper.ResultGenerator.success;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
////Written by Happybudui

@Service
public class VenueService {
    private final VenueMapper venueMapper;
    private final VenueExternMapper venueExternMapper;
    public static Map<Integer,AreaBean> venueAreaMap;

    @Autowired
    public VenueService(VenueMapper venueMapper,VenueExternMapper venueExternMapper){
        this.venueMapper=venueMapper;
        this.venueExternMapper=venueExternMapper;
        initVenueAreaInfo();
    }

    //初始化场馆信息
    public void initVenueAreaInfo(){
        List<VenueEntity> venueEntityList=venueMapper.getAllVenues();
        for(VenueEntity venueEntity : venueEntityList){
            int venueId=venueEntity.getVenueId();
            AreaBean areaBean=new AreaBean(venueMapper,venueExternMapper);
            areaBean.initData(venueId);
            venueAreaMap.put(venueId,areaBean);
        }
    }

    //根据场馆ID获取场地信息
    public ResponseResult<AreaBean> getVenueAreaInfo(String venueId){
        AreaBean areaBean=venueAreaMap.get(Integer.valueOf(venueId));
        if(areaBean!=null)
            return ResultGenerator.success(areaBean);
        else
            return ResultGenerator.error("Illegal Operation!");
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
