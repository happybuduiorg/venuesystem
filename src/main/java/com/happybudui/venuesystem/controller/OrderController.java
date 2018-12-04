package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.entity.OrderEntity;
import com.happybudui.venuesystem.service.OrderService;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Controller
@RequestMapping("/order/")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    // 用户创建订单
    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    ResponseResult<Integer> addOrder(@RequestParam(name = "orderdate") String orderDate, @RequestParam(name = "orderstatus") String orderStatus, @RequestParam(name = "orderprice") String orderPrice, @RequestParam(name = "venueid") String venueId, @RequestParam(name = "dayofweek") String dayOfWeek, @RequestParam(name = "timeslot") String timeSlot, HttpSession session) {
        return orderService.addOrder(orderDate, orderStatus, orderPrice, venueId, dayOfWeek, timeSlot, session);
    }

    //用户获取自己所有订单
    @RequestMapping(value = "getUserAllOrders", method = RequestMethod.POST)
    ResponseResult<List<OrderEntity>> getUserAllOrders(HttpSession session) {
        return orderService.getUserAllOrders(session);
    }

    //用户根据订单状态获取所有订单
    @RequestMapping(value = "getUserOrdersByStatus", method = RequestMethod.POST)
    ResponseResult<List<OrderEntity>> getUserOrdersByStatus(HttpSession session, @RequestParam(name = "orderstatus") String orderStatus) {
        return orderService.getUserOrdersByStatus(session, orderStatus);
    }

    //用户取消订单
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    ResponseResult<Integer> cancelOrder(@RequestParam(name = "orderid") String orderId) {
        return orderService.cancelOrder(orderId);
    }

}
