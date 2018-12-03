package com.happybudui.venuesystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class VenueExternEntity {
    int venueId;
    int dayOfWeek;
    int venueSlot;
    int areaRemain;

    public VenueExternEntity() {
    }

    public VenueExternEntity(int venueId, int dayOfWeek, int venueSlot, int areaRemain) {
        this.venueId = venueId;
        this.dayOfWeek = dayOfWeek;
        this.venueSlot = venueSlot;
        this.areaRemain = areaRemain;
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

    public int getVenueSlot() {
        return venueSlot;
    }

    public void setVenueSlot(int venueSlot) {
        this.venueSlot = venueSlot;
    }

    public int getAreaRemain() {
        return areaRemain;
    }

    public void setAreaRemain(int areaRemain) {
        this.areaRemain = areaRemain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueExternEntity)) return false;
        VenueExternEntity that = (VenueExternEntity) o;
        return getVenueId() == that.getVenueId() &&
                getDayOfWeek() == that.getDayOfWeek() &&
                getVenueSlot() == that.getVenueSlot() &&
                getAreaRemain() == that.getAreaRemain();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenueId(), getDayOfWeek(), getVenueSlot(), getAreaRemain());
    }

    @Override
    public String toString() {
        return "VenueExternEntity{" +
                "venueId=" + venueId +
                ", dayOfWeek=" + dayOfWeek +
                ", venueSlot=" + venueSlot +
                ", areaRemain=" + areaRemain +
                '}';
    }
}
