package com.happybudui.venuesystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class UserExternEntity implements Serializable {

    private String userId;
    private int userGender;
    private Date userBirthday;
    private String userSignature;
    private int userReadCount = 0;

    public UserExternEntity() {
    }

    public UserExternEntity(String userId) {
        this.userId = userId;
    }

    public UserExternEntity(String userId, int userGender, Date userBirthday, String userSignature, int userReadCount) {
        this.userId = userId;
        this.userGender = userGender;
        this.userBirthday = userBirthday;
        this.userSignature = userSignature;
        this.userReadCount = userReadCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public int getUserReadCount() {
        return userReadCount;
    }

    public void setUserReadCount(int userReadCount) {
        this.userReadCount = userReadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserExternEntity)) return false;
        UserExternEntity that = (UserExternEntity) o;
        return getUserGender() == that.getUserGender() &&
                getUserReadCount() == that.getUserReadCount() &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getUserBirthday(), that.getUserBirthday()) &&
                Objects.equals(getUserSignature(), that.getUserSignature());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserGender(), getUserBirthday(), getUserSignature(), getUserReadCount());
    }

    @Override
    public String toString() {
        return "UserExternEntity{" +
                "userId='" + userId + '\'' +
                ", userGender=" + userGender +
                ", userBirthday=" + userBirthday +
                ", userSignature='" + userSignature + '\'' +
                ", userReadCount=" + userReadCount +
                '}';
    }
}
