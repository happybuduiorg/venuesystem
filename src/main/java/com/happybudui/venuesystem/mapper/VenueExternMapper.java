package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.VenueExternEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
@CacheConfig(cacheNames = "venueextern")
public interface VenueExternMapper {

    @Insert("insert into venueextern(venueid, dayofweek, venueslot, arearemain) values(#{venueId}, #{dayOfWeek}, #{venueSlot}, #{areaRemain})")
    int insertVenueExternInfo(VenueExternEntity venueExternEntity);

    @Select("select * from venueextern")
    List<VenueExternEntity> getAllVenueExternInfo();

    @Cacheable(key="#p0")
    @Select("select * from venueextern where venueid=#{venueId}")
    List<VenueExternEntity> getVenueExternInfoById(@Param("venueId")int venueId);

    @Cacheable()
    @Select("select arearemain from venueextern where venueid = #{venueId} and dayofweek = #{dayOfWeek} and venueslot = #{venueSlot}")
    int getVenueAreaRemain(@Param("venueId")int venueId, @Param("dayOfWeek")int dayOfWeek, @Param("venueSlot")int venueSlot);

    @Cacheable(key="#p0")
    @Update("update venueextern set arearemain = #{areaRemain} where venueid = #{venueId} and dayofweek = #{dayOfWeek} and venueslot = #{venueSlot}")
    int getVenueAreaRemain(@Param("venueId")int venueId, @Param("dayOfWeek")int dayOfWeek, @Param("venueSlot")int venueSlot, @Param("arearemain")int areaRemain);

    @CachePut(key="#p0")
    @Update("update venueextern set arearemain = #{areaRemain} where venueid = #{venueId} and dayofweek = #{dayOfWeek} and venueslot = #{venueSlot}")
    int updateVenueAreaRemain(@Param("venueId")int venueId, @Param("dayOfWeek")int dayOfWeek, @Param("venueSlot")int venueSlot, @Param("areaRemain")int areaRemain);

    @CacheEvict(key="#p0", allEntries=true)
    @Delete("delete from venueextern where venueid=#{venueId}")
    int deleteVenueExternById(@Param("venueId")int venueId);
}
