package com.happybudui.venuesystem.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happybudui.venuesystem.entity.VenueEntity;
import com.happybudui.venuesystem.entity.VenueExternEntity;
import com.happybudui.venuesystem.exception.VenueSystemException;
import com.happybudui.venuesystem.mapper.VenueExternMapper;
import com.happybudui.venuesystem.mapper.VenueMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AreaBean {
    @JsonIgnore
    private final VenueMapper venueMapper;
    @JsonIgnore
    private final VenueExternMapper venueExternMapper;
    @JsonIgnore
    private VenueEntity venueEntity;
    @JsonIgnore
    private List<VenueExternEntity> venueExternEntityList;
    @JsonIgnore
    private int venueId;
    @JsonIgnore
    private int slotNum;
    @JsonIgnore
    private int areaCapacity;
    @JsonIgnore
    private int [][] tempArray;

    private List<List<Integer>> areaRemainMatrix = new ArrayList<>(7);

    public AreaBean(VenueMapper venueMapper,VenueExternMapper venueExternMapper){
        this.venueMapper=venueMapper;
        this.venueExternMapper=venueExternMapper;
    }

    public void initData(int venueId){
        this.venueId=venueId;
        venueEntity=venueMapper.getVenueById(venueId);
        venueExternEntityList=venueExternMapper.getVenueExternInfoById(venueId);
        if(venueEntity==null||venueExternEntityList==null)
            throw new VenueSystemException("Inner Error");

        Date openTime=venueEntity.getVenueOpenTime();
        Date closeTime=venueEntity.getVenueCloseTIme();
        int slotInterval=venueEntity.getVenueInterval();
        areaCapacity=venueEntity.getVenueAreaNum();
        slotNum=(int)((closeTime.getTime()-openTime.getTime())/(long)(60000*slotInterval));

        tempArray=new int[7][slotNum];
        for(int i=0;i<7;i++)
            for(int j=0;j<slotNum;j++)
                tempArray[i][j]=areaCapacity;

        refreshData();
    }

    public void refreshData(){
        for(int i=0;i<7;i++)
            for(int j=0;j<slotNum;j++)
                tempArray[i][j]=areaCapacity;

        for(VenueExternEntity venueExternEntity : venueExternEntityList){
            int dayOfWeek=venueExternEntity.getDayOfWeek();
            int slot=venueExternEntity.getVenueSlot();
            int areaRemain=venueExternEntity.getAreaRemain();
            tempArray[dayOfWeek-1][slot-1]=areaRemain;
        }

        for(int i=0;i<7;i++) {
            List<Integer> temp=new ArrayList<>();
            for (int j = 0; j < slotNum; j++) {
                temp.add(tempArray[i][j]);
            }
            areaRemainMatrix.add(temp);
        }
    }

}
