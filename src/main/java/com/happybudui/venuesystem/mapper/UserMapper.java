package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Mapper
@CacheConfig(cacheNames = "user")
public interface UserMapper {

    //select userBasicInfo
    @Select("select * from \"user\" where userid =#{userId}::uuid and isdelete = false")
    @Cacheable(key ="#p0")
    UserEntity getUserInfoById(@Param("userId") String userId);

    @Select("select * from \"user\" where usermail =#{userMail} and isdelete =false")
    @Results({
            @Result(property = "userId",  column = "userid", jdbcType=VARCHAR,javaType=java.lang.String.class),
            @Result(property = "userMail", column = "usermail"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userPassword", column = "userpassword"),
            @Result(property = "userStatus", column = "userstatus"),
            @Result(property = "isMailActive", column = "ismailactive")
    })
    @Cacheable(key ="#p0")
    UserEntity getUserInfoByMail(@Param("userMail")String userMail);

    //insert user
    //插入时无需指定userid  数据库会自增
    @Insert("insert into \"user\"(userid,username,usermail,userpassword,userstatus) values(#{userId}::uuid, #{userName}, #{userMail},#{userPassword},#{userStatus})")
    int insertUser(UserEntity userEntity);

    //update userBasicInfo
    @CachePut(key = "#p0")
    @Update("update \"user\" set username =#{userName} where userid =#{userId}::uuid and isdelete = false")
    int updateUserNameById(@Param("userId")String userId, @Param("userName") String userName);

    @CachePut(key = "#p0")
    @Update("update \"user\" set userpassword =#{userPassword} where userid =#{userId}::uuid and isdelete =false ")
    int updateUserPasswordById(@Param("userId")String userId, @Param("userPassword")String userPassword);

    @CachePut(key = "#p0")
    @Update("update \"user\" set userstatus =#{userStatus} where userid =#{userId}::uuid and isdelete = false")
    int updateUserStatusdById(@Param("userId")String userId, @Param("userStatus")int userStatus);

    @CachePut(key = "#p0")
    @Update("update \"user\" set usermail =#{userMail} where userid =#{userId}::uuid and isdelete = false")
    int updateUserMailById(@Param("userId")String userId, @Param("userMail")String userMail);

    //frozen user
    @CacheEvict(key ="#p0",allEntries=true)
    @Update("update \"user\" set userstatus = 1 where userid =#{userId}::uuid and userstatus = 0")
    int frozenUserById(@Param("userId") String userId);

    //delete user
    @CacheEvict(key ="#p0",allEntries=true)
    @Delete("update \"user\" set isdelete = true where userid =#{userId}::uuid")
    int deleteUserById(@Param("userId") String userId);

    //make user's mail valid
    @CachePut(key = "#p0")
    @Update("update \"user\" set ismailactive = true where userid =#{userId}::uuid")
    int updateUserMailActiveStatus(@Param("userId")String userId);

    @CachePut(key = "#p0")
    @Select("select \"ismailactive\" from \"user\" where usermail=#{usermail} and isdelete = false")
    boolean getUserIsMailActiveByMail(@Param("userMail")String userMail);
}