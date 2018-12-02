package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.NewsEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Mapper
@CacheConfig(cacheNames = "news")
public interface NewsMapper {

    @Insert("insert into news(newscontent,newspublicdate,newspublisername,newspublisherid,newslevel) values(#{newsContent},#{newsPublicDate},#{newsPublisherName},#{newPublisherId}::uuid,#{newsLevel})")
    public int insertNews(NewsEntity newsEntity);

    @Update("update news set isdelete = true where newsid = #{newsId}")
    @CachePut(key = "#p0")
    public int deleteNewByNewsId(@Param("newsId")int newsId);

    @Select("select * from news")
    @Cacheable(key ="#p0")
    public List<NewsEntity> getAllNews();

    @Select("select count(newsid) from news")
    @Cacheable(key ="#p0")
    public int getNewsNumber();

    @Select("select max(newsid) from news")
    @Cacheable("#p0")
    public int getNewsMaxId();

    @Update("update news set newslevel = #{newsLevel} where newsid = #{newId}")
    @CachePut(key = "#p0")
    public int changeNewsLevel(@Param("newsId")int newsId,@Param("newsLevel")int newsLevel);

}
