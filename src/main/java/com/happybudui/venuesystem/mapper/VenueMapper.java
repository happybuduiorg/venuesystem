package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.VenueEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
////Written by Happybudui

@Mapper
public interface VenueMapper {

    @Insert("insert into venue(venuename,venueplace,venuedescription,venueprice,venueareanum," +
            "venueopentime,venueclosetime,venueinterval,venuemaxintervals,venuestatus) values" +
            "(#{venueName},#{venuePlace},#{venueDescription},#{venuePrice},#{venueAreaNum},#{venueOpenTime}," +
            "#{venueCloseTime},#{venueInterval},#{venueMaxIntervals},#{venueStatus})")
    public int insertVenue(VenueEntity venueEntity);

    @CachePut(key = "#p0")
    @Select("select * from venue where venuestatus = true")
    public List<VenueEntity> getAllVenues();

    @CachePut(key = "#p0")
    @Select("select * from venue where venueid=#{venueId} and venuestatus = true")
    public VenueEntity getVenueById(@Param("venueId") int venueId);

    @CachePut(key = "#p0")
    @Delete("delete from venue where venueid=#{venueId}")
    public int deleteVenueById(@Param("venueId")int venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuestatus= false where venueId=#{venueId}")
    public int changeVenueStatusClose(@Param("venueId")int venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuestatus= true where venueId=#{venueId}")
    public int changeVenueStatusOpen(@Param("venueId")int venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuedescription=#{venueDescription} where venueId=#{venueId} and venuestatus = true ")
    public int changeVenueDescription(@Param("venueId")int venueId, @Param("venuedescription")String venueDescription);

    @CachePut(key = "#p0")
    @Update("update venue set venueprice=#{venuePrice} where venueId=#{venueId} and venuestatus = true")
    public int changeVenuePrice(@Param("venueId")int venueId, @Param("venueprice")int venuePrice);

    @CachePut(key = "#p0")
    @Update("update venue set venueareanum=#{venueAreaNum} where venueId=#{venueId} and venuestatus = true")
    public int changeVenueAreaNum(@Param("venueId")int venueId, @Param("venueareanum")int venueAreaNum);

    @CachePut(key = "#p0")
    @Update("update venue set venueopentime=#{venueOpenTime} where venueId=#{venueId} and venuestatus = true")
    public int changeVenueOpenTime(@Param("venueId")int venueId, @Param("venueopentime")String venueOpenTime);

    @CachePut(key = "#p0")
    @Update("update venue set venueclosetime=#{venueCloseTime} where venueId=#{venueId} and venuestatus = true")
    public int changeVenueCloseTime(@Param("venueId")int venueId, @Param("venueclosetime")String venueCloseTime);

    @CachePut(key = "#p0")
    @Update("update venue set venueinterval=#{venueInterval} where venueId=#{venueId} and venuestatus = true")
    public int changeVenueInterval(@Param("venueId")int venueId, @Param("venueinterval")int venueInterval);

    @CachePut(key = "#p0")
    @Update("update venue set venuemaxintervals=#{venueMaxIntervals} where venueId=#{venueId} and venuestatus = true")
    public int changeVenueMaxIntervals(@Param("venueId")int venueId, @Param("venuemaxintervals")int venueMaxIntervals);


}
