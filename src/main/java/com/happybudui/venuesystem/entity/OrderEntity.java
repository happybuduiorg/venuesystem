package com.happybudui.venuesystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class OrderEntity implements Serializable {

    private String orderId;
    private Date orderDate;
    private int orderStatus;
    private double orderPrice;
    private String userId;
    private int venueId;
    private int dayOfWeek;
    private int timeSlot;

    public OrderEntity() {
        this.orderId = UUID.randomUUID().toString();
        java.util.Date utilDate=new java.util.Date();
        this.orderDate = new Date(utilDate.getTime());
    }

    public OrderEntity(double orderPrice, String userId, int venueId, int dayOfWeek, int timeSlot) {
        this.orderPrice = orderPrice;
        this.userId = userId;
        this.venueId = venueId;
        this.dayOfWeek = dayOfWeek;
        this.timeSlot = timeSlot;
        this.orderId = UUID.randomUUID().toString();
        java.util.Date utilDate=new java.util.Date();
        this.orderDate = new Date(utilDate.getTime());
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity that = (OrderEntity) o;
        return getOrderStatus() == that.getOrderStatus() &&
                Double.compare(that.getOrderPrice(), getOrderPrice()) == 0 &&
                getVenueId() == that.getVenueId() &&
                getDayOfWeek() == that.getDayOfWeek() &&
                getTimeSlot() == that.getTimeSlot() &&
                Objects.equals(getOrderId(), that.getOrderId()) &&
                Objects.equals(getOrderDate(), that.getOrderDate()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderDate(), getOrderStatus(), getOrderPrice(), getUserId(), getVenueId(), getDayOfWeek(), getTimeSlot());
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", orderPrice=" + orderPrice +
                ", userId='" + userId + '\'' +
                ", venueId=" + venueId +
                ", dayOfWeek=" + dayOfWeek +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
