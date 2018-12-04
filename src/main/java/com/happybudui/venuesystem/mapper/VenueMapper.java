package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.VenueEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Date;
import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
////Written by Happybudui

@Mapper
@CacheConfig(cacheNames = "venue")
public interface VenueMapper {

    @Insert("insert into venue(venuename,venueplace,venuedescription,venueprice,venueareanum," +
            "venueopentime,venueclosetime,venueinterval,venuemaxintervals,venuestatus) values" +
            "(#{venueName},#{venuePlace},#{venueDescription},#{venuePrice},#{venueAreaNum},#{venueOpenTime}," +
            "#{venueCloseTime},#{venueInterval},#{venueMaxIntervals},#{venueStatus})")
    public int insertVenue(VenueEntity venueEntity);

    @Select("select * from venue where venuestatus = true")
    public List<VenueEntity> getAllVenues();

    @Cacheable(key="#p0")
    @Select("select * from venue where venueid=#{venueId} and venuestatus = true")
    public VenueEntity getVenueById(@Param("venueId") Integer venueId);

    @CacheEvict(key ="#p0",allEntries=true)
    @Delete("delete from venue where venueid=#{venueId}")
    public Integer deleteVenueById(@Param("venueId")Integer venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuestatus= false where venueId=#{venueId}")
    public Integer changeVenueStatusClose(@Param("venueId")Integer venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuestatus= true where venueId=#{venueId}")
    public Integer changeVenueStatusOpen(@Param("venueId")Integer venueId);

    @CachePut(key = "#p0")
    @Update("update venue set venuedescription=#{venueDescription} where venueId=#{venueId} and venuestatus = true ")
    public Integer changeVenueDescription(@Param("venueId")Integer venueId, @Param("venueDescription")String venueDescription);

    @CachePut(key = "#p0")
    @Update("update venue set venueprice=#{venuePrice} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenuePrice(@Param("venueId")Integer venueId, @Param("venuePrice")Double venuePrice);

    @CachePut(key = "#p0")
    @Update("update venue set venueareanum=#{venueAreaNum} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenueAreaNum(@Param("venueId")Integer venueId, @Param("venueAreaNum")int venueAreaNum);

    @CachePut(key = "#p0")
    @Update("update venue set venueopentime=#{venueOpenTime} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenueOpenTime(@Param("venueId")Integer venueId, @Param("venueOpenTime") Date venueOpenTime);

    @CachePut(key = "#p0")
    @Update("update venue set venueclosetime=#{venueCloseTime} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenueCloseTime(@Param("venueId")Integer venueId, @Param("venueCloseTime") Date venueCloseTime);

    @CachePut(key = "#p0")
    @Update("update venue set venueinterval=#{venueInterval} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenueInterval(@Param("venueId")Integer venueId, @Param("venueInterval")int venueInterval);

    @CachePut(key = "#p0")
    @Update("update venue set venuemaxintervals=#{venueMaxIntervals} where venueId=#{venueId} and venuestatus = true")
    public Integer changeVenueMaxIntervals(@Param("venueId")Integer venueId, @Param("venueMaxIntervals")int venueMaxIntervals);

}