package com.happybudui.venuesystem.mapper;

import com.happybudui.venuesystem.entity.OrderEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Mapper
@CacheConfig(cacheNames = "order")
public interface OrderMapper {
    @Insert("insert into order(orderstatus,orderprice,userid,venueid,dayofweek,timeslot) values" +
            "(#{orderStatus},#{orderPrice},#{userId}::uuid ,#{venueId},#{dayOfWeek},#{timeSlot})")
    public int insertOrder(OrderEntity orderEntity);

    @Select("select * from order where orderid=#{orderId}::uuid")
    public OrderEntity getOrderByOrderId(@Param("orderId")String orderId);

    @Select("select * from order")
    public List<OrderEntity> getAllOrders();

    @Cacheable(key="#p0")
    @Select("select * from order where userid=#{userId}::uuid")
    public List<OrderEntity> getOrderByUserId(@Param("userId") String userId);

    @Cacheable(key="#p0")
    @Select("select * from order where orderstatus = #{orderStatus}")
    public List<OrderEntity> getOrderByOrderStatus(@Param("orderStatus")int orderStatus);

    @Select("select * from order where userid=#{userId} and orderstatus=#{orderStatus}")
    public List<OrderEntity> getUserOrderByStatus(@Param("userId")String userId,@Param("orderStatus")int orderStatus);

    @CacheEvict(key = "#p0",allEntries=true)
    @Delete("delete from order where orderid=#{orderId}::uuid")
    public int deleteOrderById(@Param("orderId")String orderId);

    @CachePut(key = "#p0")
    @Update("update order set orderstatus = #{orderStatus} where orderid=#{orderId}")
    public int changeOrderStatus(@Param("orderId")String orderId, @Param("orderStatus")int orderStatus);

}
