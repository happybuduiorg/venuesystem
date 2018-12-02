package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.UserExternEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Date;
import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Mapper
@CacheConfig(cacheNames = "userextern")
public interface UserExternMapper {
    @Select("select * from userextern")
    @Cacheable(key ="#p0")
    List<UserExternEntity> getAllUserExternInfo();

    @Select("select * from userextern where userid=#{userId}::uuid")
    @Cacheable(key ="#p0")
    UserExternEntity getUserExternInfoById(@Param("userId")String userId);

    @Select("select userreadcount from userextern where userid=#{userId}::uuid")
    @Cacheable(key ="#p0")
    int getUserReadCountById(@Param("userId")String userId);

    @Insert("insert into userextern(userid,usergender,userbirthday,usersignature,userreadcount) values(#{userId}::uuid,#{userGender},#{userBirthday},#{userSignature},#{userReadCount})")
    int insertUserExternInfo(UserExternEntity userExternEntity);

    @Update("update userextern set isdelete = true where userid=#{userId}::uuid")
    @CachePut(key = "#p0")
    int deleteUserExternInfo(@Param("userId")String userId);

    @Update("update userextern set usergender = #{userGender} where userid = #{userId}::uuid")
    @CachePut(key = "#p0")
    int updateUserGender(@Param("userId") String userId,@Param("userGender")int userGender);

    @Update("update userextern set userbirthday = #{userBirthday} where userid = #{userId}::uuid")
    @CachePut(key = "#p0")
    int updateUserBirthday(@Param("userId") String userId,@Param("userBirthday") Date userBirthday);

    @Update("update userextern set usersignature = #{userSignature} where userid = #{userId}::uuid")
    @CachePut(key = "#p0")
    int updateUserSiganture(@Param("userId") String userId,@Param("userSignature") String userSignature);

    @Update("update userextern set userreadcount = #{userReadCount} where userid = #{userId}::uuid")
    @CachePut(key = "#p0")
    int updateUserReadCount(@Param("userId") String userId,@Param("userReadCount") int userReadCount);
}
