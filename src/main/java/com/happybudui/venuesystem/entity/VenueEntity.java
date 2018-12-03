package com.happybudui.venuesystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Date;
import java.util.Objects;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class VenueEntity {
    int venueId;
    String venueName;
    String venuePlace;
    String venueDescription;
    Double venuePrice;
    int venueAreaNum;
    Date venueOpenTime;
    Date venueCloseTIme;
    int venueInterval;
    int venueMaxIntervals;
    boolean venueStatus = true;

    public VenueEntity() {
    }

    public VenueEntity(String venueName, String venuePlace, String venueDescription, Double venuePrice, int venueAreaNum, Date venueOpenTime, Date venueCloseTIme, int venueInterval, int venueMaxIntervals) {
        this.venueName = venueName;
        this.venuePlace = venuePlace;
        this.venueDescription = venueDescription;
        this.venuePrice = venuePrice;
        this.venueAreaNum = venueAreaNum;
        this.venueOpenTime = venueOpenTime;
        this.venueCloseTIme = venueCloseTIme;
        this.venueInterval = venueInterval;
        this.venueMaxIntervals = venueMaxIntervals;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenuePlace() {
        return venuePlace;
    }

    public void setVenuePlace(String venuePlace) {
        this.venuePlace = venuePlace;
    }

    public String getVenueDescription() {
        return venueDescription;
    }

    public void setVenueDescription(String venueDescription) {
        this.venueDescription = venueDescription;
    }

    public Double getVenuePrice() {
        return venuePrice;
    }

    public void setVenuePrice(Double venuePrice) {
        this.venuePrice = venuePrice;
    }

    public int getVenueAreaNum() {
        return venueAreaNum;
    }

    public void setVenueAreaNum(int venueAreaNum) {
        this.venueAreaNum = venueAreaNum;
    }

    public Date getVenueOpenTime() {
        return venueOpenTime;
    }

    public void setVenueOpenTime(Date venueOpenTime) {
        this.venueOpenTime = venueOpenTime;
    }

    public Date getVenueCloseTIme() {
        return venueCloseTIme;
    }

    public void setVenueCloseTIme(Date venueCloseTIme) {
        this.venueCloseTIme = venueCloseTIme;
    }

    public int getVenueInterval() {
        return venueInterval;
    }

    public void setVenueInterval(int venueInterval) {
        this.venueInterval = venueInterval;
    }

    public int getVenueMaxIntervals() {
        return venueMaxIntervals;
    }

    public void setVenueMaxIntervals(int venueMaxIntervals) {
        this.venueMaxIntervals = venueMaxIntervals;
    }

    public boolean isVenueStatus() {
        return venueStatus;
    }

    public void setVenueStatus(boolean venueStatus) {
        this.venueStatus = venueStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueEntity)) return false;
        VenueEntity that = (VenueEntity) o;
        return getVenueId() == that.getVenueId() &&
                getVenueAreaNum() == that.getVenueAreaNum() &&
                getVenueInterval() == that.getVenueInterval() &&
                getVenueMaxIntervals() == that.getVenueMaxIntervals() &&
                isVenueStatus() == that.isVenueStatus() &&
                Objects.equals(getVenueName(), that.getVenueName()) &&
                Objects.equals(getVenuePlace(), that.getVenuePlace()) &&
                Objects.equals(getVenueDescription(), that.getVenueDescription()) &&
                Objects.equals(getVenuePrice(), that.getVenuePrice()) &&
                Objects.equals(getVenueOpenTime(), that.getVenueOpenTime()) &&
                Objects.equals(getVenueCloseTIme(), that.getVenueCloseTIme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenueId(), getVenueName(), getVenuePlace(), getVenueDescription(), getVenuePrice(), getVenueAreaNum(), getVenueOpenTime(), getVenueCloseTIme(), getVenueInterval(), getVenueMaxIntervals(), isVenueStatus());
    }

    @Override
    public String toString() {
        return "VenueEntity{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", venuePlace='" + venuePlace + '\'' +
                ", venueDescription='" + venueDescription + '\'' +
                ", venuePrice=" + venuePrice +
                ", venueAreaNum=" + venueAreaNum +
                ", venueOpenTime=" + venueOpenTime +
                ", venueCloseTIme=" + venueCloseTIme +
                ", venueInterval=" + venueInterval +
                ", venueMaxIntervals=" + venueMaxIntervals +
                ", venueStatus=" + venueStatus +
                '}';
    }
}
