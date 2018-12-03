package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.VenueEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
////Written by Happybudui

@Mapper
public interface VenueMapper {

    @Insert("insert into venue(venuename,venueplace,venuedesciption,venueprice,venueareanum," +
            "venueopentime,venueclosetime,venueinterval,venuemaxintervals,venuestatus) values" +
            "(#{venueName},#{venuePlace},#{venueDescription},#{venuePrice},#{venueAreaNum},#{venueOpenTime}," +
            "#{venueCloseTime},#{venueInterval},#{venueMaxIntervals},#{venueStatus})")
    public int insertVenue(VenueEntity venueEntity);

    @Select("select * from venue where venuestatus = true")
    public List<VenueEntity> getAllVenues();

    @Select("select * from venue where venueid=#{venueId} and venuestatus = true")
    public VenueEntity getVenueById(@Param("venueId") int venueId);

    @Delete("delete from venue where venueid=#{venueId}")
    public int deleteVenueById(@Param("venueId")int venueId);

    @Update("update venue set venuestatus= false where venueId=#{venueId}")
    public int changeVenueStatusClose(@Param("venueId")int venueId);

    @Update("update venue set venuestatus= true where venueId=#{venueId}")
    public int changeVenueStatusOpen(@Param("venueId")int venueId);


}
