package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.bean.AreaBean;
import com.happybudui.venuesystem.entity.OrderEntity;
import com.happybudui.venuesystem.entity.VenueEntity;
import com.happybudui.venuesystem.entity.VenueExternEntity;
import com.happybudui.venuesystem.mapper.OrderMapper;
import com.happybudui.venuesystem.mapper.VenueExternMapper;
import com.happybudui.venuesystem.mapper.VenueMapper;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static com.happybudui.venuesystem.wrapper.ResultGenerator.success;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final VenueMapper venueMapper;
    private final VenueExternMapper venueExternMapper;
    private Map<Integer,AreaBean> venueAreaMap;

    public OrderService(OrderMapper orderMapper, VenueMapper venueMapper, VenueExternMapper venueExternMapper) {
        this.orderMapper = orderMapper;
        this.venueMapper = venueMapper;
        this.venueExternMapper = venueExternMapper;
        venueAreaMap=VenueService.venueAreaMap;
    }

    // 用户创建订单
    @Transactional
    public ResponseResult<Integer> addOrder(String orderDate, String orderStatus, String orderPrice,
                                            String venueId, String dayOfWeek, String timeSlot, HttpSession session) {
        if (venueMapper.getVenueById(Integer.valueOf(venueId)).isVenueStatus()) {
            if(venueExternMapper.getVenueAreaRemain(Integer.valueOf(venueId), Integer.valueOf(dayOfWeek), Integer.valueOf(timeSlot))==null) {
                    venueExternMapper.insertVenueExternInfo(new VenueExternEntity(Integer.valueOf(venueId),Integer.valueOf(dayOfWeek),Integer.valueOf(timeSlot),venueMapper.getVenueById(Integer.valueOf(venueId)).getVenueAreaNum()));
            }

            if (venueExternMapper.getVenueAreaRemain(Integer.valueOf(venueId), Integer.valueOf(dayOfWeek), Integer.valueOf(timeSlot)) != 0) {
                String thisUserId = (String) session.getAttribute("userId");

                int currentAreaRemain = venueExternMapper.getVenueAreaRemain(Integer.valueOf(venueId), Integer.valueOf(dayOfWeek), Integer.valueOf(timeSlot));

                orderMapper.insertOrder(new OrderEntity(Double.valueOf(orderPrice), thisUserId, Integer.valueOf(venueId),
                        Integer.valueOf(dayOfWeek), Integer.valueOf(timeSlot)));

                venueExternMapper.updateVenueAreaRemain(Integer.valueOf(venueId), Integer.valueOf(dayOfWeek), Integer.valueOf(timeSlot), currentAreaRemain - 1);
                ((AreaBean)venueAreaMap.get(venueId)).refreshData();

                return success("Insert Order successfully!");
            } else {
                return ResultGenerator.error("No idle area!");
            }
        } else {
            return ResultGenerator.error("Venue is unopened!");
        }
    }

    //获取用户所有订单
    @Transactional
    public ResponseResult<List<OrderEntity>> getUserAllOrders(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            List<OrderEntity> allUserOrders = orderMapper.getOrderByUserId(userId);
            return success(allUserOrders);
        }
        return ResultGenerator.error("Inner Error!");
    }

    //根据订单状态获取所有订单
    @Transactional
    public ResponseResult<List<OrderEntity>> getUserOrdersByStatus(HttpSession session, String orderStatus) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            List<OrderEntity> allUserOrders = orderMapper.getUserOrderByStatus(userId, Integer.valueOf(orderStatus));
            return success(allUserOrders);
        }
        return ResultGenerator.error("Inner Error!");
    }

    //取消订单
    @Transactional
    public ResponseResult<Integer> cancelOrder(String orderId){
        orderMapper.changeOrderStatus(orderId,3);
        OrderEntity orderEntity=orderMapper.getOrderByOrderId(orderId);
        int currentAreaRemain = venueExternMapper.getVenueAreaRemain(orderEntity.getVenueId(),orderEntity.getDayOfWeek(),orderEntity.getTimeSlot());
        venueExternMapper.updateVenueAreaRemain(orderEntity.getVenueId(),orderEntity.getDayOfWeek(),orderEntity.getTimeSlot(),currentAreaRemain+1);
        ((AreaBean)venueAreaMap.get(orderEntity.getVenueId())).refreshData();

        return ResultGenerator.success("order has canceled!");
    }

    //管理员接口

    //获取所有订单
    @Transactional
    public ResponseResult<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> allOrders = orderMapper.getAllOrders();
        return ResultGenerator.success(allOrders);
    }

    //根据订单状态获取所有订单
    @Transactional
    public ResponseResult<List<OrderEntity>> getOrdersByStatus(String orderStatus) {
        List<OrderEntity> allOrders = orderMapper.getOrderByOrderStatus(Integer.valueOf(orderStatus));
        return ResultGenerator.success(allOrders);
    }

    //审核并通过订单
    @Transactional
    public ResponseResult<Integer> approveOrder(String orderId){
        orderMapper.changeOrderStatus(orderId,1);
        return ResultGenerator.success("approved!");
    }

    //审核并拒绝订单
    @Transactional
    public ResponseResult<Integer> disApproveOrder(String orderId){
        orderMapper.changeOrderStatus(orderId,2);
        OrderEntity orderEntity=orderMapper.getOrderByOrderId(orderId);
        int currentAreaRemain = venueExternMapper.getVenueAreaRemain(orderEntity.getVenueId(),orderEntity.getDayOfWeek(),orderEntity.getTimeSlot());
        venueExternMapper.updateVenueAreaRemain(orderEntity.getVenueId(),orderEntity.getDayOfWeek(),orderEntity.getTimeSlot(),currentAreaRemain+1);
        ((AreaBean)venueAreaMap.get(orderEntity.getVenueId())).refreshData();

        return ResultGenerator.success("disapproved!");
    }

}
