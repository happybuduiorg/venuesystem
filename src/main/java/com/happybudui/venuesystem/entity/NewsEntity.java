package com.happybudui.venuesystem.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Date;
import java.util.Objects;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@EntityScan
public class NewsEntity {
    private String newsId;

    private String newsContent;

    private Date newsPublicDate;

    private String newsPublisherName;

    private String newsPublisherId;

    private int newsLevel;

    public NewsEntity() {
        java.util.Date utilDate=new java.util.Date();
        newsPublicDate=new Date(utilDate.getTime());
    }

    public NewsEntity(String newsContent, String newsPublisherName, String newsPublisherId, int newsLevel) {
        this.newsContent = newsContent;
        java.util.Date utilDate=new java.util.Date();
        this.newsPublicDate=new Date(utilDate.getTime());
        this.newsPublisherName = newsPublisherName;
        this.newsPublisherId = newsPublisherId;
        this.newsLevel = newsLevel;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getNewsPublicDate() {
        return newsPublicDate;
    }

    public void setNewsPublicDate(Date newsPublicDate) {
        this.newsPublicDate = newsPublicDate;
    }

    public String getNewsPublisherName() {
        return newsPublisherName;
    }

    public void setNewsPublisherName(String newsPublisherName) {
        this.newsPublisherName = newsPublisherName;
    }

    public String getNewsPublisherId() {
        return newsPublisherId;
    }

    public void setNewsPublisherId(String newsPublisherId) {
        this.newsPublisherId = newsPublisherId;
    }

    public int getNewsLevel() {
        return newsLevel;
    }

    public void setNewsLevel(int newsLevel) {
        this.newsLevel = newsLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsEntity)) return false;
        NewsEntity that = (NewsEntity) o;
        return getNewsLevel() == that.getNewsLevel() &&
                Objects.equals(getNewsId(), that.getNewsId()) &&
                Objects.equals(getNewsContent(), that.getNewsContent()) &&
                Objects.equals(getNewsPublicDate(), that.getNewsPublicDate()) &&
                Objects.equals(getNewsPublisherName(), that.getNewsPublisherName()) &&
                Objects.equals(getNewsPublisherId(), that.getNewsPublisherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewsId(), getNewsContent(), getNewsPublicDate(), getNewsPublisherName(), getNewsPublisherId(), getNewsLevel());
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "newsId='" + newsId + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsPublicDate=" + newsPublicDate +
                ", newsPublisherName='" + newsPublisherName + '\'' +
                ", newsPublisherId='" + newsPublisherId + '\'' +
                ", newsLevel=" + newsLevel +
                '}';
    }
}
