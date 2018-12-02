package com.happybudui.venuesystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class UserEntity implements Serializable {

    private String userId;

    private String userMail;

    private String userName;

    @JsonIgnore
    private String userPassword;

    @JsonIgnore
    private int userStatus;

    private boolean isMailActive;

    public UserEntity(){
        this.userId = UUID.randomUUID().toString().replaceAll("-","");
        userStatus = 0;
    }

    public UserEntity(String userMail, String userName, String userPassword) {
        this.userId = UUID.randomUUID().toString();
        userStatus = 0;
        this.userMail = userMail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isMailActive() {
        return isMailActive;
    }

    public void setMailActive(boolean mailActive) {
        isMailActive = mailActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return getUserStatus() == that.getUserStatus() &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getUserMail(), that.getUserMail()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getUserPassword(), that.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserMail(), getUserName(), getUserPassword(), getUserStatus());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
