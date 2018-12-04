package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
