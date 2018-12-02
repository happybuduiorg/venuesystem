package com.happybudui.venuesystem.controller;

import com.happybudui.venuesystem.bean.PageBean;
import com.happybudui.venuesystem.entity.NewsEntity;
import com.happybudui.venuesystem.service.NewsService;
import com.happybudui.venuesystem.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Controller()
@RequestMapping("/news/")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService=newsService;
    }

    @RequestMapping(value = "publicNews",method = RequestMethod.POST)
    public ResponseResult<Integer> publicNews(@RequestParam(name = "content") String content, HttpSession session){
        return this.newsService.publicNews(content,session);
    }

    @RequestMapping(value = "getNews",method = RequestMethod.POST)
    public ResponseResult<PageBean<NewsEntity>> getNews(@RequestParam(name = "currentpage")int currentPage,HttpSession session ){
        return this.newsService.getNewsItemByPage(currentPage,10,session);
    }
}
